package kr.or.ddit.fileupload.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.FileInfoVO;

public class FileinfoDaoImpl implements IFileinfoDao {
	
	private static FileinfoDaoImpl dao;
	
	private FileinfoDaoImpl() {}
	
	public static FileinfoDaoImpl getInstance() {
		if (dao == null) {
			dao = new FileinfoDaoImpl();
		}
		
		return dao;
	}

	@Override
	public int insertFileinfo(FileInfoVO fileVo) {
		int cnt = 0; // 반환값 변수
		
		SqlSession session = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.insert("fileinfo.insertFileinfo", fileVo);
			
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( session != null ) {
				session.close();
			}
		}
		
		return cnt;
	}

	@Override
	public List<FileInfoVO> getAllFileinfo() {
		List<FileInfoVO> fileList = null; // 반환값 변수
		
		SqlSession session = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			fileList = session.selectList("fileinfo.getAllFileinfo");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( session != null ) {
				session.close();
			}
		}
		
		return fileList;
	}

	@Override
	public FileInfoVO getFileinfo(int fileNo) {
		FileInfoVO fileVo = null; // 반환값 변수
		SqlSession session = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			fileVo = session.selectOne("fileinfo.getFileinfo", fileNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( session != null ) {
				session.close();
			}
		}
		
		return fileVo;
	}

}
