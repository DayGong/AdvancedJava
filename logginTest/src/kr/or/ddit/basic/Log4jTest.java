package kr.or.ddit.basic;

import org.apache.log4j.Logger;

public class Log4jTest {
	// import org.apache.log4j.Logger;
	private static Logger logger = Logger.getLogger(Log4jTest.class);
	public static void main(String[] args) {
		/*
			로그 기록을 남기는 방법
			형식) Logger객체.레벨이름("로그 메시지")
		*/
		logger.trace("이 메시지는 [Trace]레벨의 메시지입니다.");
		logger.debug("[Debug]레벨의 메시지 출력하기");
		logger.info("log4j의 [Info]레벨의 메시지");
		logger.warn("경고를 나타내는 [Warn]레벨의 메시지");
		logger.error("오류에 대한 메시지를 나타내는 [Error]레벨");
		logger.fatal("가장 심각한 오류를 나타내는 [Fatal]레벨");
	}
}
