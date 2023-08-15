package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;

public class BWriteService implements BServiceInter {

	@Override
	public void execute(Model model) {
		System.out.println(">>> BWriteService >>>");
		//모델에서 request를 풀어서 맵으로 전환(BController의 toss아래)
		Map<String, Object> map = model.asMap();
		
		//맵에서 request를 뽑아내기
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String brd_name = request.getParameter("brd_name");
		String brd_title = request.getParameter("brd_title");
		String brd_content = request.getParameter("brd_content");
		
		BoardDao dao = new BoardDao(); //DB접속 준비완료, 생성자로
		dao.write(brd_name, brd_title, brd_content);
		
		
		
	}

}
