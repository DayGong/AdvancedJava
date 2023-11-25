package kr.or.ddit.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {	
	// 싱글톤
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() {}
	
	public static BoardDaoImpl getInstance() {
		if (dao == null) {	dao = new BoardDaoImpl();	}
		
		return dao;
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0;	// 반환값이 저장될 변수 선언
		
		try {
			session = MybatisUtil.getSqlSession();
			
			BoardVO newBoardVo = new BoardVO();
			
			newBoardVo.setBoard_title(boardVo.getBoard_title());
			newBoardVo.setBoard_writer(boardVo.getBoard_writer());
			newBoardVo.setBoard_content(boardVo.getBoard_content());
			
			cnt = session.insert("board.insertBoard", newBoardVo);
			
			if (cnt > 0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.delete("board.deleteBoard", boardNo);
			
			if (cnt > 0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("board.updateBoard", boardVo);

			if (cnt > 0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return cnt;
	}
	
	@Override
	public int setCountIncrement(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("board.updateCntBoard", boardNo);

			if (cnt > 0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		SqlSession session = null;
		List<BoardVO> boardList= new ArrayList<BoardVO>();
		
		try {
			session = MybatisUtil.getSqlSession();
			
			boardList = session.selectList("board.getAllBoard");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return boardList;
	}
	
	@Override
	public BoardVO getBoard(int boardNo) {
		SqlSession session = null;
		BoardVO boardVo = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			boardVo = session.selectOne("board.getOneBoard", boardNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return boardVo;
	}

	@Override
	public List<BoardVO> getSearchBoard(String search) {
		SqlSession session = null;
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			session = MybatisUtil.getSqlSession();
			
			boardList = session.selectList("board.getSearchBoard", search);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return boardList;
	}
}
