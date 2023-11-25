package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {
	public static void main(String[] args) {
		// 문자 기반의 Buffered 스트림 사용하기
		try {
			// 문자 기반의 Buffered스트림으로 자료를 읽을 때는 줄 단위로 데이터를 읽을 수 있다.
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			BufferedReader br = new BufferedReader(fr);
			
			String temp = ""; // 읽어온 문자열이 저장될 변수
			
			// readLine() 한줄씩 값을 읽어오는 것
			for (int i = 1; ( temp = br.readLine() ) != null; i++) {
				System.out.printf("%4d : %s\n", i, temp);
			}
		} catch (IOException e) {
		}
	}
}
