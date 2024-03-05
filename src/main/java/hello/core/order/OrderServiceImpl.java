package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService{

	@Autowired private MemberRepository memberRepository; // 필드에 직접 주입은 권장 x , 스프링 컨테이너 없으면 주입이 안됨
	@Autowired private DiscountPolicy discountPolicy;
	
//	@Autowired // 생성자 파라미터가 여러개여도 각 타입에 맞는 스프링빈을 찾아서 넣어줌
//	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//		this.memberRepository = memberRepository;
//		this.discountPolicy = discountPolicy;
//	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// 테스트 용도
	public MemberRepository getMemberRepository() { return memberRepository; }
}
