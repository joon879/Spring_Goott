package com.tech.sprj09.service;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

@Service
public class BDownloadService implements BServiceInter{
	
	private SqlSession sqlSession;
	
	public BDownloadService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void execute(Model model) {
		System.out.println(">>>BDownloadService");
		
//		map변환
		Map<String, Object> map=model.asMap();
//		map에서 request추출
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		HttpServletResponse response=
				(HttpServletResponse) map.get("response");
		

		//컨트롤러 download()에서 가져온거-------------
		String path=request.getParameter("p");
		String fname=request.getParameter("f");
		String bid=request.getParameter("bid");
		
		try {
			//down처리
			response.setHeader("Content-Disposition",
					"Attachment;filename="+URLEncoder.encode(fname,"utf-8"));
			String attachPath="resources\\upload\\";
			String realPath=request.getSession().getServletContext().getRealPath(attachPath)+"\\"+fname;
			System.out.println("realpath: "+realPath);
			
//			stream연결(다운로드)
			FileInputStream fin=new FileInputStream(realPath);
			ServletOutputStream sout=response.getOutputStream();
			
			byte[] buf=new byte[1024];
			int size=0;
			while ((size=fin.read(buf,0,1024))!=-1) {
				sout.write(buf,0,size);
			}
			fin.close();
			sout.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//-------------
		
		
		
//		
//		String bid=request.getParameter("bid");
////		System.out.println("bidddddd : "+bid);
//		BoardDao dao=new BoardDao();//db접속준비
//		BoardDto dto=dao.contentView(bid);
//		
//		//리턴받은 해당글(dto)을 모델에 적제
//		model.addAttribute("content_view",dto);
	}
}
