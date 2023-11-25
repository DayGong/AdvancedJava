package kr.or.ddit.basic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesWriteTest {

	public static void main(String[] args) {
		// Properties객체의 내용을 파일로 저장하는 예제
		Properties prop = new Properties();
		
		// 저장할 파일 정보를 갖는 File객체 생성
		File f = new File("res/kr/or/ddit/config/memo.properties");
		
		FileOutputStream fout = null;	// 파일 출력용 스트림 객체 변수 선언
		
		try {
			// 저장할 내용을 Properties객체에 추가한다.
//			prop.setProperty("name", "홍길동");
			prop.setProperty("name", new String("홍길동".getBytes("utf-8"), "ISO-8859-1"));
			prop.setProperty("age", String.valueOf(30));
			prop.setProperty("tel", "010-1234-5678");
			prop.setProperty("addr", "대전시 중구 오류동");
			
			// 출력용 스트림 객체 생성
			fout = new FileOutputStream(f);
			
			// 파일로 저장할 때는 store()메서드를 이용한다.
			prop.store(fout, "이것은 주석 내용입니다.");
			System.out.println("출력 작업 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( fout != null ) try { fout.close(); } catch(IOException e) {}
		}
	}
}
