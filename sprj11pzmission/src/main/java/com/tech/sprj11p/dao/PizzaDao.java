package com.tech.sprj11p.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tech.sprj11p.dto.PizzaDto;

public class PizzaDao {

	DataSource dataSource;
	
	public PizzaDao() {
		System.out.println("생성자 ㄱㄱ");
		
		//DB 접속
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/springxe");
			System.out.println("DB Connect!!!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<PizzaDto> list() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PizzaDto> dtos2 = new ArrayList<PizzaDto>();
		
		try {
			con = dataSource.getConnection();
			String sql = "select pzid, pzname, pzsubj, pzcontent, pzdate,"
					+ " pzhit, pzgroup, pzstep, pzintent from pz_board "
					+ "order by pzgroup desc, pzstep asc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//dtos2에 글 전체 담기
			while (rs.next()) {
				int pzid = rs.getInt("pzid");
				String pzname = rs.getString("pzname");
				String pzsubj = rs.getString("pzsubj");
				String pzcontent = rs.getString("pzcontent");
				Timestamp pzdate = rs.getTimestamp("pzdate");
				int pzhit = rs.getInt("pzhit");
				int pzgroup = rs.getInt("pzgroup");
				int pzstep = rs.getInt("pzstep");
				int pzintent = rs.getInt("pzintent");
				
			//생성자를 통해 PizzaDto에 담아주기
			PizzaDto dto = new PizzaDto(pzid, pzname, pzsubj, pzcontent,
					pzdate,	pzhit, pzgroup, pzstep, pzintent);
			
			dtos2.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	
	
	public void write(String pzname, String pzsubj, String pzcontent) {
		//DB연결 후 insert쿼리 실행
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "insert into pz_board values(pz_board_seq.nextval,"
					+ " ?, ?, ?, sysdate, 0,"
					+ " pz_board_seq.currval, 0, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pzname);
			pstmt.setString(2, pzsubj);
			pstmt.setString(3, pzcontent);
			int rn = pstmt.executeUpdate();
			System.out.println("insert cnt: "+rn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}	
	}
	
	public PizzaDto pizzaContentView(String spzid) {
		
		//조회수 증가
		upHit(spzid);
		
		
		//pzid를 조건으로 DB에서 해당 글을 조회(가져오기)
		PizzaDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "select pzid, pzname, pzsubj, pzcontent, pzdate,"
					+ " pzhit, pzgroup, pzstep, pzintent"
					+ " from pz_board where pzid=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(spzid));
			rs = pstmt.executeQuery();
			
			//rs를 dto에 담아주기
			if(rs.next()) {
				int pzid = rs.getInt("pzid");
				String pzname = rs.getString("pzname");
				String pzsubj = rs.getString("pzsubj");
				String pzcontent = rs.getString("pzcontent");
				Timestamp pzdate = rs.getTimestamp("pzdate");
				int pzhit = rs.getInt("pzhit");
				int pzgroup = rs.getInt("pzgroup");
				int pzstep = rs.getInt("pzstep");
				int pzintent = rs.getInt("pzintent");
				
				dto = new PizzaDto(pzid, pzname, pzsubj,
						pzcontent, pzdate, pzhit, pzgroup,
						pzstep, pzintent);
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	
	public void upHit(String spzid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "Update pz_board "
					+ "set pzhit=pzhit+1 where pzid=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(spzid));
			pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public void modify(String spzid, String pzname,
			String pzsubj, String pzcontent) {
		//spzid를 조건으로 DB에서 해당 글을 수정
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "update pz_board "
					+ "set pzname=?, pzsubj=?, "
					+ "pzcontent=? where pzid=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pzname);
			pstmt.setString(2, pzsubj);
			pstmt.setString(3, pzcontent);
			pstmt.setInt(4, Integer.parseInt(spzid));
			
			//업데이트 실행
			pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				
			}
		}
	}
	
	public void delete(String spzid) {
		//spzid를 조건으로 DB에서 해당 글을 삭제
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "delete from pz_board where pzid = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(spzid));
			
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

	public PizzaDto replyView(String spzid) {
		
		//spzid를 조건으로 DB에서 해당 글을 조회(가져오기)
		PizzaDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "select pzid, pzname, pzsubj, pzcontent, pzdate,"
					+ "pzhit, pzgroup, pzstep, pzintent "
					+ "from pz_board where pzid = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(spzid));
			rs = pstmt.executeQuery();
			
			//rs를 dto에 담아주기
			if(rs.next()) {
				int pzid = rs.getInt("pzid");
				String pzname = rs.getString("pzname");
				String pzsubj = rs.getString("pzsubj");
				String pzcontent = rs.getString("pzcontent");
				Timestamp pzdate = rs.getTimestamp("pzdate");
				int pzhit = rs.getInt("pzhit");
				int pzgroup = rs.getInt("pzgroup");
				int pzstep = rs.getInt("pzstep");
				int pzintent = rs.getInt("pzintent");
				
				dto = new PizzaDto(pzid, pzname, pzsubj,
						pzcontent, pzdate, pzhit, pzgroup,
						pzstep, pzintent);
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
	
	public void reply(String pzid, String pzname, String pzsubj,
			String pzcontent, String pzgroup, String pzstep, String pzintent) {
		
		
		//DB연결 후 insert쿼리 실행
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "insert into pz_board(pzid, pzname, pzsubj, pzcontent, pzgroup, pzstep, pzintent) "+ 
			"values(pz_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pzname);
			pstmt.setString(2, pzsubj);
			pstmt.setString(3, pzcontent);
			
			pstmt.setInt(4, Integer.parseInt(pzgroup)); //원글의 번호 유지
			pstmt.setInt(5, Integer.parseInt(pzstep)+1); //순서 원글+1
			pstmt.setInt(6, Integer.parseInt(pzintent)+1); //들여쓰기 원글+1
			
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
	
	public void replyShape(String pzgroup, String pzstep) {
		
		//DB연결 후 쿼리 실행
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			
			//댓글 사이에 댓글 추가되는 공간을 만들어서 대댓글을 넣는다.
			String sql = "update pz_board "
					+ "set pzstep=pzstep+1 "
					+ "where pzgroup=? and pzstep>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(pzgroup));
			pstmt.setInt(2, Integer.parseInt(pzstep));
						
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
