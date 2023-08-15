package com.tech.anno2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tech.anno3.College;

@Configuration
public class CollegeConfig {
	
//	@Bean(name ="col")
	@Bean
	public College college() {
		
		return new College();
	}

}
