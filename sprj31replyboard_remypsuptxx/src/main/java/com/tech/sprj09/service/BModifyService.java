package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

public class BModifyService implements BServiceInter{
	
	private SqlSession sqlSession;
	
	public BModifyService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BModifyService");
		
//		map변환
		Map<String, Object> map=model.asMap();
//		map에서 request추출
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		
		
		//컨트롤러 modify()에서 가져온거------------------
		IDao dao=sqlSession.getMapper(IDao.class);
		String bid=request.getParameter("bid");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		
		dao.modify(bid,bname,btitle,bcontent);
		//------------------
		
		
		
		
//		String bid=request.getParameter("bid");
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
////		System.out.println(">>>>>>>"+bid);
//		BoardDao dao=new BoardDao();
//		dao.modify(bid,bname,btitle,bcontent);
		
	}
}
