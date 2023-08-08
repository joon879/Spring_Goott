package com.tech.sprj09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.sprj09.service.BListService;
import com.tech.sprj09.service.BServiceInter;

@Controller
public class BController {
	
	BServiceInter bServiceInter;
	
	
	@RequestMapping("/list")
	public String list(Model model) { //model 파라미터는 리스트를 만들 때 가져온다??
		System.out.println("list()");
		
//		데이터 가져오기 작업
		bServiceInter = new BListService();
		bServiceInter.execute(model);
		//BListService의 execute() 결과가 담김
		
		
		return "/list";
	}

}
