package com.tech.anno2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	
	public static void main(String[] args) {
		
//		ApplicationContext context = 
//				new ClassPathXmlApplicationContext("com/tech/anno2/beans.xml");
		
		ApplicationContext context =
				new AnnotationConfigApplicationContext(CollegeConfig.class);
		System.out.println("beans.xml file loaded");
		
		
		College college = context.getBean("college", College.class);
		//getBean("XXX",sss); XXX가 Bean 이름이다.		
		System.out.println("This College obj by spring is: "+college);
		
		
		college.test();
		
	}
}
