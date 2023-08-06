package com.tech.sprj06controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	@RequestMapping("view")
	public String view() {
		
		return "view";
	}
	
	@RequestMapping("/content/contentView")
	public String contentview(Model model) {
//		데이터 전달
		model.addAttribute("id", "Blueeeeeeeee");
		model.addAttribute("name", "아비요!!");
		
		return "/content/contentView";
	}
	
	@RequestMapping("/model/modelEx")
	public String modelEx(Model model) {
//		데이터 전달
		model.addAttribute("id", "Redddddddd");
		model.addAttribute("age", "34");
		
		return "/model/modelEx";
	}
	
	@RequestMapping("/modelAndView/modelView")
	public ModelAndView modelAndView() {

		ModelAndView mv = new ModelAndView();
		mv.addObject("data", "modelandviewdata");
		mv.setViewName("modelAndView/modelView");
		
		return mv;
	}
	
	
	
	
	
	
	
}
