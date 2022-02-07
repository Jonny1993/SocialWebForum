package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.Log;
import dao.LogHandler;
import dao.User;
import dao.UserHandler;
import db.HibernateUtil;

@Controller
public class DashboardController {

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String loadDashboard(
			Model m, 
			HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		if(AuthController.isUserLoggedIn()) {

			List<User> users = (List<User>) req.getSession().getAttribute("usersList");
			m.addAttribute("users_list", users);

			return "views/dashboard.jsp";
		}
		res.sendRedirect("login");
		return null;
	}
	
	@RequestMapping(value = "/view_user")
	public String viewUser(
			Model m, 
			HttpServletRequest req,
			HttpServletResponse resp,
			@RequestParam("nr") int nr
			) throws IOException {
		if(AuthController.isUserLoggedIn()) {
			List<User> users = (List<User>) req.getSession().getAttribute("usersList");
			if(users.size() > nr) {
				User clickedUser = LogHandler.getUserWithLogs(users.get(nr).getId());
				req.getSession().setAttribute("clickedUser", clickedUser);
				m.addAttribute("logsList", clickedUser.getLogs());
				return "views/view_user.jsp";
			}
			resp.sendRedirect("dashboard");
			return null;
		}
		resp.sendRedirect("login");
		return null;
	}
}
