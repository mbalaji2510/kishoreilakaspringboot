package com.infosys.usermanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infosys.usermanagement.model.User;

public class AccessController {
	
	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required=false) String message,HttpServletRequest request) {
		model.addAttribute("message", message);
		request.getSession().removeAttribute("UserDetails");
	    request.getSession().removeAttribute("UserName");
	    request.getSession().removeAttribute("userFirstName");
	    request.getSession().removeAttribute("userLastName");
	    request.getSession().removeAttribute("userId");
		User user = new User();
		model.addAttribute(user);
		return "access/login";
	}
	
	@RequestMapping(value = "/denied")
 	public String denied() {
		return "access/denied";
	}
	
	@RequestMapping(value = "/login/failure")
 	public String loginFailure() {
		String message = "Credentials does not match!";
		return "redirect:/login?message="+message;
	}

}
	
