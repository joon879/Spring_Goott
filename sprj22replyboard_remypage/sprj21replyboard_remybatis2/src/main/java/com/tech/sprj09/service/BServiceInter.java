package com.tech.sprj09.service;

import org.springframework.ui.Model;

import com.tech.sprj09.dao.IDao;

public interface BServiceInter {
	public void execute(Model model,IDao dao);

}
