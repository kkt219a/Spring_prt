package springt.repository;

import springt.domain.Member;

import java.util.List;
import java.util.Optional;

/**
 *
 * `save(member)` 회원 저장시 저장된 회원을 반환하는 기능
 * `findById(id)` ID로 회원을 찾는 기능
 * `findByName(name)` 이름으로 회원을 찾는 기능
 * `findAll()`  모든 멤버를 List 형태로 반환하는 기능
 * `optional` java8에 들어가는 기능으로 `findBy..`가
 *  null을 반환할 수 있음으로 null을 그대로 받아오는 방법 대신
 *  요즘은 optional로 감싸서 가져오는 것을 선호
 *
 */
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
