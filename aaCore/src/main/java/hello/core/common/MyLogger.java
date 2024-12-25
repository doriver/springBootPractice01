package hello.core.common;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@Scope(value= "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger { // 로그 출력위한 클래스
	private String uuid;
	private String requestURL;
	
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
	public void log(String message) {
		System.out.println("[" + uuid + "]" + " [" + requestURL + "] " + message);
	}
	
	@PostConstruct // 빈이 생성되는 시점에 자동으로 @PostConstruct초기화 메서드에 의해, uuid를 생성해 저장 
	public void init() {
		uuid = UUID.randomUUID().toString();
		System.out.println("[" + uuid + "] request scope bean create: " + this);
	}
	@PreDestroy
	public void close() {
		System.out.println("[" + uuid + "] request scope bean close: " + this);
	}
}
