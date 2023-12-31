package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dto.BoardDto;

public class BContentViewService implements BServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println(">>> BContentViewService >>>");
		
//		map변환
		Map<String, Object> map = model.asMap();
		
//		map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bid = request.getParameter("bid");
		System.out.println("bid: "+bid);
		
		BoardDao dao = new BoardDao(); //DB접속 준비
		BoardDto dto = dao.contentView(bid);
		
//		리턴받은 해당글을(dto) 모델에 적재
		model.addAttribute("content_view", dto);
		
	}



}
