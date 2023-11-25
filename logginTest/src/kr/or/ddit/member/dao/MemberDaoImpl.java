package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	private static Logger logger = Logger.getLogger(MemberDaoImpl.class);
	
	// 싱글톤
	// 1번
	private static MemberDaoImpl memDao;
	
	// 2번
	private MemberDaoImpl() {
	}
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if (memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			
			String sql =  " insert into MYMEMBER "
		               + " (MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR) "
		               + " values (?, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());
			
			logger.debug("PreparedStatement객체 생성");
			logger.debug("실행 SQL문 >> " + sql);
			logger.debug("사용 데이터 >> [" + memVo.getMem_id() + 
					", " + memVo.getMem_pass() +", " +memVo.getMem_name()
					+ ", " + memVo.getMem_tel() + ", " +memVo.getMem_addr() + "]");
			
			cnt = pstmt.executeUpdate();
			logger.info("insert 작업 성공");
			
		} catch (SQLException e) {
			logger.error("insert 작업 실패", e);
		} finally {
			if(pstmt != null) try { 
					pstmt.close();
					logger.info("PreparedStatement 객체 반납");
				} catch (SQLException e) {} 
			if(conn != null) try { 
					conn.close();
					logger.info("Connection 객체 반납");
				} catch (SQLException e) {} 
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		// 지역변수로 하는 이유 = 사용할 때마다 닫아주기 때문에 전역변수로 메모리를 남겨줄 필요가 없다고 판단했기 때문
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;	// 반환값 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			
			String sql = " delete from MYMEMBER where MEM_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			logger.debug("PreparedStatement객체 생성");
			logger.debug("실행 SQL문 >> " + sql);
			logger.debug("사용 데이터 >> [" + memId + "]");
			
			cnt = pstmt.executeUpdate();
			logger.info("delete 작업 성공");
			
		} catch (SQLException e) {
			logger.error("delete 작업 실패", e);
		} finally {
			if( pstmt != null ) try { 
					pstmt.close();
					logger.info("PreparedStatement 객체 반납");
				} catch (SQLException e) {}
			if( conn != null ) try { 
					conn.close();
					logger.info("Connection 객체 반납");
				} catch (SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// key값 정보 ==> 회원ID(MEMID), 수정할 컬럼명(FIELD), 수정할 데이터(DATA)
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;	// 반환값 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			
			String sql = "update mymember set " + paramMap.get("FIELD") + " = ? "
			+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("DATA"));
			pstmt.setString(2, paramMap.get("MEMID"));
			
			logger.debug("PreparedStatement객체 생성");
			logger.debug("실행 SQL문 >> " + sql);
			logger.debug("사용 데이터 >> [" + paramMap.get("DATA") + ", " + paramMap.get("MEMID") + "]");
			
			cnt = pstmt.executeUpdate();
			logger.info("update 작업 성공");
			
		} catch (SQLException e) {
			logger.error("update 작업 실패", e);
		} finally {
			if( pstmt != null ) try { 
				pstmt.close();
				logger.info("PreparedStatement 객체 반납");
			} catch (SQLException e) {}
			if( conn != null ) try { 
				conn.close();
				logger.info("Connection 객체 반납");
			} catch (SQLException e) {}
		}
		
		return cnt;
	}
	
	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
	
			String sql = " update MYMEMBER set "
					+ "  MEM_PASS = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? "
		            + " where MEM_ID = ? ";
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_pass());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());
			
			logger.debug("PreparedStatement 객체 생성");
			logger.debug("실행 SQL문 >> " + sql);
			logger.debug("사용 데이터 >> [" + memVo.getMem_pass() + ", " + memVo.getMem_name()
						+ ", " + memVo.getMem_tel() + memVo.getMem_addr() + memVo.getMem_id() + "]");
			
			cnt = pstmt.executeUpdate();
			logger.info("update 작업 성공");
			
		} catch (SQLException e) {
			logger.error("update 작업 실패", e);
		} finally {
			if ( rs != null ) try { 
				rs.close();
				logger.info("ResultSet 객체 반납");
			} catch ( SQLException e ) {}
 			if ( pstmt != null ) try { 
 				pstmt.close();
 				logger.info("PreparedStatement 객체 반납");
 			} catch ( SQLException e ) {}
			if ( conn != null ) try { 
				conn.close();
				logger.info("Connection 객체 반납");
			} catch ( SQLException e ) {}
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = new ArrayList<MemberVO>(); // 반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			
			String sql = " select * from MYMEMBER ";
			pstmt = conn.prepareStatement(sql);
			
			logger.debug("PreparedStatement 객체 생성");
			logger.debug("실행 SQL문 >> " + sql);
			
			rs = pstmt.executeQuery();
			logger.info("select 작업 성공");
			
			while(rs.next()) {
				// 1개의 레코드가 저장될 변수 (MemberVO객체 변수) 생성
				MemberVO memVo = new MemberVO();
				memVo.setMem_id(rs.getString("MEM_ID"));
				memVo.setMem_pass(rs.getString("MEM_PASS"));
				memVo.setMem_name(rs.getString("MEM_NAME"));
				memVo.setMem_tel(rs.getString("MEM_TEL"));
				memVo.setMem_addr(rs.getString("MEM_ADDR"));
				memList.add(memVo);	// List에 VO객체 추가하기
			}
			
			logger.info("List에 VO객체 추가 작업 성공");
			
		} catch (SQLException e) {
			logger.error("select 작업 실패", e);
		} finally {
			if ( rs != null ) try { 
				rs.close();
				logger.info("ResultSet 객체 반납");
			} catch ( SQLException e ) {}
 			if ( pstmt != null ) try { 
 				pstmt.close();
 				logger.info("PreparedStatement 객체 반납");
 			} catch ( SQLException e ) {}
			if ( conn != null ) try { 
				conn.close();
				logger.info("Connection 객체 반납");
			} catch ( SQLException e ) {}
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		int count = 0;	// 반환값이 저장될 객체
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");

			String sql = " select count(*) CNT from MYMEMBER "
					+ "where MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			logger.debug("PreparedStatement 객체 생성");
			logger.debug("실행 SQL문 >> " + sql);
			logger.debug("사용 데이터 >> [" + memId + "]");

			rs = pstmt.executeQuery();
			logger.info("select 작업 성공");
			
			if (rs.next()) {
				count = rs.getInt("CNT");
			}
			
		} catch (SQLException e) {
			logger.error("select 작업 실패", e);
		} finally {
			if ( rs != null ) try { 
				rs.close();
				logger.info("ResultSet 객체 반납");
			} catch ( SQLException e ) {}
 			if ( pstmt != null ) try { 
 				pstmt.close();
 				logger.info("PreparedStatement 객체 반납");
 			} catch ( SQLException e ) {}
			if ( conn != null ) try { 
				conn.close();
				logger.info("Connection 객체 반납");
			} catch ( SQLException e ) {}
		}
		
		return count;
	}
	
	@Override
	public MemberVO selectMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVO memVo = new MemberVO();
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");

			String sql = " select * from MYMEMBER "
					+ "where MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			logger.debug("PreparedStatement 객체 생성");
			logger.debug("실행 SQL문 >> " + sql);
			logger.debug("사용 데이터 >> [" + memId + "]");

			rs = pstmt.executeQuery();
			logger.info("select 작업 성공");
			
			if (rs.next()) {
				memVo.setMem_id(rs.getString("MEM_ID"));
				memVo.setMem_pass(rs.getString("MEM_PASS"));
				memVo.setMem_name(rs.getString("MEM_NAME"));
				memVo.setMem_tel(rs.getString("MEM_TEL"));
				memVo.setMem_addr(rs.getString("MEM_ADDR"));
			}
			
		} catch (SQLException e) {
			logger.error("select 작업 실패", e);
		} finally {
			if ( rs != null ) try { 
				rs.close();
				logger.info("ResultSet 객체 반납");
			} catch ( SQLException e ) {}
 			if ( pstmt != null ) try { 
 				pstmt.close();
 				logger.info("PreparedStatement 객체 반납");
 			} catch ( SQLException e ) {}
			if ( conn != null ) try { 
				conn.close();
				logger.info("Connection 객체 반납");
			} catch ( SQLException e ) {}
		}
		
		return memVo;
	}

}
