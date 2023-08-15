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
		}finally {//자원 회수
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return dtos2;
		
	}
	
	public void write(String bname, String btitle, String bcontent) {
		//DB연결 후 insert쿼리 실행
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "insert into replyboard values(replyboard_seq.nextval,"
					+ " ?, ?, ?, sysdate, 0,"
					+ " replyboard_seq.currval, 0, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			int rn = pstmt.executeUpdate();
			System.out.println("insert cnt: "+rn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {//자원 회수
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public BoardDto contentView(String sbid) {
		
		//조회수 증가
		upHit(sbid);
		
		
		
		//sbid를 조건으로 DB에서 해당 글을 조회(가져오기)
		BoardDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "select bid, bname, btitle, bcontent, bdate,"
					+"bhit, bgroup, bstep, bindent "
					+"from replyboard where bid=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbid));
			rs = pstmt.executeQuery();
			
			//rs를 dto에 담아주기
			if(rs.next()) {
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");
				
				dto = new BoardDto(bid, bname, btitle,
						bcontent, bdate, bhit, bgroup,
						bstep, bindent);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {//자원 회수
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		return dto;
	}
	
	public void upHit(String sbid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "update replyboard set "
					+ "bhit=bhit+1 where bid=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbid));
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {//자원 회수
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
	
	
	
	public void modify(String sbid, String bname, 
			String btitle, String bcontent) {
		//sbid를 조건으로 DB에서 해당 글을 수정
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "update replyboard "
					+ "set bname=?, btitle=?,"
					+ " bcontent=? where bid=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setInt(4, Integer.parseInt(sbid));
			
			//업데이트 실행.
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {//자원 회수
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public void delete(String sbid) {
		//sbid를 조건으로 DB에서 해당 글을 삭제
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "delete from replyboard where bid = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbid));
			
//			삭제 실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {//자원 회수
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
}
