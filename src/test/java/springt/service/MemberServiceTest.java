package springt.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springt.domain.Member;
import springt.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    // test는 함수 이름 한글로 써도됨 void 회원가입!!

    MemberService memberService;
    //MemberService memberService=new MemberService();
    //테스트에서의 new는 다른 객체다 밑에애가..? 두개를 쓸 이유없다?
    // 같은거 쓰는게 낫다? static은 instance와 상관없이 class레벨이 붙어
    // MemberService에서 MemberRepository와 testcase에서 만든
    // memberRepository가 서로 다른 인스턴스다..?
    // static이 아니면 문제가 생기겠지?
    // 다른 repository가 테스트 되고 있는거다..?
    // 같은 인스턴스 쓰게 바꾸려면

    //MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memoryMemberRepository;

    //동작 전에 수행하는 것 각 테스트마다 얘네를 만들고 같은 레포지토리에서 사용하겠지
    // 직접 new 하지않고 멤버 레포지토리를 외부에서 넣어주지
    // dependency Injection = DI 상세하게 보자!
    // 지금 이 예시가 그 예시다
    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId= memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        // assertThat을 static 올리는거도 alt enter

    }

    //조인의 핵심은 중복 회원 로직으로 중간에 터뜨려지는거도 봐야한다
    @Test
    public void 중복_회원_예외() {
        //given
        // 회운가입에서 member1에 spring 하면 터진다 중복이니까
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        //try, catch로 잡을 수도 있다
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail(); // 여기까지 오면 fail
//        } catch (IllegalStateException e){
//            //증명 성공, 밑에 오류 발생하겠지
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // 근데 이거때문에 try cath 쓰기는 좀 . 그래서!
        // IllegalStateException 를
        //NullPointerException 로 바꿔쓰면 테스트 실패, 오류 발생
        // ctrl+alt+v 하면 변수로 추출 가능
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then


    }
    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}