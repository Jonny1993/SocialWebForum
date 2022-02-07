package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.LoginResult;
import dao.User;
import dao.UserHandler;

@Controller
public class AuthController {
	private static boolean isUserLoggedIn = false;
	private boolean newUserRegistered = false;

	public static boolean isUserLoggedIn(){
		return isUserLoggedIn;
	}

	@RequestMapping(value= "/login")
	public String getLoginForm(
			HttpServletRequest req,
			HttpServletResponse resp,
			Model m
			) throws IOException {

		if(!isUserLoggedIn) {
			m.addAttribute("user", new User());
			return ("views/login.jsp");
		}
		else { 
			resp.sendRedirect("dashboard"); 
			return null;
		}
	}

	@RequestMapping(value = "/login_action")
	public String checkCreds(
			HttpServletRequest req,
			HttpServletResponse resp,
			@Valid
			@ModelAttribute ("user")
			User user,
			BindingResult br) throws IOException {
		
		if(br.getErrorCount() > 1) {
			System.out.println("Error are: " + br.getErrorCount());
			return "views/login.jsp";
		}
		else {
			
			LoginResult userAndUsersList = 	newUserRegistered? 
					new LoginResult(user, UserHandler.getAllUsersExcept(user.getId())) :
							UserHandler.checkLogin(user);
			
			newUserRegistered = false;

			if(userAndUsersList != null) {
				isUserLoggedIn = true;

				User loggedInUser = userAndUsersList.getLoggedInUser();
				List<User> usersList = userAndUsersList.getUsersList();

				req.getSession().setAttribute("currentUser", loggedInUser);
				req.getSession().setAttribute("usersList", usersList);
				resp.sendRedirect("dashboard");
				return null;
			}
			else {
				UserHandler.closeHibernateSession();
				req.getSession().setAttribute("wrongCredentials", "Email or password is wrong");
				resp.sendRedirect("login");
				return null;
			}
		}
	}

	@RequestMapping(value="/register")
	public String registerForm(
			HttpServletRequest req, 
			HttpServletResponse resp,
			Model m) throws IOException{
		if(!isUserLoggedIn) {
			m.addAttribute("user", new User());
			return "views/register.jsp";
		}
		resp.sendRedirect("dashboard");
		return null;
	}

	@RequestMapping(value = "/register_action")
	public String registerSubmit(
			HttpServletRequest req,
			HttpServletResponse resp,
			@Valid
			@ModelAttribute("user") User newUser,
			BindingResult br
			) throws IOException{
		if(br.hasErrors()) {
			return "views/register.jsp";
		}
		if(!UserHandler.registerUser(newUser)) {
			req.getSession().setAttribute("alreadyExists", "A user with this email already exists");
			resp.sendRedirect("register");
			return null;
		}
		newUserRegistered = true;
		checkCreds(req, resp, newUser, br);
		return null;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logoutUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession sess = req.getSession(false);
		if(sess != null) sess.invalidate();
		UserHandler.closeHibernateSession();
		isUserLoggedIn = false;
		res.sendRedirect("welcome");
	}

}
