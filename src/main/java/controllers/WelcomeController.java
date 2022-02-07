package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {
    
    public WelcomeController(){
        System.out.println("\n\nWelcome Controller Initialized");
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomePage(){
        return "index.jsp";
    }
    
}
