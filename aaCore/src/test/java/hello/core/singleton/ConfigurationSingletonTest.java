package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	@Test
	void configuratinoTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
		MemberRepository memberRepository = ac.getBean(MemberRepository.class);
		
		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();
		System.out.println("memberService --> memberRepository = " + memberRepository1);
		System.out.println("orderService --> memberRepository = " + memberRepository2);
		System.out.println("memberRepository = " + memberRepository);
		
		Assertions.assertThat(memberRepository1).isSameAs(memberRepository);
		Assertions.assertThat(memberRepository2).isSameAs(memberRepository);		
	}
	
	@Test
	void configurationDeep() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = ac.getBean(AppConfig.class);
		
		System.out.println(bean.getClass()); // class hello.core.AppConfig$$SpringCGLIB$$0
		// @Configuration 없으면 class hello.core.AppConfig 출력
	}
}
