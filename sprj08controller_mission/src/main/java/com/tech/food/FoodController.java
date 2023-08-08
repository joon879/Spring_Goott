package com.tech.food;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FoodController {
	
	@RequestMapping("/food/kfood")
	public String kfoodform() {
		
		return "/food/kfood";
	}
	
	@RequestMapping("/food/wfood")
	public String wfoodform() {
		
		return "/food/wfood";
	}
	
	@RequestMapping("/food/kfoodconfirm")
	public String kfoodconfirm(HttpServletRequest request, Model model) {
		
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		
		model.addAttribute("first", first);
		model.addAttribute("second", second);
		
		return "food/kfoodconfirm";
	}
	
	@RequestMapping("/food/wfoodconfirm")
	public ModelAndView wfoodconfirm(@RequestParam("first") String first, 
			@RequestParam("second") String second) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("first", first);
		mv.addObject("second", second); 
		mv.setViewName("food/wfoodconfirm");
		
		return mv;
	}
	
}
