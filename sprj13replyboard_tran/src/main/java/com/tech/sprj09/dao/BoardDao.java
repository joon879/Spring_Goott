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
//				String sql = "select bid, bname, btitle, bcontent, bdate,"
//						+ "bhit, bgroup, bstep, bindent from replyboard";
				
				
				
				String sql = "select bid,bname,btitle,bcontent,bdate,bhit,"
						+ "bgroup,bstep,bindent from replyboard "
						+ "order by bgroup desc, bstep asc";
				
				
				
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
	
	public BoardDto replyView(String sbid) {
		
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
	
	public void reply(String bid, String bname, String btitle, 
			String bcontent, String bgroup, String bstep, String bindent) {
		
		//2. 댓글을 달기 전 스텝을 검사??
		//댓글을 달려는 원글의 스텝번호보다 큰 스텝(대댓글)이 있다면 1을 더해준다.
		
		
		
		//DB연결 후 insert쿼리 실행
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			
//			커넥션 생성 후 replyShape에 전달
//			con의 autocommit 상태를 false로 변경
			con.setAutoCommit(false);
			int rn1 = replyShape(bgroup, bstep, con);
			int rn2 = 0;
			System.out.println("nr111111111:"+rn1);
			
			
			String sql = "insert into replyboard(bid, bname, btitle, bcontent, bgroup, bstep, bindent)\r\n" + 
					"values(replyboard_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			
			pstmt.setInt(4, Integer.parseInt(bgroup)); //원글의 번호 유지
			pstmt.setInt(5, Integer.parseInt(bstep)+1); //순서 원글+1
			pstmt.setInt(6, Integer.parseInt(bindent)+1); //들여쓰기 원글+1
			
			rn2 = pstmt.executeUpdate();	
			System.out.println("nr2222222222:"+rn2);
//			현재 con의 기본 commit 상태확인
			System.out.println("commit 상태: "+con.getAutoCommit());
			//여기까진 commit 처리가 완전히 안된 상태. false 그대로 나옴.
//			아래 조건문으로 커밋처리 정함
			
			if(rn1>=0 && rn2>=1) {
				con.commit();	
				System.out.println("comittttttttttttt");
//				System.out.println("commit 상태: "+con.getAutoCommit());
			} else {
				con.rollback();
				System.out.println("rollbackkkkkkkkk");
//				System.out.println("commit 상태: "+con.getAutoCommit());
			}
			
			
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
	
	public int replyShape(String bgroup, String bstep, Connection con) {
		
		//DB연결 후 insert쿼리 실행
//		Connection con = null;
		
		
//		**reply에서 전달한 con을 사용: 트랜잭션 처리1
		
		
		PreparedStatement pstmt = null;
		int rn1 = 0;
		try {
//			con = dataSource.getConnection();
			
			//댓글 사이에 댓글 추가되는 공간 만들기
			//스텝을 1증가 시키는 쿼리.
			String sql = "update replyboard "
					+ "set bstep=bstep+1 "
					+ "where bgroup=? and bstep>?";
			con = null;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bgroup));
			pstmt.setInt(2, Integer.parseInt(bstep));
			
			
			rn1 = pstmt.executeUpdate();
			//업데이트된 갯수만큼 rn1에 저장.
			
		} catch (Exception e) {
			return -1;
		}finally {//자원 회수
			try {
				if(pstmt != null) pstmt.close();
//				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return rn1;
	}
	
}
