package com.tech.anno2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class College {
	
	private Principal principal;
	private Teacher teacher;
	
	
	//생성자에서 principal을 받아와서 College에 주입 시켜둠
//	public College(Principal principal) {
//
//		this.principal =principal;
//	}

	public void test() {
		
		principal.principalInfo();
		teacher.teach();
		System.out.println("testing is call methods");
	}

	
	@Autowired //자동주입
	public void setPrincipal(Principal principal) {
		this.principal = principal;
		System.out.println("using setPrincipal method");
	}

	
	@Autowired //자동주입
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
		System.out.println("using setTeacher method");
	}
	
}
