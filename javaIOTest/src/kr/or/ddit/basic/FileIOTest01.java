package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {
	public static void main(String[] args) {
		// d:/d_other/test.txt파일의 내용을 읽어와 화면에 출력하는 예제
		
		try {
			// 파일 입력용 스트림 객체 생성
			// 파일 입력용 스트림 객체를 생성할 때 읽어올 파일을 지정해야 한다.
			
			// 방법1 ==> 읽어올 파일의 경로 및 파일명을 문자열로 지정
//			FileInputStream fin = new FileInputStream("d:/d_other/test.txt");
			
			// 방법2 ==> 읽어올 파일의 정보를 갖는 File객체를 지정
			File file = new File("d:/d_other/test.txt");
			FileInputStream fin = new FileInputStream(file);
			
			int c; // 읽어온 데이터를 저장할 변수
			while((c = fin.read()) != -1) {
				// 읽어온 자료를 화면에 출력
				System.out.print((char)c);
			}
			
			// 작업 완료 후 스트림 닫기
			fin.close();
			
		} catch (IOException e) {
			System.out.println("입출력 오류");
		}
	}
}
