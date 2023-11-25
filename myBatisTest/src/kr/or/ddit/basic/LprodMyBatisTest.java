package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.vo.LprodVO;

public class LprodMyBatisTest {
	public static void main(String[] args) {
		// MyBatis를 이용하여 DB자료를 처리하는 순서 및 방법
		Scanner scan = new Scanner(System.in);
		
		// 1. MyBatis의 환경 설정 파일(mybatis-config.xml)을 읽어와서
		//	  그 내용을 처리한 후 SqlSessionFactory객체를 생성한다.
		
		InputStream in = null;	// 스트림 객체 변수 선언
		SqlSessionFactory sqlSessionFactory = null;
		
		try {
			// 1-1. 환경 설정 파일을 읽어 올 스트림 객체 생성
			in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");
			
			// 1-2. 환경 설정 파일을 읽어와 처리하고 처리가 성공하면 
			//		SqlSessionFactory객체를 생성하여 반환한다.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
			
		} catch (Exception e) {
			System.out.println("MyBatis 초기화 실패");
			e.printStackTrace();
		} finally {
			if( in != null ) try { in.close(); } catch(IOException e) {}
		}
		
		// ===============================================================
		
		// 2. mapper에 등록된 SQL문들 중 실행할 SQL문을 호출해서 원하는 작업을 수행한다.
		
		/*
		// 2-1. insert 연습
		System.out.println("insert 작업 시작");
		System.out.println();
		
		System.out.print("Lprod_id 입력 >> ");
		int lprodId = scan.nextInt();
		
		System.out.print("Lprod_gu 입력 >> ");
		String lprodGu = scan.next();
		
		System.out.print("Lprod_nm 입력 >> ");
		String lprodNm = scan.next();
		
		// 입력한 데이터들을 VO에 저장한다.
		LprodVO lvo1 = new LprodVO();
		lvo1.setLprod_id(lprodId);
		lvo1.setLprod_gu(lprodGu);
		lvo1.setLprod_nm(lprodNm);
		
		SqlSession session = null;
		
		try {
			// SqlSessionFactory객체의 openSession()메서드를 이용하여 mapper에서
			// SQL문을 호출해서 실행할 수 있는 SqlSession객체를 생성한다.
			// 형식) SqlSessionFactory객체.openSession(논리값);
			//		'논리값'이 true면 AutoCommit이 활성화 된 상태가 되고,
			//		'논리값'이 생략되거나 false면 AutoCommit이 비활성화 된 상태가 된다.
			session = sqlSessionFactory.openSession();
			
			// SqlSession객체를 이용하여 처리할 SQL문을 호출해 실행
			// 형식) SqlSession객체.insert("namespace속성값.id속성값", 파라미터클래스)
			//		반환값: 작업에 성공한 레코드 수
			int insertCnt = session.insert("lprod.insertLprod", lvo1);
			
			if (insertCnt > 0) {
				// SqlSession객체를 생성할 때 AutoCommit이 비활성화된 상태일 때는
				// Commit을 직접 실행해 줘야 한다.
				session.commit();
				System.out.println("insert 작업 성공");
			} else {
				System.out.println("insert 작업 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 작업이 끝나면 SqlSession객체를 닫아준다.
			session.close();
		}
		System.out.println("=======================================================");
		*/
		
		// 2-2. update 연습
		/*
		System.out.println("update 작업 시작");
		System.out.println();
		
		System.out.print("수정할 Lprod_gu 입력 >> ");
		String lprodGu2 = scan.next();
		
		System.out.print("수정할 Lprod_id 입력 >> ");
		int lprodId2 = scan.nextInt();
		
		System.out.print("수정할 Lprod_nm 입력 >> ");
		String lprodNm2 = scan.next();
		
		// 입력한 데이터들을 VO에 저장한다.
		LprodVO lvo2 = new LprodVO();
		lvo2.setLprod_id(lprodId2);
		lvo2.setLprod_gu(lprodGu2);
		lvo2.setLprod_nm(lprodNm2);
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			// SQL문을 호출해서 실행하기
			// 형식) SqlSession객체.update("namespace속성값.id속성값", 파라미터클래스)
			//		반환값: 작업에 성공한 레코드 수
			int updateCnt = session.update("lprod.updateLprod", lvo2);
			
			if(updateCnt > 0) {
				session.commit();
				System.out.println("update 작업 성공");
			} else {
				System.out.println("update 작업 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("=======================================================");
		*/
		
		/*
		// 2-3. delete 연습
		System.out.println("delete 작업 시작");
		System.out.println();
		
		System.out.print("삭제할 Lprod_gu >> ");
		String lprodGu3 = scan.next();
		
		lprodGu3 = (lprodGu3 + "   ").substring(0, 4);
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			// SQL문 호출해서 실행하기
			// 형식) SqlSession객체.delete("namespace속성값.id속성값", 파라미터클래스)
			//		반환값: 작업에 성공한 레코드 수
			int deleteCnt = session.delete("lprod.deleteLprod", lprodGu3);
			
			if (deleteCnt > 0) {
				session.commit();
				System.out.println("delete 작업 성공");
			} else {
				System.out.println("delete 작업 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("=======================================================");
		*/
		
		// 2-4. select 연습
		/*
		// 1) 검색 결과가 여러 개의 레코드인 경우
		System.out.println("select 작업 시작 (결과가 여러 개의 레코드일 경우)");
		System.out.println();
		
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			
			// SQL문을 호출해서 실행한다.
			// 형식) SqlSession객체.selectList("namespace속성값.id속성값", 파라미터클래스);
			//		==> select문이 실행된 결과가 여러 개의 레코드일 경우에는 selectList()메서드를
			//			사용하는데 이 메서드는 검색된 레코드 각각을 'resultType'에 지정한
			//			객체(보통 VO객체)에 저장한 후 이 객체를 List에 추가해주는 작업을 자동으로 수행한다.
			List<LprodVO> lprodList = session.selectList("lprod.getAllLprod");
			
			for ( LprodVO lvo3 : lprodList ) {
				System.out.println("ID: " + lvo3.getLprod_id());
				System.out.println("GU: " + lvo3.getLprod_gu());
				System.out.println("NM: " + lvo3.getLprod_nm());
				System.out.println("===============================");
			}
			System.out.println("출력 끝");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		*/
		
		// 2) 검색 결과가 한 개의 레코드인 경우
		System.out.println("select 작업 시작 (결과가 한 개의 레코드인 경우)");
		System.out.println();
		
		System.out.print("검색할 Lprod_gu 입력 >> ");
		String lprodGu = scan.next();
		
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			
			// SQL문을 호출해서 실행하기
			// 형식) SqlSession객체.selectOne("namespace속성값.id속성값", 파라미터클래스)
			//		==> select문을 실행한 결과가 한 개의 레코드일 경우에는 selectOne()메서드를 사용한다.
			//			selectOne()메서드는 검색한 결과가 없으면 null을 반환한다.
			//			반환값의 자료형: resultType 속성에 지정한 객체의 자료형과 같다.
			
			LprodVO lvo4 = session.selectOne("lprod.getLprod", lprodGu);
			
			System.out.println("===========검색 결과==============");
			
			if(lvo4 == null) {
				System.out.println("검색한 데이터가 없습니다.");
				return;
			}
			
			System.out.println("ID: " + lvo4.getLprod_id());
			System.out.println("GU: " + lvo4.getLprod_gu());
			System.out.println("NM: " + lvo4.getLprod_nm());
			System.out.println("===============================");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
