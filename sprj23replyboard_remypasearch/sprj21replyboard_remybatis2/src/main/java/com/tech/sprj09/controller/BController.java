package com.tech.sprj09.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;
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
	
	@Autowired
	private SqlSession sqlSession;
	
	
//	@RequestMapping("/list")
//	public String list(Model model) {
//		System.out.println("====list()====");
//		IDao dao=sqlSession.getMapper(IDao.class);
////		데이터 가져오기 작업
//		bServiceInter=new BListService();
//		bServiceInter.execute(model,dao);
//		
////		ArrayList<BoardDto> dtos=dao.list();
////		model.addAttribute("list",dtos);
//		return "list";
//	}
	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model) {
		System.out.println("====list()====");
		IDao dao=sqlSession.getMapper(IDao.class);
		model.addAttribute("request",request);
//		데이터 가져오기 작업
		bServiceInter=new BListService();
		bServiceInter.execute(model,dao);
		
//		ArrayList<BoardDto> dtos=dao.list();
//		model.addAttribute("list",dtos);
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("====write_view()====");
		return "write_view";
	}
	@RequestMapping("/write")
	public String write(HttpServletRequest request,Model model) {
		System.out.println("====write()====");
//		글쓰기진행
//		toss
		model.addAttribute("request",request);
		IDao dao=sqlSession.getMapper(IDao.class);
		bServiceInter=new BWriteService();
		bServiceInter.execute(model,dao);
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
//		dao.write(bname,btitle,bcontent);
		
		return "redirect:list";
	}
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request,Model model) {
		System.out.println("====content_view()====");
//		글조회
//		toss
		IDao dao=sqlSession.getMapper(IDao.class);
		model.addAttribute("request",request);
		bServiceInter=new BContentViewService();
		bServiceInter.execute(model,dao);
//		String bid=request.getParameter("bid");
		
//		dao.upHit(bid);
		
//		BoardDto dto=dao.contentView(bid);
//		model.addAttribute("content_view",dto);
		
		return "content_view";
	}
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request,Model model) {
		System.out.println("====content_update()====");
//		글수정form
//		toss
		IDao dao=sqlSession.getMapper(IDao.class);
		model.addAttribute("request",request);
		bServiceInter=new BContentViewService();
		bServiceInter.execute(model,dao);
		
//		String bid=request.getParameter("bid");
//		BoardDto dto=dao.contentView(bid);
//		model.addAttribute("content_view",dto);
		
		return "content_update";
	}
	@RequestMapping(method = RequestMethod.POST,value = "/modify")
	public String modify(HttpServletRequest request,Model model) {
		System.out.println("====modify()====");
//		글수정update
//		toss
		IDao dao=sqlSession.getMapper(IDao.class);
		model.addAttribute("request",request);
		bServiceInter=new BModifyService();
		bServiceInter.execute(model,dao);
//		String bid=request.getParameter("bid");
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
//		
//		dao.modify(bid,bname,btitle,bcontent);
		
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			Model model) {
		System.out.println("======delete()======");
//		db에 데이터 삭제
		//toss
		IDao dao=sqlSession.getMapper(IDao.class);
		model.addAttribute("request",request);
		bServiceInter=new BDeleteService();
		bServiceInter.execute(model,dao);
//		String bid=request.getParameter("bid");
//		dao.delete(bid);
		
		return "redirect:list";
	}
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request,
			Model model) {
		System.out.println("======reply_view()======");
//		db에 데이터 조회
		//toss
		IDao dao=sqlSession.getMapper(IDao.class);
		model.addAttribute("request",request);
		bServiceInter=new BReplyViewService();
		bServiceInter.execute(model,dao);
//		String bid=request.getParameter("bid");
//		BoardDto dto=dao.replyView(bid);
//		model.addAttribute("reply_view",dto);	
		return "reply_view";
	}
	@RequestMapping(method = RequestMethod.POST,value = "/reply")
	public String reply(HttpServletRequest request,Model model) {
		System.out.println("====reply()====");
//		댓글달기
//		toss
		IDao dao=sqlSession.getMapper(IDao.class);
		model.addAttribute("request",request);
		bServiceInter=new BReplyService();
		bServiceInter.execute(model,dao);
//		String bid=request.getParameter("bid");
//		String bgroup=request.getParameter("bgroup");
//		String bstep=request.getParameter("bstep");
//		String bindent=request.getParameter("bindent");
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
//		dao.replyShape(bgroup,bstep);
//		dao.reply(bid,bname,btitle,bcontent,bgroup,bstep,bindent);
			
		return "redirect:list";
	}
	
}
