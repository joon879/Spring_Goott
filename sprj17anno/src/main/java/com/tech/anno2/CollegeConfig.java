package com.tech.anno2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollegeConfig {
	
	@Bean
	public Principal principalBean() {
		
		return new Principal();
	}
	
	
//	@Bean(name ="col")
	@Bean
	public College college() {
		
//		return new College(pricipalBean());
		College college = new College();
		college.setPrincipal(principalBean());
		
		return college;
	}

}
