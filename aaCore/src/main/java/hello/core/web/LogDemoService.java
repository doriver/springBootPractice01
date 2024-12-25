package hello.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogDemoService {

	private final MyLogger myLogger; // controller에서와 같은 프록시빈 이 주입됨
	
	public void getLoggerAndLog(String id) {
//		MyLogger myLogger = myLoggerProvider.getObject(); // Controller와 같은 HTTP 요청이라, Controller에서 생성된 같은 requestBean이 반환된다!
		System.out.println("service myLogger = " + myLogger.getClass());
		myLogger.log("service id = " + id);
	}
}
