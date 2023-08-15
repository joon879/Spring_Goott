package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

public class BDeleteService implements BServiceInter{

	private SqlSession sqlSession;
	
	public BDeleteService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BDeleteService");
//		map변환
		Map<String, Object> map=model.asMap();
//		map에서 request추출
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		//--------컨트롤러 delete에서 가져온거
		String bid=request.getParameter("bid");
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.delete(bid);
		//---------------
		
		
		
//		String bid=request.getParameter("bid");		
//		BoardDao dao=new BoardDao();
//		dao.delete(bid);
	}

}
