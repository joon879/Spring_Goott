package com.tech.coffee;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CoffeeController {
	
	@RequestMapping("/coffee/coffeeform")
	public String coffeeform() {
		
		return("/coffee/coffeeform");
	}
	
	@RequestMapping("/coffee/coffeeresult")
	public String coffeeresult(HttpServletRequest request, Model model) {
		
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		
		model.addAttribute("name", name);
		model.addAttribute("price", price);
		
		return ("/coffee/coffeeresult");
	}

}
