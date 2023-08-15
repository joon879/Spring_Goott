package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;
import com.tech.sprj09.vopage.SearchVO;

public class BListService implements BServiceInter{

	@Override
	public void execute(Model model,IDao dao) {
		System.out.println(">>>BListService");
//		map변환
		Map<String, Object> map=model.asMap();
//		map에서 request추출
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");//***
//		db에 접속해서 데이터 가져오기
		//BoardDao dao=new BoardDao();
		
//		paging
		String strPage=request.getParameter("page");
//		처음 null처리
		if(strPage==null)
			strPage="1";
		System.out.println("pagggg:"+strPage);
		int page=Integer.parseInt(strPage);
		SearchVO searchVO=new SearchVO();//***
		searchVO.setPage(page);
		
//		글의 총갯수 구하기
		int total=dao.selectBoardTotCount();
		System.out.println("totcnt : "+total);
		searchVO.pageCalculate(total);
		//계산결과들 출력
		System.out.println("totrow:"+total);
		System.out.println("clickpage:"+searchVO.getPage());
		System.out.println("pageStart:"+searchVO.getPageStart());
		System.out.println("pageEnd:"+searchVO.getPageEnd());
		System.out.println("pageTot:"+searchVO.getTotPage());
		System.out.println("rowStart:"+searchVO.getRowStart());
		System.out.println("rowEnd:"+searchVO.getRowEnd());
		
		//패이징 글 번호전달
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
		ArrayList<BoardDto> dtos=dao.list(rowStart,rowEnd);
		
//		ArrayList<BoardDto> dtos=dao.list();
		model.addAttribute("list",dtos);
		model.addAttribute("totRowcnt",total);
		model.addAttribute("searchVO",searchVO);
		
	}

}
