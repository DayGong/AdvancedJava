package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {
	public static void main(String[] args) {
		// AWT ==> SWING ==> javaFX
		
		// SWING의 파일 열기 창, 파일 저장 창 만들기 연습
		JFileChooser chooser = new JFileChooser();
		
		// 선택할 파일의 확장자 설정
		FileNameExtensionFilter txt = 
				new FileNameExtensionFilter("텍스트문서(*.txt)", "txt");
		
		FileNameExtensionFilter img = 
				new FileNameExtensionFilter("이미지 파일", "png", "jpg", "gif");
		
		FileNameExtensionFilter doc = 
				new FileNameExtensionFilter("MS Word문서", new String[]{"doc", "docx"});
		
		FileNameExtensionFilter pdf = 
				new FileNameExtensionFilter("PDF 문서", "pdf");
		
		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(pdf);
		
		// 확장자 목록 중 처음부터 선택된 상태가 될 확장자 지정
		chooser.setFileFilter(txt);
		
		// 확장자 목록 중 '모든 파일' 사용 여부 확인(생략한 경우: true)
//		chooser.setAcceptAllFileFilterUsed(false);	// 나타나지 않음
		chooser.setAcceptAllFileFilterUsed(true);	// 나타남
		
		// Dialog창의 기본 위치(디렉토리 위치) 설정하기
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
//		int result = chooser.showOpenDialog(new Panel()); // 열기용 창
		int result = chooser.showSaveDialog(new Panel()); // 저장용 창
		
		// '저장' 또는 '열기' 버튼을 눌렀는지 여부 검사
		if(result == JFileChooser.APPROVE_OPTION) {
			// 선택한 파일 정보를 갖는 File 객체를 반환
			File selectFile = chooser.getSelectedFile();
			
			System.out.println("선택한 파일 ==> " + selectFile.getAbsolutePath());
		}
	}
}
