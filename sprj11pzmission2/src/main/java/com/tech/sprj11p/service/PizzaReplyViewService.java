package com.tech.sprj11p.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj11p.dao.PizzaDao;
import com.tech.sprj11p.dto.PizzaDto;



public class PizzaReplyViewService implements PizzaServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println(">>> PizzaReplyViewServiceeeeeeeee");

//		map변환
		Map<String, Object> map = model.asMap();
		
//		map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bid = request.getParameter("pzid");
		
		//content view와 동일
		PizzaDao dao = new PizzaDao();
		PizzaDto dto = dao.replyView(bid);
		model.addAttribute("reply_view", dto);
		
	}

}
