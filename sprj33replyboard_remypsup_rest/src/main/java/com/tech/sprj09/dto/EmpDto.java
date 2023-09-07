package com.tech.sprj09.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class EmpDto {

	private int empno;
	private String ename;
	private String job;
	@JsonFormat(pattern="yy-MM-dd")
	private Date hiredate;
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
	
	
	
}
