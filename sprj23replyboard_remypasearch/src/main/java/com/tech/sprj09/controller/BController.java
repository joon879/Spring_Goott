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
import com.tech.sprj09.vopage.SearchVO;

@Controller
public class BController {
	
	BServiceInter bServiceInter;
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, SearchVO searchVO, Model model) { //model 파라미터는 리스트를 만들 때 가져온다??
		System.out.println("====list()====");
		
//		데이터 가져오기 작업
//		bServiceInter = new BListService();
//		bServiceInter.execute(model);
		
		//IDao.xml mapper 파일을 IDao 인터페이스 타입으로 가져온다.
		IDao dao = sqlSession.getMapper(IDao.class);
		
//		searching
		String btitle = "";
		String bcontent = "";
		
		//체크박스는 배열로 받는다
		String[] brdtitle = request.getParameterValues("searchType");
		System.out.println("brdtitle:"+brdtitle);
		
		//아무것도 체크하지 않으면 length자체가 없어서 오류난다.
		if (brdtitle != null) {
			for (int i = 0; i < brdtitle.length; i++) {
				System.out.println("brdtitle: "+brdtitle[i]);		
			}
		}
		
		//변수에 저장 체크박스 선택한거 
		if (brdtitle != null) {
			for (String var : brdtitle) {
				if(var.contentEquals("btitle")) {
					btitle = "btitle";
					model.addAttribute("btitle", "true");
				} else if(var.equals("bcontent")) {
					bcontent = "bcontent";
					model.addAttribute("bcontent", "true");
				}
			}
		}
		
		//검색결과 유지
		String bt = request.getParameter("btitle");
		String bc = request.getParameter("bcontent");
		
		//변수에 저장 
		if (bt != null) {
			if(bt.equals("btitle")) {
				btitle = bt;
				model.addAttribute("btitle", "true");
			}
		}
		if (bc != null) {
			if(bc.equals("bcontent")) {
				bcontent = bc;
				model.addAttribute("bcontent", "true");
			}
		}
				
		
		//sk값(검색어) 가져오기
		String searchKeyword = request.getParameter("sk");
		if (searchKeyword == null) {
			searchKeyword = "";
		}
			
		//검색창에 방금 입력한 조건과 검색어가 남아있도록.
		model.addAttribute("resk", searchKeyword);
		//model에 검색조건 선택된거는 트루 넣어둠.
		
		//paging 시작
		String strPage = request.getParameter("page");
		
		//처음 list null처리
		if(strPage==null) {
			strPage = "1";
		}
		System.out.println("pagggggg:"+strPage);
		int page = Integer.parseInt(strPage);
		searchVO.setPage(page); //페이지 전달
		
		//글의 총 갯수 구하기
//		int total = dao.selectBoardTotCount();
		
		//검색에 따른 총갯수 변형
		int total = 0;
		//검색어 체크박스 4개의 경우의 수로 총갯수 구하기
		if(btitle.contentEquals("btitle") && bcontent.contentEquals("")) {
			total = dao.selectBoardTotCount1(searchKeyword);
		} else if(btitle.contentEquals("") && bcontent.contentEquals("bcontent")) {
			total = dao.selectBoardTotCount2(searchKeyword);
		} else if(btitle.contentEquals("btitle") && bcontent.contentEquals("bcontent")) {
			total = dao.selectBoardTotCount3(searchKeyword);
		} else if(btitle.contentEquals("") && bcontent.contentEquals("")) {
			total = dao.selectBoardTotCount4(searchKeyword);
		}
		
		
		System.out.println("totcnt: "+total);
		searchVO.pageCalculate(total);
		//계산 결과들 출력
		System.out.println("totrow: "+total);
		System.out.println("clickpage: "+searchVO.getPage());
		System.out.println("pageStart: "+searchVO.getPageStart());
		System.out.println("pageEnd: "+searchVO.getPageEnd());
		System.out.println("pageTot: "+searchVO.getTotPage());
		System.out.println("rowStart: "+searchVO.getRowStart());
		System.out.println("rowEnd: "+searchVO.getRowEnd());		
		
		//페이징 글 번호 전달
		int rowStart = searchVO.getRowStart();
		int rowEnd = searchVO.getRowEnd();		
		
//		ArrayList<BoardDto> dtos = dao.list(rowStart, rowEnd);
//		ArrayList<BoardDto> list = null;
		//리스트에 뿌려주는 select도 검색 경우의 수 반영
		if(btitle.contentEquals("btitle") && bcontent.contentEquals("")) {
//			list = dao.list(rowStart, rowEnd, searchKeyword, "1");
			model.addAttribute("list", dao.list(rowStart, rowEnd, searchKeyword, "1"));
		} else if(btitle.contentEquals("") && bcontent.contentEquals("bcontent")) {
//			list = dao.list(rowStart, rowEnd, searchKeyword, "2");
			model.addAttribute("list", dao.list(rowStart, rowEnd, searchKeyword, "2"));
		} else if(btitle.contentEquals("btitle") && bcontent.contentEquals("bcontent")) {
//			list = dao.list(rowStart, rowEnd, searchKeyword, "3");
			model.addAttribute("list", dao.list(rowStart, rowEnd, searchKeyword, "3"));
		} else if(btitle.contentEquals("") && bcontent.contentEquals("")) {
//			list = dao.list(rowStart, rowEnd, searchKeyword, "4");
			model.addAttribute("list", dao.list(rowStart, rowEnd, searchKeyword, "4"));
		}
		
		
		
//		ArrayList<BoardDto> dtos = dao.list(); //인터페이스에 선언한 list()
//		model.addAttribute("list", list);
		model.addAttribute("totRowcnt", total);
		model.addAttribute("searchVO", searchVO);
		
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
//		model.addAttribute("request", request);
//		bServiceInter = new BWriteService();
//		bServiceInter.execute(model);
		
		//인터페이스 타입으로
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.write(bname, btitle, bcontent);
		
//		return "/list"; //리다이렉트와 다르다??
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("====content_view()====");
				
		//글조회 진행
		//model에 담아서 toss. 이 모델이 BContentViewService로 전달.
//		model.addAttribute("request", request);
//		bServiceInter = new BContentViewService();
//		bServiceInter.execute(model);
		
		//인터페이스 타입으로
		IDao dao = sqlSession.getMapper(IDao.class);
		String bid = request.getParameter("bid");
		
		dao.upHit(bid);
		
		BoardDto dtos = dao.contentView(bid);
		model.addAttribute("content_view", dtos);
		
		
		return "/content_view";
	}
	
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request, Model model) {
		System.out.println("====content_update()====");
				
		//글수정 form
		//model에 담아서 toss. 이 모델이 BContentViewService로 전달.
//		model.addAttribute("request", request);
//		bServiceInter = new BContentViewService();
//		bServiceInter.execute(model);
		
//		//content_view와 동일해도 됨	
		//인터페이스 타입으로
		IDao dao = sqlSession.getMapper(IDao.class);
		String bid = request.getParameter("bid");
		BoardDto dtos = dao.contentView(bid);
		model.addAttribute("content_view", dtos);
		
		
		return "/content_update";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("====modify()====");
				
//		//글수정 update
//		//model에 담아서 toss. 이 모델이 BContentViewService로 전달.
//		model.addAttribute("request", request);
//		bServiceInter = new BModifyService();
//		bServiceInter.execute(model);
		
		//인터페이스 타입으로
		IDao dao = sqlSession.getMapper(IDao.class);
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		dao.modify(bid, bname, btitle, bcontent);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("====delete()====");
				
//		//글삭제
//		//model에 담아서 toss. 이 모델이 BDeleteService로 전달.
//		model.addAttribute("request", request);
//		bServiceInter = new BDeleteService();
//		bServiceInter.execute(model);
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String bid = request.getParameter("bid");
		dao.delete(bid);
		
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("====reply_view()====");
				
//		//DB에서 데이터 조회
//		//model에 담아서 toss. 이 모델이 BDeleteService로 전달.
//		model.addAttribute("request", request);
//		bServiceInter = new BReplyViewService();
//		bServiceInter.execute(model);
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String bid = request.getParameter("bid");
		BoardDto dto = dao.replyView(bid);
		model.addAttribute("reply_view", dto);
				
		return "reply_view";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("====reply()====");
				
//		//댓글달기
//		//model에 담아서 toss. 이 모델이 BContentViewService로 전달.
//		model.addAttribute("request", request);
//		bServiceInter = new BReplyService();
//		bServiceInter.execute(model);
		
		//인터페이스로
		IDao dao = sqlSession.getMapper(IDao.class);
		String bid = request.getParameter("bid");
		String bgroup = request.getParameter("bgroup");
		String bstep = request.getParameter("bstep");
		String bindent = request.getParameter("bindent");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		dao.replyShape(bgroup, bstep);
		dao.reply(bid, bname, btitle, bcontent, bgroup, bstep, bindent);
		
				
		return "redirect:list";
	}
	

}
