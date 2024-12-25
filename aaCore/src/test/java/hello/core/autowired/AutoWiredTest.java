package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import hello.core.member.Member;

public class AutoWiredTest {
	@Test
	void AutoWiredOption() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
	}
	static class TestBean {
		@Autowired(required = false) // 해당 메서드 호출x , required = false 없으면 오류남
		public void setNoBean1(Member noBean1) {
			System.out.println("noBean1 = " + noBean1);
		}
		@Autowired
		public void setNoBean2(@Nullable Member noBean2) {
			System.out.println("noBean2 = " + noBean2); // null
		}
		@Autowired
		public void setNoBean3(Optional<Member>  noBean3) {
			System.out.println("noBean3 = " + noBean3); // Optional.empty
		}
	}
}
