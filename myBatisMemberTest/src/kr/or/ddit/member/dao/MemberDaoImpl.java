package kr.or.ddit.member.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	
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
		SqlSession session = null;
		int cnt = 0; // 반환값이 저장될 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			
			MemberVO memberVo = new MemberVO();
			memberVo.setMem_id(memVo.getMem_id());
			memberVo.setMem_pass(memVo.getMem_pass());
			memberVo.setMem_name(memVo.getMem_name());
			memberVo.setMem_tel(memVo.getMem_tel());
			memberVo.setMem_addr(memVo.getMem_addr());
			
			cnt = session.insert("member.insertMember", memberVo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlSession session = null;
		int cnt = 0;	// 반환값 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.delete("member.deleteMember", memId);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return cnt;
	}
	
	@Override
	public int updateMember2(Map<String, String> paramMap) {
		SqlSession session = null;
		int cnt = 0;	// 반환값 변수
		
		// key값 정보 ==> 회원ID(MEMID), 수정할 컬럼명(FIELD), 수정할 데이터(DATA)
		try {
			
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("member.updateMember2", paramMap);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return cnt;
	}
	
	@Override
	public int updateMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;	// 반환값 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("member.updateMember", memVo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;
		List<MemberVO> memList = new ArrayList<MemberVO>(); // 반환값이 저장될 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			
			memList = session.selectList("member.getAllMember");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		int count = 0;	// 반환값이 저장될 객체

		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			
			count = session.selectOne("member.getDupliMemberCnt", memId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return count;
	}
	
	@Override
	public MemberVO selectMember(String memId) {
		MemberVO memVo = new MemberVO();

		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			
			memVo = session.selectOne("member.getOneMember", memId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return memVo;
	}

}
