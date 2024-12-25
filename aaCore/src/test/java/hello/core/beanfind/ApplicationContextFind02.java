package hello.core.beanfind;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class ApplicationContextFind02 {
	
	@Configuration
	static class TestConfig {
		@Bean
		public MemberRepository memberRepository1() { return new MemoryMemberRepository(); }
		@Bean
		public MemberRepository memberRepository2() { return new MemoryMemberRepository(); }
		@Bean
        public DiscountPolicy rateDiscountPolicy() { return new RateDiscountPolicy(); }
		@Bean
        public DiscountPolicy fixDiscountPolicy() { return new FixDiscountPolicy(); }
	}
	
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
	
	@Test
	@DisplayName("같은 타입 빈 조회")
	void findSameType() {
		Assertions.assertThrows(NoUniqueBeanDefinitionException.class
				, () -> ac.getBean(MemberRepository.class));
		
		Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
		org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
		System.out.println("beansOfType = " + beansOfType);
		
		for(String key : beansOfType.keySet()) {
			System.out.println("beanName = " + key + " / bean = " + beansOfType.get(key));
		}
		
		MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
		org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
		System.out.println(memberRepository);
	}
	
	@Test
	@DisplayName("부모타입으로 빈 조회")
	void findExtends() {
		
		Assertions.assertThrows(NoUniqueBeanDefinitionException.class
				, () -> ac.getBean(DiscountPolicy.class));
		
		org.assertj.core.api.Assertions
		.assertThat(ac.getBean("rateDiscountPolicy", DiscountPolicy.class))
		.isInstanceOf(RateDiscountPolicy.class);

		org.assertj.core.api.Assertions
		.assertThat(ac.getBean(RateDiscountPolicy.class))
		.isInstanceOf(RateDiscountPolicy.class);
		
		Map<String, DiscountPolicy> beansOfDiscountPolicy = ac.getBeansOfType(DiscountPolicy.class);
		for(String key : beansOfDiscountPolicy.keySet()) {
			System.out.println("beanName = " + key + " / bean = " + beansOfDiscountPolicy.get(key));
		}
		System.out.println("   =============    =============   ");
		Map<String, Object> beansOfObject = ac.getBeansOfType(Object.class); // 이렇게 하면 스프링 내부적으로 쓰는 빈까지 다 티어 나옴
		for(String key : beansOfObject.keySet()) {
			System.out.println("beanName = " + key + " / bean = " + beansOfObject.get(key));			
		}
	}

}


