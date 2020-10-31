package springt.repository;

import springt.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import springt.repository.MemoryMemberRepository;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 하나가 끝날때마다 실행된다!
    // 이거 없으면 멤버가 선언되어있는데 TEST에서 또 멤버를
    // 선언하니까 재선언이 안되서. 의존관계를 끊어주기 위한거다
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();

        // 맞으면 그냥 파란불, 아니면 빨간불에 에러 나옴
        //Assertions.assertEquals(member,result);
        // static import하면 Assertions. 안써도 됨
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        //shift f6 하면 수정 나옴
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
