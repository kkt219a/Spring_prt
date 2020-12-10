package springt.repository;

import org.assertj.core.api.Assertions;
import springt.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import springt.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 하나가 끝날때마다 실행
    // AfterEach는 직전 테스트에 멤버가 선언되어 있는데 또 멤버를
    // 선언하니까 재선언이 안되서. 의존관계를 끊어주기 위한 것
    @AfterEach
    public void afterEach() {
        //repository 초기화!
        repository.clearStore();
    }

    //Test(org.junit.jupitter.api)를 import하면 실행할 수 있다.
    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        // then - 아이디를 가지고 결과가 있는지 체크
        // get으로 꺼내는게 좋은 방법은 아님
        Member result = repository.findById(member.getId()).get();

        // 잘 저장되었는지 검증.
        // 맞으면 파란불, 아니면 빨간불 에러
        // Assertions.assertEquals(member,result);
        // static import하면 Assertions. 안써도 된다.
        // Assertions. 에서 alt+enter --> static
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        // 위에 것을 복사해서 이름이 같은 문제를
        // shift f6 하면 수정이 나온다
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {

        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }

}
