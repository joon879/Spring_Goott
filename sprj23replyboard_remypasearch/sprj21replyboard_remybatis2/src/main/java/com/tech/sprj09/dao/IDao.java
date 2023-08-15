package com.tech.sprj09.dao;

import java.util.ArrayList;

import com.tech.sprj09.dto.BoardDto;

public interface IDao {
//	public ArrayList<BoardDto> list();
	public ArrayList<BoardDto> list(int start,int end);
	public void write(String bname,String btitle,String bcontent);
	public BoardDto contentView(String sbid );
	public void modify(String sbid,String bname,
			String btitle,String bcontent);
	public void upHit(String sbid);
	public void delete(String sbid );
	public BoardDto replyView(String sbid);
	public void reply(String bid,String bname,String btitle,
			String bcontent,String bgroup,
			String bstep,String bindent);
	public void replyShape(String bgroup,String bstep);
	public int selectBoardTotCount();
}
