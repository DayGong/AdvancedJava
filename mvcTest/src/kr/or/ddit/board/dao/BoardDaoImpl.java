package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	// DB연동용 객체 변수 선언
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	//------------------------------------------------------
	
	// 싱글톤
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() {}
	
	public static BoardDaoImpl getInstance() {
		if (dao == null) {	dao = new BoardDaoImpl();	}
		
		return dao;
	}
	
	// 자원 반납용 메서드
	private void disConnect() {
		if(rs != null) try { rs.close(); } catch(SQLException e) {};
		if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {};
		if(conn != null) try { conn.close(); } catch(SQLException e) {};
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
		int cnt = 0;	// 반환값이 저장될 변수 선언
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "insert into JDBC_BOARD "
					+ " (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT, BOARD_CONTENT) "
					+ " values (board_seq.nextVal, ?, ?, SYSDATE, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
		} finally {
//			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {} 
//			if(conn != null) try { conn.close(); } catch (SQLException e) {}
			disConnect();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = " delete from JDBC_BOARD "
					+ " where BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
		} finally {
// 			if ( pstmt != null ) try { pstmt.close(); } catch ( SQLException e ) {}
//			if ( conn != null ) try { conn.close(); } catch ( SQLException e ) {}
			disConnect();
		}
		
		return res;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = " update JDBC_BOARD set "
					+ " BOARD_TITLE = ?, BOARD_CONTENT = ? "
					+ " BOARD_DATE = SYSDATE "
					+ " where BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_no());
			
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
		} finally {
// 			if ( pstmt != null ) try { pstmt.close(); } catch ( SQLException e ) {}
//			if ( conn != null ) try { conn.close(); } catch ( SQLException e ) {}
			disConnect();
		}
		
		return res;
	}
	
	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = " UPDATE jdbc_board SET board_cnt = board_cnt+1"
					+ " WHERE board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return cnt;
	}
	
//	public int updateCount(Map<String, Integer> cntMap) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = DBUtil3.getConnection();
//			
//			String sql = " update JDBC_BOARD set BOARD_CNT = ? "
//					+ " where BOARD_NO = ? ";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, cntMap.get("BOARD_CNT"));
//			pstmt.setInt(2, cntMap.get("BOARD_NO"));
//			
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//		} finally {
//			if( pstmt != null ) try { pstmt.close(); } catch (SQLException e) {}
//			if( conn != null ) try { conn.close(); } catch (SQLException e) {}
//		}
//		return 0;
//	}

	@Override
	public List<BoardVO> getAllBoard() {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		List<BoardVO> boardList= new ArrayList<BoardVO>();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = " select BOARD_NO, BOARD_TITLE, BOARD_WRITER, "
					+ " to_char(BOARD_DATE, 'yyyy-MM-dd') BOARD_DATE,"
					+ " BOARD_CNT, BOARD_CONTENT "
					+ " from JDBC_BOARD "
					+ " order by BOARD_NO desc";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 하나의 레코드를 VO객체에 저장한 후 이 VO객체를 List에 추가한다.
				BoardVO boardVo = new BoardVO();
				boardVo.setBoard_no(rs.getInt("BOARD_NO"));
				boardVo.setBoard_title(rs.getString("BOARD_TITLE"));
				boardVo.setBoard_writer(rs.getString("BOARD_WRITER"));
				boardVo.setBoard_date(rs.getString("BOARD_DATE"));
				boardVo.setBoard_cnt(rs.getInt("BOARD_CNT"));
				boardVo.setBoard_content(rs.getString("BOARD_CONTENT"));
				
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			if ( rs != null ) try { rs.close(); } catch ( SQLException e ) {}
//			if ( pstmt != null ) try { pstmt.close(); } catch ( SQLException e ) {}
//			if ( conn != null ) try { conn.close(); } catch ( SQLException e ) {}
			disConnect();
		}
		return boardList;
	}
	
	@Override
	public BoardVO getBoard(int boardNo) {
		BoardVO boardVo = null;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = " select BOARD_NO, BOARD_TITLE, BOARD_WRITER, "
					+ " to_char(BOARD_DATE, 'yyyy-MM-dd') BOARD_DATE,"
					+ " BOARD_CNT, BOARD_CONTENT "
					+ " from JDBC_BOARD "
					+ " where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				boardVo = new BoardVO();
				boardVo.setBoard_no(rs.getInt("BOARD_NO"));
				boardVo.setBoard_title(rs.getString("BOARD_TITLE"));
				boardVo.setBoard_writer(rs.getString("BOARD_WRITER"));
				boardVo.setBoard_date(rs.getString("BOARD_DATE"));
				boardVo.setBoard_cnt(rs.getInt("BOARD_CNT"));
				boardVo.setBoard_content(rs.getString("BOARD_CONTENT"));
			}
			
		} catch (SQLException e) {
		} finally {
			disConnect();
		}
		
		return boardVo;
	}

//	@Override
//	public BoardVO selectBoard(String boardNo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		BoardVO boardVo = null;
//		
//		try {
//			conn = DBUtil3.getConnection();
//			
//			String sql = " select * from JDBC_BOARD "
//					+ " where BOARD_NO = ?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, boardNo);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				boardVo = new BoardVO(); // 데이터가 없으면 null값을 반환하기 위해서 안쪽에 써줌
//				boardVo.setBoard_no(rs.getInt("BOARD_NO"));
//				boardVo.setBoard_title(rs.getString("BOARD_TITLE"));
//				boardVo.setBoard_writer(rs.getString("BOARD_WRITER"));
////				boardVo.setBoard_date(rs.getDate("BOARD_DATE"));
//				boardVo.setBoard_date(rs.getString("BOARD_DATE"));
//				boardVo.setBoard_cnt(rs.getInt("BOARD_CNT") + 1);
//				boardVo.setBoard_content(rs.getString("BOARD_CONTENT"));
//			}
//			
//			Map<String, Integer> cntMap = new HashMap<String, Integer>();
//			cntMap.put("BOARD_NO", rs.getInt("BOARD_NO"));
//			cntMap.put("BOARD_CNT", rs.getInt("BOARD_CNT") + 1);
//			
//			if (rs != null) {
//				updateCount(cntMap);
//			}
//			
//		} catch (SQLException e) {
//		} finally {
//			if ( rs != null ) try { rs.close(); } catch (SQLException e) {}
//			if ( pstmt != null ) try { pstmt.close(); } catch ( SQLException e ) {}
//			if ( conn != null ) try { conn.close(); } catch ( SQLException e ) {}
//		}
//		
//		return boardVo;
//	}

	@Override
	public List<BoardVO> getSearchBoard(String search) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = DBUtil3.getConnection();
			
//			String sql = " select * from JDBC_BOARD "
//					+ " where BOARD_TITLE LIKE '%" + search +"%' ";
			
			String sql = " select BOARD_NO, BOARD_TITLE, BOARD_WRITER, "
					+ " to_char(BOARD_DATE, 'yyyy-MM-dd') BOARD_DATE,"
					+ " BOARD_CNT, BOARD_CONTENT "
					+ " from JDBC_BOARD "
					+ " WHERE board_title LIKE '%' || ? || '%' "
					+ " order by BOARD_NO desc";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO boardVo = new BoardVO();
				boardVo.setBoard_no(rs.getInt("BOARD_NO"));
				boardVo.setBoard_title(rs.getString("BOARD_TITLE"));
				boardVo.setBoard_writer(rs.getString("BOARD_WRITER"));
				boardVo.setBoard_cnt(rs.getInt("BOARD_CNT"));
				boardVo.setBoard_content(rs.getString("BOARD_CONTENT"));
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( rs != null ) try { rs.close(); } catch ( SQLException e ) {}
			if ( pstmt != null ) try { pstmt.close(); } catch ( SQLException e ) {}
			if ( conn != null ) try { conn.close(); } catch ( SQLException e ) {}
		}
		
		return boardList;
	}
}
