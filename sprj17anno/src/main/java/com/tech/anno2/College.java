package com.tech.anno2;

public class College {
	
	private Principal principal;
	
	
	//생성자에서 principal을 받아와서 College에 주입 시켜둠
//	public College(Principal principal) {
//
//		this.principal =principal;
//	}

	public void test() {
		
		principal.principalInfo();
		System.out.println("testing is call methods");
	}

	//세터로 의존성 주입??
	public void setPrincipal(Principal principal) {
		
		this.principal = principal;
	}
	
	
}
