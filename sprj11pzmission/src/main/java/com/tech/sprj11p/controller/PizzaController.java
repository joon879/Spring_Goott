package com.tech.sprj11p.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.sprj11p.service.PizzaContentViewService;
import com.tech.sprj11p.service.PizzaDeleteService;
import com.tech.sprj11p.service.PizzaListService;
import com.tech.sprj11p.service.PizzaModifyService;
import com.tech.sprj11p.service.PizzaReplyService;
import com.tech.sprj11p.service.PizzaReplyViewService;
import com.tech.sprj11p.service.PizzaServiceInter;
import com.tech.sprj11p.service.PizzaWriteService;

@Controller
public class PizzaController {

	PizzaServiceInter pizzaServiceInter;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("listttttt");
		
		//데이터 가져오기 작업
		pizzaServiceInter = new PizzaListService();
		pizzaServiceInter.execute(model);
				
		return "/list";
	}
	
	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("write_viewwwwww");
		
		return "/write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("writeeeeeeee");
		
		//글조회 진행
		//model에 담아서 토스
		model.addAttribute("request", request);
		pizzaServiceInter = new PizzaWriteService();
		pizzaServiceInter.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_viewwwwwwwww");
		
		//글조회 진행
		//model에 담아서 토스
		model.addAttribute("request", request);
		pizzaServiceInter = new PizzaContentViewService();
		pizzaServiceInter.execute(model);
		
		return "/content_view";
	}
	
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request, Model model) {
		System.out.println("content_updateeeeeeeee");
		
		//글수정 form
		//model에 담아서 toss. 이 모델이 BModifyService로 전달.
		model.addAttribute("request", request);
		pizzaServiceInter = new PizzaContentViewService();
		pizzaServiceInter.execute(model);
		//content_view와 동일해도 됨
		
		return "/content_update";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modifyyyyyyyyy");
		
		//글수정 update
		//model에 담아서 토스.
		model.addAttribute("request", request);
		pizzaServiceInter = new PizzaModifyService();
		pizzaServiceInter.execute(model);
				
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("deleteeeeeeeee");
		
		//글삭제
		model.addAttribute("request", request);
		pizzaServiceInter = new PizzaDeleteService();
		pizzaServiceInter.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_viewwwwwwwww");
		
		//DB에서 데이터 조회
		//모델에 담아서 토스
		model.addAttribute("request", request);
		pizzaServiceInter = new PizzaReplyViewService();
		pizzaServiceInter.execute(model);
				
		return "reply_view";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("replyyyyyyyyyyyy");
		
		//댓글달기
		//모델에 담아서 토스.
		model.addAttribute("request", request);
		pizzaServiceInter = new PizzaReplyService();
		pizzaServiceInter.execute(model);
		
		return "redirect:list";
	}
	
}
