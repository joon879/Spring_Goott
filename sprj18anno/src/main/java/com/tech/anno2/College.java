package com.tech.anno2;

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

	//세터로 의존성 주입??
	public void setPrincipal(Principal principal) {
		this.principal = principal;
		System.out.println("using setPrincipal method");
	}

	//인터페이스로
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
		System.out.println("using setTeacher method");
	}
	
}
