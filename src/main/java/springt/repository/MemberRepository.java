package springt.repository;

import springt.domain.Member;

import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    //회원 저장시 저장된 회원이 반환됨
    Member save(Member member);
    // 방금 아이디와 이름으로 회원찾는 걸 만들거임
    // optional은 간단하게 가져올때 null이 올수있으니
    // optional로 감싸서 반환하는게 선호 자바8에 들어간 기능
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //모든 멤버를 반환
    List<Member> findAll();
}
