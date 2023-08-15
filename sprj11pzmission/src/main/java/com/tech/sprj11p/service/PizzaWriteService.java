package com.tech.sprj11p.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj11p.dao.PizzaDao;

public class PizzaWriteService implements PizzaServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println("PizzaWriteServiceeeeeeee");
		
		//모델에서 request를 풀어서 맵으로 전환(PizzaController의 토스 아래)
		Map<String, Object> map = model.asMap();
		
		//맵에서 request를 뽑아내기
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String pzname = request.getParameter("pzname");
		String pzsubj = request.getParameter("pzsubj");
		String pzcontent = request.getParameter("pzcontent");
		
		PizzaDao dao = new PizzaDao(); //DB접속 준비완료, 생성자로
		dao.write(pzname, pzsubj, pzcontent);
	}

}
