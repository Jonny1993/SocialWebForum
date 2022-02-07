package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.Message;
import dao.MessageHandler;
import dao.User;

@Controller
public class MessagesController {
	@RequestMapping(value = "/view_message")
	public String viewMessage(
			HttpServletRequest req,
			HttpServletResponse resp,
			Model m,
			@RequestParam("nr") int positionInList) throws IOException{
		if(AuthController.isUserLoggedIn()) {
			List<Message> message_list = (
					(User) req.getSession().getAttribute("currentUser")
					).getMessagesReceived();
			if(message_list.size() > positionInList) {
				Message theMessage = message_list.get(positionInList);
				m.addAttribute("theMessage", theMessage);
				return("views/view_message.jsp");
			}
			resp.sendRedirect("my_messages");
			return null;
		}
		resp.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "/my_messages")
	public String showAllReceived(
			HttpServletRequest req,
			HttpServletResponse resp,
			Model m) throws IOException {
		if(AuthController.isUserLoggedIn()) {
			m.addAttribute("message_list", 
					(List<Message>)((User) req.getSession()
							.getAttribute("currentUser")).getMessagesReceived());
			return ("views/my_messages.jsp");
		}
		resp.sendRedirect("login");
		return null;
	}

	@RequestMapping(value= "/send_msg")
	public String sendMessage(
			HttpServletRequest req,
			HttpServletResponse resp,
			Model m) throws IOException{
		if(AuthController.isUserLoggedIn()) {
			System.out.println("Title: "+ (String) req.getSession().getAttribute("messageTitle"));
			m.addAttribute("msg", new Message());
			return "views/send_msg.jsp";
		}
		resp.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "/send_msg_action")
	public String sendMessageOnClick(
			HttpServletRequest req,
			HttpServletResponse resp,
			@Valid
			@ModelAttribute("msg") Message msg,
			BindingResult br
			) throws IOException {
		if(AuthController.isUserLoggedIn()) {
			if(!br.hasErrors()) {
				User theSender = (User) req.getSession().getAttribute("currentUser");
				int receiverIndex = Integer.valueOf(req.getCookies()[1].getValue());
				User theReciever = ((List<User>) req.getSession().getAttribute("usersList")).get(receiverIndex);
				msg.setSender(theSender);
				msg.setReceiver(theReciever);
				msg.setDateReceived(new Timestamp(System.currentTimeMillis()));
				MessageHandler.sendMessage(msg);
				req.getSession().setAttribute("messageSent", "Message was sent successfully!");
				resp.sendRedirect("my_messages");
				return null;
			}
			return "views/send_msg.jsp";
		}
		resp.sendRedirect("login");
		return null;
	}
}
