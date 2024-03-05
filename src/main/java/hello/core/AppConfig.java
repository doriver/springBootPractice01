package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


@Configuration // 없애본 결과도 봐보기
public class AppConfig {
	// @Bean memberRepository() --> new MemoryMemberRepository()
    // @Bean memberService() --> memberRepository() --> new MemoryMemberRepository()
    // @Bean orderService() --> memberRepository() --> new MemoryMemberRepository()
	
	// 스프링컨테이너 생성했을때 println되는 결과, @Configuration 없으면 call AppConfig.memberRepository가 총 3번 나옴
//	call AppConfig.memberService
//	call AppConfig.memberRepository
//	call AppConfig.orderService
	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}
	
	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
//		return null;
	}
	
	@Bean
	public DiscountPolicy discountPolicy() {
		return new FixDiscountPolicy();
//		return new RateDiscountPolicy();
	}
}
