package kr.or.ddit.session.dao;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDao {
	
	/**
	 * 회원 ID와 Password가 저장된 MemberVO객체를 인수값으로 받아서
	 * 해당 회원을 검색하여 반환하는 메서드
	 * 
	 * @param memVo 검색할 회원ID와 Password가 저장된 MemberVO객체
	 * @return 검색된 회원정보가 저장된 MemberVO객체
	 */
	public MemberVO getLoginMember(MemberVO memVo);
	
}
