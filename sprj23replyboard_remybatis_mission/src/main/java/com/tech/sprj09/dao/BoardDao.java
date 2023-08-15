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
	
//	마이바티스로!!
//	public BoardDao() {//생성자
//		System.out.println("hihi");
//		
////		DB 접속
//		try {
//			Context context = new InitialContext();
//			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/springxe");
//			System.out.println("DB Connect!!");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
	public ArrayList<BoardDto> list() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDto> dtos2 = new ArrayList<BoardDto>();
				
		
		try {
				con = dataSource.getConnection(); //dataSource에 이미 커넥션 세팅이 된걸 들고옴
//				String sql = "select bid, bname, btitle, bcontent, bdate,"
//						+ "bhit, bgroup, bstep, bindent from replyboard";
				
				
				
				String sql = "select brd_id,brd_name,brd_title,brd_content,brd_date,brd_hit,"
						+ "brd_group,brd_step,brd_indent from replyboard2 "
						+ "order by brd_group desc, brd_step asc";
				
				
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
	
				//dtos2에 글 전체를 담아주기
				while (rs.next()) {
					int brd_id = rs.getInt("brd_id");
					String brd_name = rs.getString("brd_name");
					String brd_title = rs.getString("brd_title");
					String brd_content = rs.getString("brd_content");
					Timestamp brd_date = rs.getTimestamp("brd_date");
					int brd_hit = rs.getInt("brd_hit");
					int brd_group = rs.getInt("brd_group");
					int brd_step = rs.getInt("brd_step");
					int brd_indent = rs.getInt("brd_indent");
					
				//생성자를 통해 BoardDto에 담아주기
				BoardDto dto = new BoardDto(brd_id, brd_name,
						brd_title, brd_content, brd_date, brd_hit,
						brd_group, brd_step, brd_indent);
				
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
	
	public void write(String brd_name, String brd_title, String brd_content) {
		//DB연결 후 insert쿼리 실행
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "insert into replyboard2 values(replyboard2_seq.nextval,"
					+ " ?, ?, ?, sysdate, 0,"
					+ " replyboard2_seq.currval, 0, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, brd_name);
			pstmt.setString(2, brd_title);
			pstmt.setString(3, brd_content);
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
	
	public BoardDto contentView(String sbrd_id) {
		
		//조회수 증가
		upHit(sbrd_id);
		
		
		//sbid를 조건으로 DB에서 해당 글을 조회(가져오기)
		BoardDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "select brd_id, brd_name, brd_title, brd_content, brd_date,"
					+"brd_hit, brd_group, brd_step, brd_indent "
					+"from replyboard2 where brd_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbrd_id));
			rs = pstmt.executeQuery();
			
			//rs를 dto에 담아주기
			if(rs.next()) {
				int brd_id = rs.getInt("brd_id");
				String brd_name = rs.getString("brd_name");
				String brd_title = rs.getString("brd_title");
				String brd_content = rs.getString("brd_content");
				Timestamp brd_date = rs.getTimestamp("brd_date");
				int brd_hit = rs.getInt("brd_hit");
				int brd_group = rs.getInt("brd_group");
				int brd_step = rs.getInt("brd_step");
				int brd_indent = rs.getInt("brd_indent");
				
				dto = new BoardDto(brd_id, brd_name, brd_title,
						brd_content, brd_date, brd_hit, brd_group,
						brd_step, brd_indent);
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
	
	public void upHit(String sbrd_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "update replyboard2 set "
					+ "brd_hit=brd_hit+1 where brd_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbrd_id));
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
	
	
	
	
	public void modify(String sbrd_id, String brd_name, 
			String brd_title, String brd_content) {
		//sbid를 조건으로 DB에서 해당 글을 수정
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "update replyboard2 "
					+ "set brd_name=?, brd_title=?,"
					+ " brd_content=? where brd_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, brd_name);
			pstmt.setString(2, brd_title);
			pstmt.setString(3, brd_content);
			pstmt.setInt(4, Integer.parseInt(sbrd_id));
			
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
	
	public void delete(String sbrd_id) {
		//sbid를 조건으로 DB에서 해당 글을 삭제
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "delete from replyboard2 where bidrd_ = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbrd_id));
			
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
	
	public BoardDto replyView(String sbrd_id) {
		
		//sbid를 조건으로 DB에서 해당 글을 조회(가져오기)
		BoardDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "select brd_id, brd_name, brd_title, brd_content, brd_date,"
					+"brd_hit, brd_group, brd_step, brd_indent "
					+"from replyboard2 where brd_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbrd_id));
			rs = pstmt.executeQuery();
			
			//rs를 dto에 담아주기
			if(rs.next()) {
				int brd_id = rs.getInt("brd_id");
				String brd_name = rs.getString("brd_name");
				String brd_title = rs.getString("brd_title");
				String brd_content = rs.getString("brd_content");
				Timestamp brd_date = rs.getTimestamp("brd_date");
				int brd_hit = rs.getInt("brd_hit");
				int brd_group = rs.getInt("brd_group");
				int brd_step = rs.getInt("brd_step");
				int brd_indent = rs.getInt("brd_indent");
				
				dto = new BoardDto(brd_id, brd_name, brd_title,
						brd_content, brd_date, brd_hit, brd_group,
						brd_step, brd_indent);
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
	
	public void reply(String brd_id, String brd_name, String brd_title, 
			String brd_content, String brd_group, String brd_step, String brd_indent) {
		
		//2. 댓글을 달기 전 스텝을 검사??
		//댓글을 달려는 원글의 스텝번호보다 큰 스텝(대댓글)이 있다면 1을 더해준다.
		replyShape(brd_group, brd_step);
		
		
		//DB연결 후 insert쿼리 실행
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "insert into replyboard2(brd_id, brd_name, brd_title, brd_content, brd_group, brd_step, brd_indent) " + 
					"values(replyboard2_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, brd_name);
			pstmt.setString(2, brd_title);
			pstmt.setString(3, brd_content);
			
			pstmt.setInt(4, Integer.parseInt(brd_group)); //원글의 번호 유지
			pstmt.setInt(5, Integer.parseInt(brd_step)+1); //순서 원글+1
			pstmt.setInt(6, Integer.parseInt(brd_indent)+1); //들여쓰기 원글+1
			
			pstmt.executeUpdate();			
			
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
	
	public void replyShape(String brd_group, String brd_step) {
		
		//DB연결 후 쿼리 실행
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			
			//댓글 사이에 댓글 추가되는 공간 만들기
			//스텝을 1증가 시키는 쿼리.
			String sql = "update replyboard2 "
					+ "set brd_step=brd_step+1 "
					+ "where brd_group=? and brd_step>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(brd_group));
			pstmt.setInt(2, Integer.parseInt(brd_step));
						
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
