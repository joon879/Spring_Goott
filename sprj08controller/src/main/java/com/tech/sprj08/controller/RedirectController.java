package com.tech.sprj08.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	
	@RequestMapping("/studentConfirm")
	public String studentRedirect(HttpServletRequest request) {
				
		String id = request.getParameter("id");
		if(id.equals("abc")) {
			
			return "redirect:studentOk";
		}			
		return "redirect:studentNg?msg=tryagain";
	}
	
	@RequestMapping("/studentOk")
	public String studentOk(HttpServletRequest request) {
		System.out.println("studentOk");
		return "studentOk";
	}
	
	@RequestMapping("/studentNg")
	public String studentNg(HttpServletRequest request) {
		System.out.println("studentNg:"+request.getParameter("msg"));
		
		return "studentNg";
	}

}
