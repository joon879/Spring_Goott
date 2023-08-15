package com.tech.sprj09.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

public class BListService implements BServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println(">>> BListService >>>");
		
//		DB에 접속해서 데이터 가져오기
		BoardDao dao = new BoardDao(); //BoardDao 생성자 호출(hihi)
		ArrayList<BoardDto> dtos = dao.list(); //글 내용 모두가 담긴 dtos가 됨.
		
		//모델에 적재
		model.addAttribute("list", dtos);
		
	}

}
