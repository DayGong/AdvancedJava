package kr.or.ddit.json.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

public class LprodDaoImpl implements ILprodDao {
	private static LprodDaoImpl dao;
	
	private LprodDaoImpl() { }
	
	public static LprodDaoImpl getInstance() {
		if(dao==null) dao = new LprodDaoImpl();
		return dao;
	}
	
	
	@Override
	public List<LprodVO> getAllLprod() {
		List<LprodVO> lprodList = null; // 반환값이 저장될 변수 선언
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			
			lprodList = session.selectList("lprod.getAllLprod");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return lprodList;
	}

}








