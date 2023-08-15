package com.tech.aop3;

public class Cats {
	
	private String name;
	private int age;
	private String color;
	
	public void getCatsInfo() {
		System.out.println("========================");
		System.out.println("이름: "+getName());
		System.out.println("나이: "+getAge());
		System.out.println("색상: "+getColor());
		System.out.println("========================");
	}
	
	public void getColorChar() {
		if(name.equals("호랑이")) {
			System.out.println("=======================");
			System.out.println("이녀석 성격이 용맹하다.");
			System.out.println("=======================");
		} else {
			System.out.println("=======================");
			System.out.println("이녀석 성격이 온순하다.");
			System.out.println("=======================");
		}
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
