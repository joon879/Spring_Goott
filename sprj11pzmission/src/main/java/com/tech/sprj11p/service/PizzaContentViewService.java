package com.tech.sprj11p.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj11p.dao.PizzaDao;
import com.tech.sprj11p.dto.PizzaDto;

public class PizzaContentViewService implements PizzaServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println("PizzaContentViewServiceeeeeeeeeee");
		
		//map변환
		Map<String, Object> map = model.asMap();
		
		//map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pzid = request.getParameter("pzid");
		System.out.println("pzid: "+ pzid);
		
		PizzaDao dao = new PizzaDao(); //DB접속 준비
		PizzaDto dto = dao.pizzaContentView(pzid);
		
		//리턴받은 해당글을(dto) 모델에 적재
		model.addAttribute("content_view", dto);
		
	}

}
