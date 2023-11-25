package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {
	public static void main(String[] args) {
		// 파일에 알파벳 A~Z까지 출력하기
		try {
			// 파일 출력용 스트림 객체 생성 ==> 출력할 파일을 지정해서 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/outTest.txt");
			
			for (char i = 'A'; i <= 'Z'; i++) {
				fout.write(i); // i변수의 값을 파일로 출력
			}
			
			System.out.println("출력 작업 완료");
			
			// 스트림 닫기
			fout.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
