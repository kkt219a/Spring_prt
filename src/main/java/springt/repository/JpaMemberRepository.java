package springt.repository;

import springt.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //JPA는 EntityManager로 모든게 동작
    //build.gradle- jpa library를 설정했기 때문에
    //spring boot가 자동으로 현재 DB와 연결을 다 해서 EntityManager를 생성
    //그래서 만들어진 걸 Injection 받으면 되는 것.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    // insert도 하고 member에 setting도 해서 반환
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    //jpql이란 객체지향을 짜야한다.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    //객체를 대상으로 sql을 날린다. 그럼 sql로 번역된다.
    // *을 안쓰고 entity 자체(m)를 select 한다.
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
