package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.Log;
import dao.LogHandler;
import dao.User;

@Controller
public class LogsController {

	@RequestMapping(value = "/create_log")
	public String createLog(
			HttpServletRequest req,
			HttpServletResponse resp,
			Model m) throws IOException {
		if(AuthController.isUserLoggedIn()) {
			m.addAttribute("newLog", new Log());
			return "views/create_log.jsp";
		}
		resp.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "create_log_action")
	public String postLog(
			HttpServletRequest req, 
			HttpServletResponse resp,
			@Valid
			@ModelAttribute("newLog") Log newLog,
			BindingResult br) throws IOException {
		if(AuthController.isUserLoggedIn()) {
			if(br.hasErrors()) {
				return "views/create_log.jsp";
			}
			User currentUser = (User) req.getSession().getAttribute("currentUser");
			newLog.setAuthor(currentUser);
			newLog.setDatePosted(new Timestamp(System.currentTimeMillis()));
			currentUser.addLog(newLog);
			req.getSession().setAttribute("currentUser", currentUser);
			LogHandler.createLog(newLog);
			resp.sendRedirect("my_logs");
			return null;
		}
		resp.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "/view_mylog")
	public String showMyLog(
			HttpServletRequest req,
			HttpServletResponse resp, 
			Model m, 
			@RequestParam("nr") int pos) throws IOException {
		if(AuthController.isUserLoggedIn()) {
			List<Log> the_logs = ((User) req.getSession().getAttribute("currentUser")).getLogs();

			if(the_logs.size() > pos) {	
				m.addAttribute("theLog", the_logs.get(pos));
				return "views/view_mylog.jsp";
			}
			resp.sendRedirect("my_logs");
			return null;
		}

		resp.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "/view_userlog")
	public String showUserLog(
			HttpServletRequest req, 
			HttpServletResponse resp, 
			Model m, 
			@RequestParam("nr") int pos) throws IOException {
		if(AuthController.isUserLoggedIn()) {

			User clickedUser = (User) req.getSession().getAttribute("clickedUser");
			if(clickedUser != null) {
				List<Log> clickedUserLogs = clickedUser.getLogs();
				if(clickedUserLogs.size() > pos) {
					m.addAttribute("theLog", clickedUserLogs.get(pos));
					return "views/view_mylog.jsp";
				}
				resp.sendRedirect("view_user");
				return null;
			}
			resp.sendRedirect("dashboard");
			return null;
		}

		resp.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "/my_logs")
	public String myLogs(
			HttpServletRequest req, 
			HttpServletResponse resp, 
			Model m) throws IOException {
		if(AuthController.isUserLoggedIn()) {
			m.addAttribute("logsList", 
					(List<Log>) ((User) req.getSession().getAttribute("currentUser")).getLogs());
			return "views/my_logs.jsp";
		}
		resp.sendRedirect("login");
		return null;
	}
}
