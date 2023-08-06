package com.tech.sprj07;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login/loginform22")
	public String loginform() {
		
		return ("/login/loginform");
	}
	
	@RequestMapping("/login/loginconfirm")
	public String loginconfirm(HttpServletRequest request, Model model) {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		model.addAttribute("id", id);
		model.addAttribute("pwd", pwd);
		
		return ("/login/loginconfirm");
	}

}
