package springt.repository;

import springt.domain.Member;
import java.util.*;
/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository {

    // save할 공간을 MAP으로 키는 회원 아이디니까 LONG? 값은 멤버?
    // 컨트롤 스페이스 하고 import해주면 됨
    // 아이디는 여기서 세팅을 해주는거지
    private static Map<Long, Member> store = new HashMap<>();

    //0,1,2 키값을 만들어주는 애라고 보면 됨
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 시퀀스 값을 하나 올려주고
        store.put(member.getId(), member); // 이제 저장
        return member;
    }

    //결과가 없으면 null 일수 있으니 optional로 감싸는거!
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 끝까지 없으면 null로 감싸서 올거
    @Override
    public Optional<Member> findByName(String name) {
        //루프를 돌린다..? 람다...? 파라미터로 넘어온 name이랑 같은지
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 이제 전부 가져오는 거지
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
