package com.tech.ex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
//	bean 등록
	@Bean
	public Student student1() {
		Student student = new Student("홍길동", "20");
		student.setGradeNum("5학년");
		student.setClassNum("computer");
				
		return student;
	}
	
	@Bean
	public Student student2() {
		Student student = new Student("홍길순", "19");
		student.setGradeNum("4학년");
		student.setClassNum("python");
		
		return student;
	}

}
