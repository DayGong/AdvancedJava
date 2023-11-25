package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

public class BoardController {
	private Scanner scan;
	private IBoardService service;
	
	public BoardController() {
		scan = new Scanner(System.in);
		service = BoardServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new BoardController().boardStart();
	}

	// 시작 메서드
	private void boardStart() {
		while (true) {
			getBoardAll();
			String choice = printMenu("메뉴 : "
					+ "1.새 글 작성     2.게시글 보기    3.검색    0.작업끝");
			
			switch(choice) {
			case "1":
				insertBoard();
				break;
			case "2":
				selectBoard();
				break;
			case "3":
				searchBoard();
				break;
			case "0":
				printTitle("작업을 종료합니다.");
				return;
			default:
				printTitle("잘못 입력하셨습니다.");	
			}
		}
		
	}

	private void searchBoard() {
		printTitle("검색 작업");
		
		System.out.print("검색할 제목 입력: ");
		String search = scan.nextLine();
		
		if ( "".equals(search) ) {
			return;
		}
		
		printTitle("No\t제목\t\t작성자\t조회수");
		
		List<BoardVO> boardList = service.getSearchBoard(search);
		
		if (boardList == null || boardList.size() == 0) {
			System.out.println("검색된 게시글이 없습니다.");
		} else {
			for (BoardVO boardVo : boardList) {
				System.out.println(boardVo);
			}
		}
		System.out.println("==========================================================");
		
	}

	// 게시글 내용을 보여주는 메서드
	private void selectBoard() {
		String boardNo = input("보기를 원하는 게시물 번호 입력 >> ");
		
//		BoardVO boardVo = service.selectBoard(boardNo);
		BoardVO boardVo = service.getBoard(Integer.parseInt(boardNo));
		
//		if (boardVo.getBoard_no() == 0) {
		if (boardVo == null) {
			printTitle(boardNo + "번 게시글이 존재하지 않습니다.");
			return;
		}
		
		printTitle(boardVo.getBoard_no() + "번글 내용");
		System.out.println("- 제목 : " + boardVo.getBoard_title());
		System.out.println("- 작성자 : " + boardVo.getBoard_writer());
		System.out.println("- 내용 : " + boardVo.getBoard_content());
		System.out.println("- 작성일 : " + boardVo.getBoard_date());
		System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
		System.out.println("==========================================================");
		
		boardChoice(boardVo);
		
	}

	private void boardChoice(BoardVO boardVo) {
		String choice = printMenu("메뉴 : 1.수정    2.삭제    3.리스트로 가기");
			
		switch(choice) {
		case "1":
			updateBoard(boardVo);
			break;
		case "2":
			deleteBoard(boardVo.getBoard_no());
			break;
		case "3":
			printTitle("리스트로 돌아갑니다.");
			return;
		default:
			printTitle("잘못 입력하셨습니다.");	
		}
	}

	// 게시글을 삭제하는 메서드
	private void deleteBoard(int boardNo) {
		printTitle("게시글 삭제");
		
		int res = service.deleteBoard(boardNo);
		
		if (res == 1) {
			printTitle(boardNo+ "번 게시글 삭제 성공");
		} else {
			printTitle(boardNo+ "번 게시글 삭제 실패");
		}
	}

	// 게시글을 수정하는 메서드
	private void updateBoard(BoardVO boardVo) {
		printTitle("게시글 수정");
		
		String reTitle = input("제목 : ");
		String reContent = input("내용: ");
		
		boardVo.setBoard_title(reTitle);
		boardVo.setBoard_content(reContent);
		
		int res = service.updateBoard(boardVo);
		
		if (res == 1) {
			printTitle("게시글 수정 성공");
		} else {
			printTitle("게시글 수정 실패");
		}
	}

	// 새 글을 작성하는 메서드
	private void insertBoard() {
		printTitle("새 글 작성하기");
		
		String title = input("제목: ");
		String writer = input("작성자: ");
		String content = input("내용: ");
		
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int res = service.insertBoard(boardVo);
		
		if (res == 1) {
			printTitle("게시글 작성 성공");
		} else {
			printTitle("게시글 작성 실패");
		}
	}

	// 게시판 목록을 보여주고 메뉴를 나타내며 사용자가 입력한 작업 번호를 반환하는 메서드
	private void getBoardAll() {
		
		// 전체 게시판 정보 가져오기
		List<BoardVO> boardList = service.getAllBoard();
		
		printTitle("No\t제목\t\t작성자\t조회수");
		
		if (boardList == null || boardList.size() == 0) {
			System.out.println("등록된 게시글이 없습니다.");
		} else {
			// 출력 부분
			for (BoardVO boardVo : boardList) {
//				System.out.println(boardVo);
				System.out.println(boardVo.getBoard_no() + "\t"
						+ boardVo.getBoard_title() + "\t\t"
						+ boardVo.getBoard_writer() + "\t"
						+ boardVo.getBoard_cnt());
			}
		}
		System.out.println("==========================================================");
	}
	
	private String input(String text) {
		System.out.print(text);
		return scan.nextLine().trim();
	}

	private void printTitle(String... text) {
		System.out.println();
		System.out.println("==========================================================");
		for(int i = 0; i < text.length; i ++) {
			System.out.println(text[i]);
		}
		System.out.println("==========================================================");
	}

	private String printMenu(String text) {
		System.out.println(text);
		System.out.print("작업 선택 >> ");
		return scan.nextLine().trim();
	}
	
}
