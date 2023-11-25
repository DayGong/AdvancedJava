package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {
	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장
		try {
			// System.in ==> 콘솔(표준 입출력 장치) 입력 장치를 이용한 입력 스트림 객체
//			System.out.println("아무거나 입력하세요");
//			int c = System.in.read();
//			System.out.println("입력값: " + (char)c);
			
			// 입력용 바이트 기반 스트림을 입력용 문자기반 스트림으로 변환
			// InputStreamReader => 보조스트림
			InputStreamReader isr = new InputStreamReader(System.in);
			
			// 파일 출력용 문자 기반 스트림 객체 생성
			FileWriter fw = new FileWriter("d:/d_other/testChar.txt");
			
			System.out.println("아무거나 입력하세요(입력의 끝은 Ctrl+Z 키입니다)");
			
			int c;
			
			// 콘솔에서 입력할 때 입력의 끝은 'Ctrl' + 'Z'키를 누르면 된다.
			while( (c=isr.read()) != -1) {
				fw.write(c); // 콘솔로 입력받은 자료를 파일에 출력
			}
			
			// 스트림 닫기
			isr.close();
			fw.close();
			
		} catch (IOException e) {
		}
	}
}
