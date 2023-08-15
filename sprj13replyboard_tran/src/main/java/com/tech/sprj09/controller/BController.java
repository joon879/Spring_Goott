package com.tech.sprj09.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.sprj09.service.BContentViewService;
import com.tech.sprj09.service.BDeleteService;
import com.tech.sprj09.service.BListService;
import com.tech.sprj09.service.BModifyService;
import com.tech.sprj09.service.BReplyService;
import com.tech.sprj09.service.BReplyViewService;
import com.tech.sprj09.service.BServiceInter;
import com.tech.sprj09.service.BWriteService;

@Controller
public class BController {
	
	BServiceInter bServiceInter;
	
	
	@RequestMapping("/list")
	public String list(Model model) { //model 파라미터는 리스트를 만들 때 가져온다??
		System.out.println("====list()====");
		
//		데이터 가져오기 작업
		bServiceInter = new BListService();
		bServiceInter.execute(model);
		//BListService의 execute() 결과가 담김
		
		
		return "/list";
	}
	
	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("====write_view()====");
		
		return "/write_view";
	}
	
	@RequestMapping("write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("====write()====");
		//글쓰기 진행
		//model에 담아서 toss
		model.addAttribute("request", request);
		bServiceInter = new BWriteService();
		bServiceInter.execute(model);
		
		
//		return "/list"; //리다이렉트와 다르다??
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("====content_view()====");
				
		//글조회 진행
		//model에 담아서 toss. 이 모델이 BContentViewService로 전달.
		model.addAttribute("request", request);
		bServiceInter = new BContentViewService();
		bServiceInter.execute(model);
		
		
		
		return "/content_view";
	}
	
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request, Model model) {
		System.out.println("====content_update()====");
				
		//글수정 form
		//model에 담아서 toss. 이 모델이 BContentViewService로 전달.
		model.addAttribute("request", request);
		bServiceInter = new BContentViewService();
		bServiceInter.execute(model);
		//content_view와 동일해도 됨
		
		
		return "/content_update";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("====modify()====");
				
		//글수정 update
		//model에 담아서 toss. 이 모델이 BContentViewService로 전달.
		model.addAttribute("request", request);
		bServiceInter = new BModifyService();
		bServiceInter.execute(model);
		
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("====delete()====");
				
		//글삭제
		//model에 담아서 toss. 이 모델이 BDeleteService로 전달.
		model.addAttribute("request", request);
		bServiceInter = new BDeleteService();
		bServiceInter.execute(model);
		
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("====reply_view()====");
				
		//DB에서 데이터 조회
		//model에 담아서 toss. 이 모델이 BDeleteService로 전달.
		model.addAttribute("request", request);
		bServiceInter = new BReplyViewService();
		bServiceInter.execute(model);
				
		
		return "reply_view";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("====reply()====");
				
		//댓글달기
		//model에 담아서 toss. 이 모델이 BContentViewService로 전달.
		model.addAttribute("request", request);
		bServiceInter = new BReplyService();
		bServiceInter.execute(model);
				
		return "redirect:list";
	}
	

}
