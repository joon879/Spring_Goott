package com.tech.sprj11p.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.sprj11p.dao.IDao;
import com.tech.sprj11p.dao.PizzaDao;
import com.tech.sprj11p.dto.PizzaDto;
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
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("listttttt");
		
//		//데이터 가져오기 작업
//		pizzaServiceInter = new PizzaListService();
//		pizzaServiceInter.execute(model);
		
		//인터페이스 타입으로 가져오기
		IDao dao = sqlSession.getMapper(IDao.class);
		ArrayList<PizzaDto> dtos = dao.list();
		//인터페이스에 선언한 list()
		//글 내용 모두가 담긴 dtos가 됨
		//모델에 적재
		model.addAttribute("list", dtos);
		
				
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
		
//		//글조회 진행
//		//model에 담아서 토스
//		model.addAttribute("request", request);
//		pizzaServiceInter = new PizzaWriteService();
//		pizzaServiceInter.execute(model);
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String pzname = request.getParameter("pzname");
		String pzsubj = request.getParameter("pzsubj");
		String pzcontent = request.getParameter("pzcontent");
		dao.write(pzname, pzsubj, pzcontent);
		
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_viewwwwwwwww");
//		
//		//글조회 진행
//		//model에 담아서 토스
//		model.addAttribute("request", request);
//		pizzaServiceInter = new PizzaContentViewService();
//		pizzaServiceInter.execute(model);
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String pzid = request.getParameter("pzid");
		
		dao.upHit(pzid);
		
		PizzaDto dto = dao.pizzaContentView(pzid);
		model.addAttribute("content_view", dto);
		
		return "/content_view";
	}
	
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request, Model model) {
		System.out.println("content_updateeeeeeeee");
		
//		//글수정 form
//		//model에 담아서 toss. 이 모델이 BModifyService로 전달.
//		model.addAttribute("request", request);
//		pizzaServiceInter = new PizzaContentViewService();
//		pizzaServiceInter.execute(model);
//		//content_view와 동일해도 됨
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String pzid = request.getParameter("pzid");
		PizzaDto dto = dao.pizzaContentView(pzid);
		model.addAttribute("content_view", dto);
		
		return "/content_update";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modifyyyyyyyyy");
		
//		//글수정 update
//		//model에 담아서 토스.
//		model.addAttribute("request", request);
//		pizzaServiceInter = new PizzaModifyService();
//		pizzaServiceInter.execute(model);
		
		//인터페이스 타입으로
		IDao dao = sqlSession.getMapper(IDao.class);
		String pzid = request.getParameter("pzid");
		String pzname = request.getParameter("pzname");
		String pzsubj = request.getParameter("pzsubj");
		String pzcontent = request.getParameter("pzcontent");	
		
		dao.modify(pzid, pzname, pzsubj, pzcontent);
				
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("deleteeeeeeeee");
		
		//글삭제
//		model.addAttribute("request", request);
//		pizzaServiceInter = new PizzaDeleteService();
//		pizzaServiceInter.execute(model);
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String pzid = request.getParameter("pzid");
		dao.delete(pzid);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_viewwwwwwwww");
		
//		//DB에서 데이터 조회
//		//모델에 담아서 토스
//		model.addAttribute("request", request);
//		pizzaServiceInter = new PizzaReplyViewService();
//		pizzaServiceInter.execute(model);
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String bid = request.getParameter("pzid");
		PizzaDto dto = dao.replyView(bid);
		model.addAttribute("reply_view", dto);
		
				
		return "reply_view";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("replyyyyyyyyyyyy");
		
//		//댓글달기
//		//모델에 담아서 토스.
//		model.addAttribute("request", request);
//		pizzaServiceInter = new PizzaReplyService();
//		pizzaServiceInter.execute(model);
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String pzid = request.getParameter("pzid");
		String pzgroup = request.getParameter("pzgroup");
		String pzstep = request.getParameter("pzstep");
		String pzintent = request.getParameter("pzintent");
		String pzname = request.getParameter("pzname");
		String pzsubj = request.getParameter("pzsubj");
		String pzcontent = request.getParameter("pzcontent");
		
		dao.replyShape(pzgroup, pzstep);
		dao.reply(pzid, pzname, pzsubj, pzcontent, pzgroup, pzstep, pzintent);
		
		
		
		return "redirect:list";
	}
	
}
