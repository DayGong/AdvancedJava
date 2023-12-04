package kr.or.ddit.json.service;

import java.util.List;

import kr.or.ddit.json.dao.ILprodDao;
import kr.or.ddit.json.dao.LprodDaoImpl;
import kr.or.ddit.vo.LprodVO;

public class LprodServiceImpl implements ILprodService {
	private ILprodDao dao;
	
	private static LprodServiceImpl service;
	
	private LprodServiceImpl() {
		dao = LprodDaoImpl.getInstance();
	}
	
	public static LprodServiceImpl getInstance() {
		if(service==null) service = new LprodServiceImpl();
		return service;
	}

	@Override
	public List<LprodVO> getAllLprod() {
		return dao.getAllLprod();
	}

}