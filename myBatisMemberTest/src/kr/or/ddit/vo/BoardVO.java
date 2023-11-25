package kr.or.ddit.vo;

public class BoardVO {
//	private int board_no;		// 번호(자동증가)
//	private String board_title;	// 제목
//	private String board_writer;// 작성자
//	private Date board_date;	// 작성 날짜
//	private int board_cnt;		// 조회수
//	private String board_content; // 내용
	
	private int board_no;
	private String board_title;
	private String board_writer;
	private String board_date;	// 날짜는 String으로 하는 것이 다루기 편함
	private int board_cnt;
	private String board_content;
	
	public int getBoard_no() {
		return board_no;
	}
	
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	
	public String getBoard_title() {
		return board_title;
	}
	
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	
	public String getBoard_writer() {
		return board_writer;
	}
	
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	
//	public Date getBoard_date() {
//		return board_date;
//	}
//	
//	public void setBoard_date(Date board_date) {
//		this.board_date = board_date;
//	}
	
	public int getBoard_cnt() {
		return board_cnt;
	}
	
	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}

	public void setBoard_cnt(int board_cnt) {
		this.board_cnt = board_cnt;
	}
	
	public String getBoard_content() {
		return board_content;
	}
	
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
	@Override
	public String toString() {
		return board_no + "\t" + board_title + "\t\t" 
				+ board_writer+ "\t" + board_cnt;
	}
	
}
