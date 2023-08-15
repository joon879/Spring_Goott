package com.tech.sprj11p.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj11p.dao.PizzaDao;

public class PizzaDeleteService implements PizzaServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println(">>> PizzaDeleteService >>>");
		
		//map 변환
		Map<String, Object> map = model.asMap();
		
		//map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pzid = request.getParameter("pzid");
		
		//DB에 접속해서 데이터 가져오기
		PizzaDao dao = new PizzaDao(); //PizzaDao 생성자 호출
		dao.delete(pzid);

	}

}
