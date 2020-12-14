package springt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springt.domain.Member;
import springt.repository.MemberRepository;
import springt.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    //외부에서 사용할 수 있게 개선해서 사용
    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    /** 회원가입 */
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * memberRepository.findByName(member.getName())는
     * optional이라 ifPresent 사용 가능
     * 또는 orElseGet()으로 값이 있으면 꺼내고,
     * 없으면 어떤 method를 실행하도록 하는 것으로 사용가능
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    //네이밍이 비지니스적이여야한다. 그래야 비지니스쪽이랑 대화가 잘됨?
    // 서비스는 비지니스에 의존적, 레포지토리는 기계적인, 개발적인 용어 선택
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
