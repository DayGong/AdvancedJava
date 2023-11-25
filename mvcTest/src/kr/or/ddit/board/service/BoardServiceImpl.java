package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao dao;
	
	private static BoardServiceImpl service;
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if (service == null) {
			service = new BoardServiceImpl();
		}
		
		return service;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		// 게시글 내용을 가져올 때 조회수를 증가하는 작업이 선행되어야하기 떄문에
		// 이 작업을 service의 메서드에서 함께 처리한다.
		int cnt = dao.setCountIncrement(boardNo);
		
		if (cnt == 0) {	return null; }
		
		return dao.getBoard(boardNo);
	}
	
//	@Override
//	public BoardVO selectBoard(String boardNo) {
//		return dao.selectBoard(boardNo);
//	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

//	@Override
//	public int updateCount(Map<String, Integer> cntMap) {
//		return dao.updateCount(cntMap);
//	}
	
	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public List<BoardVO> getSearchBoard(String search) {
		return dao.getSearchBoard(search);
	}
	
}
