package com.tech.mission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GController {
		
	@RequestMapping("/login/loginform")
	public String loginform(Model model) {
		//데이터 전달
		model.addAttribute("id", "blue");
		model.addAttribute("email", "blue@naver.com");	
		model.addAttribute("pass", "1234");
		
		return "/login/loginform";
	}
	
	@RequestMapping("/login/login")
	public String login(Model model, @RequestParam ("id") String id, @RequestParam ("email") String email, @RequestParam ("pass") String pass) {
		//데이터 전달
		model.addAttribute("id", id);
		model.addAttribute("email", email);	
		model.addAttribute("pass", pass);
				
		return "/login/login";
	}
	
	@RequestMapping("/join/joinform")
	public String joinform(Model model) {
//		//데이터 전달
//		model.addAttribute("id", "blue");
//		model.addAttribute("email", "blue@naver.com");	
//		model.addAttribute("pass", "1234");
		
		return "/join/joinform";
	}
	
}
