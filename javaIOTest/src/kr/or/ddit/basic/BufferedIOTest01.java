package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {
	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered스트림을 사용
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");

			// 버퍼의 크기가 5byte인 버퍼 스트림 객체 생성
			// 버퍼의 크기를 지정하지 않으면 버퍼의 기본 크기는 8KB(8196byte)
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);

			for (char c = '1'; c <= '9'; c++) {
				bout.write(c);
			}
			bout.flush(); // 출력 작업이 완료되면 출력 버퍼에 남아있는 데이터를 강제로 출력해줘야 한다.

			// 스트림 닫기
			// 보조 스트림을 닫으면 기반이 되는 스트림도 자동으로 닫힌다.
			// 버퍼 스트림의 close()메서드에는 flush기능이 내장되어 있다.
			bout.close();
			
			System.out.println("작업 끝");

		} catch (IOException e) {
		}
	}
}
