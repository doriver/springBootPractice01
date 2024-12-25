package hello.core.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class ApplicationContextFind01 {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	@DisplayName("getBean() 기본")
	void findBeanBasic() {
//		MemberService memberService00 = ac.getBean("memberService");  // 에러 cannot convert from Object to MemberService
		MemberService memberService01 = ac.getBean("memberService", MemberService.class);
		MemberService memberService02 = ac.getBean(MemberService.class);
		MemberService memberService03 = ac.getBean("memberService", MemberServiceImpl.class);
		System.out.println(memberService01);
		System.out.println(memberService02); // hello.core.member.MemberServiceImpl@18c5069b 이런식
		System.out.println(memberService03);
		
		Assertions.assertThat(memberService01).isInstanceOf(MemberServiceImpl.class);
		org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class
				, () -> ac.getBean("xxx", MemberService.class));
	}
	
	@Test
	@DisplayName("빈 출력하기")
	void findBean() {
		String[] beanNames = ac.getBeanDefinitionNames(); // 스프링에 등록된 모든 빈 이름 얻는다
		for(String beanName : beanNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
			Object bean = ac.getBean(beanName); // 빈 이름으로만 하면 Object로 받아야함
			
			// ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            // ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				System.out.println("ROLE_APPLICATION / Name = " + beanName + " / object = " + bean);
			} else {
				System.out.println( beanDefinition.getRole() + " / Name = " + beanName + " / object = " + bean);
			}
		}
	}
}
