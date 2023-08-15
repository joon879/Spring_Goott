package com.tech.sprj11p.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj11p.dao.PizzaDao;

public class PizzaModifyService implements PizzaServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println(">>> PizzaModifyService >>>");
		
//		map변환
		Map<String, Object> map = model.asMap();
		
//		map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pzid = request.getParameter("pzid");
		String pzname = request.getParameter("pzname");
		String pzsubj = request.getParameter("pzsubj");
		String pzcontent = request.getParameter("pzcontent");
		
		PizzaDao dao = new PizzaDao();
		dao.modify(pzid, pzname, pzsubj, pzcontent);
		
		
	}
	
}
