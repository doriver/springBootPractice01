package hello.core.scan;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.order.OrderServiceImpl;

public class AutoAppConfigTest {
	
	@Test
	void basicScan() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
	
		OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
		MemberRepository memberRepository = bean.getMemberRepository();
		System.out.println("memberRepository = " + memberRepository);
	}

}
