package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

@Service
public class BReplyService implements BServiceInter{

	private SqlSession sqlSession;
	
	public BReplyService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BReplyService");
		
//		map변환
		Map<String, Object> map=model.asMap();
//		map에서 request추출
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		
		
		// 컨트롤러 리플라이에서 가져온거-------------
		String bid=request.getParameter("bid");
		String bgroup=request.getParameter("bgroup");
		String bstep=request.getParameter("bstep");
		String bindent=request.getParameter("bindent");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.replyShape(bgroup,bstep);
		dao.reply(bid,bname,btitle,bcontent,bgroup,bstep,bindent);
		// -------------
		
		
		
		
		
		
//		String bid=request.getParameter("bid");
//		String bgroup=request.getParameter("bgroup");
//		String bstep=request.getParameter("bstep");
//		String bindent=request.getParameter("bindent");
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
//		
//		BoardDao dao=new BoardDao();
//		dao.reply(bid,bname,btitle,bcontent,bgroup,bstep,bindent);
		
		
	}
}