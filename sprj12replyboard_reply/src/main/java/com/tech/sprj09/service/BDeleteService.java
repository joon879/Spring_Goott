package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dto.BoardDto;

public class BDeleteService implements BServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println(">>> BDeleteService >>>");
		
//		map변환
		Map<String, Object> map = model.asMap();
		
//		map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bid = request.getParameter("bid");
		
//		DB에 접속해서 데이터 가져오기
		BoardDao dao = new BoardDao(); //BoardDao 생성자 호출(hihi)
		dao.delete(bid);
		
	}

}
