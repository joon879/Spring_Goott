package com.tech.sprj09.dao;

import java.util.ArrayList;

import com.tech.sprj09.dto.BoardDto;

public interface IDao {

//	public ArrayList<BoardDto> list();
//	public ArrayList<BoardDto> list(int start, int end);
	public ArrayList<BoardDto> list(int start, int end, String sk, String selNum);
	
	public void write(String brd_name, String brd_title, String brd_content);	
	public BoardDto contentView(String brd_id);
	public void upHit(String sbrd_id);
	public void modify(String sbrd_id, String brd_name, String brd_title, String brd_content);
	public void delete(String sbrd_id);
	public BoardDto replyView(String sbrd_id);
	public void reply(String brd_id, String brd_name, String brd_title, 
			String bcontent, String brd_group, String brd_step, String brd_indent);
	public void replyShape(String brd_group, String brd_step);
	
//	public int selectBoardTotCount();
	public int selectBoardTotCount1(String searchKeyword);
	public int selectBoardTotCount2(String searchKeyword);
	public int selectBoardTotCount3(String searchKeyword);
	public int selectBoardTotCount4(String searchKeyword);
	
}
