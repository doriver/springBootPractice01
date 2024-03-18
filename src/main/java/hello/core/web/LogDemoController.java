package hello.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌 > 생성자가 하나면 자동으로 의존관계 주입 됨
public class LogDemoController {
	private final ObjectProvider<MyLogger> myLoggerProvider;
	private final LogDemoService logDemoService;
	
	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		
		MyLogger myLogger = myLoggerProvider.getObject(); // HTTP 요청이 진행중이므로 requestScopeBean이 생성된다
		myLogger.setRequestURL(requestURL);
		myLogger.log("log in controller");
		
		logDemoService.getLoggerAndLog("call in controller");
		
		return "OK";
	}
}
