package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
	@Test
	public void lifeCycleTest() {
		ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient client = ac.getBean(NetworkClient.class);
		ac.close(); // ConfigurableApplicationContext에 close메소드 있음
	}
	
	@Configuration
	static class LifeCycleConfig {
		@Bean
		public NetworkClient networkClient() {
			NetworkClient nc = new NetworkClient();
			nc.setUrl("http://hello-spring.dev");
			return nc;
		}
	}
}
