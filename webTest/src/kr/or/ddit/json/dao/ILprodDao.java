package kr.or.ddit.json.dao;

import java.util.List;

import kr.or.ddit.vo.LprodVO;

public interface ILprodDao {

	/**
	 * Lprod테이블의 전체 데이터를 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @return LprodVO가 저장된 List객체
	 */
	public List<LprodVO> getAllLprod();
	
}
