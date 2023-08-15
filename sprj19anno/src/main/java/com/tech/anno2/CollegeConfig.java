package com.tech.anno2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan(basePackages = "com.tech.anno2")
@Configuration
public class CollegeConfig {
	
//	@Bean
//	public Teacher mathTeacherBean() {
//		
//		return new MathTeacher();
//	}
//		
//	@Bean
//	public Principal principalBean() {
//		
//		return new Principal();
//	}
//	
//	
////	@Bean(name ="col")
//	@Bean
//	public College college() {
//		
////		return new College(pricipalBean());
//		College college = new College();
//		college.setPrincipal(principalBean());
//		college.setTeacher(mathTeacherBean());
//		
//		return college;
//	}

}
