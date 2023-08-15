package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;


import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.JobDto;

public class BEmpSumService implements BServiceInter {

	private SqlSession sqlSession;
	
	public BEmpSumService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BEmpSumService");


		//컨트롤러에서 가져온거-----
		IDao dao=sqlSession.getMapper(IDao.class);
		JSONArray arr = new JSONArray();
		ArrayList<JobDto> jobsum = dao.sumByjob();
		for (JobDto job : jobsum) {

			JSONObject obj = new JSONObject();
			obj.put("job", job.getJob());
			obj.put("sal_sum", job.getSal_sum());
			if(obj != null){
				arr.add(obj);
			}
		}
			
		model.addAttribute("arr", arr);	
		//-----
	}
	
}
