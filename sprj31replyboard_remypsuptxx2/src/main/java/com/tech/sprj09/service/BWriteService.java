package com.tech.sprj09.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;

@Service
public class BWriteService implements BServiceInter{

	private SqlSession sqlSession;
	
	public BWriteService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void execute(Model model) {
		System.out.println(">>>BWriteService");
//		모델에서 request를 풀어서
//		맵으로 전환
		Map<String, Object> map=model.asMap();
//		맵에서 request를 뽑아내기
		
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		

		//-----------컨트롤러 write()에서 가져온거
		
//		글쓰기진행
//		toss
//		model.addAttribute("request",request);
//		bServiceInter=new BWriteService();
//		bServiceInter.execute(model);
//		multipart에서 변형
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
		
//		경로 만들기
//		String attachPath="resources\\upload\\";
//		String uploadPath=request.getSession().getServletContext().getRealPath("/");
//		System.out.println("uploadPath:"+uploadPath);
//		String path=uploadPath+attachPath;
//		System.out.println("pathhhh: "+path);
		String path="C:\\javabigsetspring2023\\spring_work\\sprj31replyboard_remypsuptxx\\src\\main\\webapp\\resources\\upload";
		MultipartRequest req= null;
		
		try {
			req = new MultipartRequest(request, path, 1024*1024*20, "utf-8",
							new DefaultFileRenamePolicy());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String bname=req.getParameter("bname");
		String btitle=req.getParameter("btitle");
		String bcontent=req.getParameter("bcontent");
		String fname=req.getFilesystemName("file");
		
//		System.out.println("bcontent : "+bcontent);
//		System.out.println("fname : "+fname);
		
		if (fname==null) {
			fname="";
		}
		
		
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.write(bname,btitle,bcontent,fname);
		
		//---------------
		
		
		
		
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
//		
//		BoardDao dao=new BoardDao();//db접속 준비완료
//		dao.write(bname,btitle,bcontent);
				
		
	}

}
