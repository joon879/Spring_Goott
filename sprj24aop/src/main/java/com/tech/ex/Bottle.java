package com.tech.ex;

public class Bottle {

	private String name;
	private String kind;
	private int price;
	
	public void getBottle() {
		System.out.println(name+"님 "+kind+"술을 "+price+"원에 마니~ 마셨습니다.");
	}
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
