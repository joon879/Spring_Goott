package com.tech.ex;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class MyMapController {

	@RequestMapping("/bview")
	public String bview(Model model) {
		model.addAttribute("id", "iiiiiiii");
		
		return "board/bview";
	}
	
	@RequestMapping("/modelandview/modelView2")
	public ModelAndView modelView2() {
		ModelAndView mv = new ModelAndView();
		
//		list 전달
		ArrayList<String> list = new ArrayList<String>();
		list.add("movie1");
		list.add("movie2");
		list.add("movie3");
		list.add("movie4");
		list.add("movie5");
		
		mv.addObject("hobbys", list);
		
		
		mv.addObject("data", "modelandView2");
		mv.setViewName("/modelAndView/modelView2");
		
		return mv;
	}
	
}
