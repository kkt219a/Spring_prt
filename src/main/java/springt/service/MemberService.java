package springt.service;

import springt.domain.Member;
import springt.repository.MemberRepository;
import springt.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //test에서 중복 문제 해결을 위하여
//    private final MemberRepository memberRepository =
//            new MemoryMemberRepository();

    //이렇게 개선, 직접이 아니라 외부에서 넣도록
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    /** 회원가입 */
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증

        /**같은 이름이 있는 중복 회원x
         근데 바로 밑 쓸필요 없이 이거 바로 변수로 만들어주는
         단축키는 또 무지 ..
         */
        //Optional<Member> result =
                //memberRepository.findByName((member.getName()));
        /**만약 값이 있으면, 동작함 optional이 있어서 가능
         * 그거 아니면 if(==null) 이런식으로 했겠지 그게 아니면
         * Member member = result.get();
         * 이런식으로 했을거고 아니면
         * result.orElseGet() 이런거로 있으면 꺼내라! 로 썼을거다
        */
        //result.ifPresent(member1 -> {
            //throw new IllegalStateException("이미 존재하는 회원입니다.");
        //});

        memberRepository.save(member);
        return member.getId();
    }

    /** 이럴 필요 없이 바로 이렇게, 어차피 Optional 적용되니까!
     *  얘를 바로 함수로 만드는 단축키는 또 무지 ;; 내부 내용을 바로 만들어주는
     * */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())

                .ifPresent(m -> {

                    throw new IllegalStateException("이미 존재하는 회원입니다.");

                });
    }

    /**
     *
     *
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
