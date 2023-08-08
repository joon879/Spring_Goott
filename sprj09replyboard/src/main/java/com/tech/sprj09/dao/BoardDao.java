package com.tech.sprj09.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tech.sprj09.dto.BoardDto;

public class BoardDao {
	
	DataSource dataSource;
	

	public BoardDao() {//생성자
		System.out.println("hihi");
		
//		DB 접속
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/springxe");
			System.out.println("DB Connect!!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public ArrayList<BoardDto> list() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDto> dtos2 = new ArrayList<BoardDto>();
				
		
		try {
				con = dataSource.getConnection(); //dataSource에 이미 커넥션 세팅이 된걸 들고옴
				String sql = "select bid, bname, btitle, bcontent, bdate,"
						+ "bhit, bgroup, bstep, bindent from replyboard";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
	
				//dtos2에 글 전체를 담아주기
				while (rs.next()) {
					int bid = rs.getInt("bid");
					String bname = rs.getString("bname");
					String btitle = rs.getString("btitle");
					String bcontent = rs.getString("bcontent");
					Timestamp bdate = rs.getTimestamp("bdate");
					int bhit = rs.getInt("bhit");
					int bgroup = rs.getInt("bgroup");
					int bstep = rs.getInt("bstep");
					int bindent = rs.getInt("bindent");
					
				//생성자를 통해 BoardDto에 담아주기
				BoardDto dto = new BoardDto(bid, bname,
						btitle, bcontent, bdate, bhit,
						bgroup, bstep, bindent);
				
				//리스트에 담기
				dtos2.add(dto);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dtos2;
		
	}
	
}
