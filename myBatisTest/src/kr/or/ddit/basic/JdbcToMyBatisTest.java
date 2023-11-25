package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.vo.LprodVO;

public class JdbcToMyBatisTest {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		InputStream in = null;
		SqlSessionFactory sqlSessionFactory = null;
		
		try {
			in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
			
		} catch (Exception e) {
			System.out.println("MyBatis 초기화 실패");
			e.printStackTrace();
		} finally {
			if (in != null) try { in.close(); } catch(IOException e) {}
		}
		
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			
			// lprod_id 중에서 제일 큰 값+1을 불러온다.
			int maxId = session.selectOne("jdbc.getLprodMax");
			
			// 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
			String gu; // lprod_gu(상품분류코드)값이 저장될 변수 선언
			int duplCnt = 0; // 입력한 lprod_gu의 개수가 저장될 변수 선언
			
			do {
				System.out.print("상품 분류 코드 (LPROD_GU) 입력 >> ");
				gu = scan.next();
				
				duplCnt = session.selectOne("jdbc.getDupliCnt", gu);
				
				if (duplCnt > 0) {
					System.out.println("중복된 분류 코드입니다.");
				}
				
			} while (duplCnt > 0);
			
			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = scan.next();
			
			LprodVO lprodVo = new LprodVO();
			lprodVo.setLprod_id(maxId);
			lprodVo.setLprod_gu(gu);
			lprodVo.setLprod_nm(nm);
			
			int insertCnt = session.insert("lprod.insertLprod", lprodVo);
			
			if (insertCnt > 0) {
				session.commit();
				System.out.println("insert 작업 성공");
			} else {
				System.out.println("insert 작업 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scan.close();
			session.close();
		}
		
	}
}
