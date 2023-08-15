package com.tech.sprj11p.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.tech.sprj11p.dao.PizzaDao;
import com.tech.sprj11p.dto.PizzaDto;

public class PizzaListService implements PizzaServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println("PizzaListService!!!!!");
		
		//DB에 접속해서 데이터 가져오기
		PizzaDao dao = new PizzaDao(); //PizzaDao 생성자 호출
		ArrayList<PizzaDto> dtos = dao.list(); //글 내용 모두가 담긴 dtos가 됨
		
		//모델에 적재
		model.addAttribute("list", dtos);
		
	}

	
}
