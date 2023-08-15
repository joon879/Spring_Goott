package com.tech.sprj11p.dao;

import java.util.ArrayList;

import com.tech.sprj11p.dto.PizzaDto;

public interface IDao {

	public ArrayList<PizzaDto> list();
	public void write(String pzname, String pzsubj, String pzcontent);
	public PizzaDto pizzaContentView(String spzid);
	public void upHit(String spzid);
	public void modify(String spzid, String pzname, String pzsubj, String pzcontent);
	public void delete(String spzid);
	public PizzaDto replyView(String spzid);
	public void reply(String pzid, String pzname, String pzsubj,
			String pzcontent, String pzgroup, String pzstep, String pzintent);
	public void replyShape(String pzgroup, String pzstep);
	
	
}
