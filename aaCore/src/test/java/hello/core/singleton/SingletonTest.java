package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import hello.core.AppConfig;
import hello.core.member.MemberService;

public class SingletonTest {

	@Test
	@DisplayName("스프링 없는 순수DI컨테이너")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();
		
		MemberService memberService1 = appConfig.memberService();
		MemberService memberService2 = appConfig.memberService();
		
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);
		
		Assertions.assertThat(memberService1).isNotSameAs(memberService2);
	}
	
	@Test
	@DisplayName("싱글톤 패턴 객체 생성")
	void singletonTest() {
//		new SingletonService(); // private로 생성자를 막아둬서 오류
		
		SingletonService singleton1 = SingletonService.getInstance();
		SingletonService singleton2 = SingletonService.getInstance();
		
		System.out.println("singleton1 = " + singleton1);
		System.out.println("singleton2 = " + singleton2);
		
		Assertions.assertThat(singleton1).isSameAs(singleton2);
	}
	
	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MemberService memberService1 = ac.getBean("memberService", MemberService.class);
		MemberService memberService2 = ac.getBean("memberService", MemberService.class);
		
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);
		
		Assertions.assertThat(memberService1).isSameAs(memberService2);
	}
	
	static class TestConfig {
		@Bean
		public StatefulService statefulService() { return new StatefulService(); }
	}
	
	@Test
	@DisplayName("스프링빈은 무상태이여야 함")
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);
		
		// ThreadA : A사용자 10000원 주문
		statefulService1.order("userA", 10000);

		// ThreadB : B사용자 20000원 주문
		statefulService2.order("userB", 20000);
		
		// ThreadA : 사용자A 주문 금액 조회
		int price = statefulService1.getPrice();
		System.out.println(price); // 2만원 나와버림( 1만원 나와야 하는데 )
		
		Assertions.assertThat(price).isEqualTo(20000);
	}
}
