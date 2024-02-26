package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;
	
	@Autowired // ac.getBean(MemberRepository.class) 해서 생성자 파라미터에 넣어준다 보면 됨
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Override
	public void join(Member memeber) {
		memberRepository.save(memeber);
	}
	
	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
	
	// 테스트 용도
	public MemberRepository getMemberRepository() { return memberRepository; }
}
