package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dto.BoardDto;

public class BModifyService implements BServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println(">>> BModifyService >>>");
		
//		map변환
		Map<String, Object> map = model.asMap();
		
//		map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String brd_id = request.getParameter("brd_id");
		String brd_name = request.getParameter("brd_name");
		String brd_title = request.getParameter("brd_title");
		String brd_content = request.getParameter("brd_content");
		
		BoardDao dao = new BoardDao();
		dao.modify(brd_id, brd_name, brd_title, brd_content);
		
		
	}



}
