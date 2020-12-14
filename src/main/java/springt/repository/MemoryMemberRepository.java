package springt.repository;

import org.springframework.stereotype.Repository;
import springt.domain.Member;
import java.util.*;
/**
 * 실무에서는 동시성 문제가 있을 수 있어서
 * private static store처럼 공유되는
 * 변수일 때는 ConcurrentHashMap을 써야한다.
 * public static long도 동시성 문제를 고려해서
 * AtomicLong 사용도 고려해야 한다.
 */
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    // save할 공간을 MAP으로 key(Long): id, Value(Member): 회원
    // ctrl+space =  import 활용
    private static Map<Long, Member> store = new HashMap<>();

    //0,1,2 키값을 생성해주는 변수
    private static long sequence = 0L;

    //저장 시 sequence plus, and put
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    //store에서 꺼내나, null을 예상해서 optional로 감싸기
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 못찾으면 null을 예상해서 optional로 감싸기
    @Override
    public Optional<Member> findByName(String name) {
        // 요소를 특정 기준으로 걸러내기, 들어온 이름이
        // member의 이름과 같은게 있는지의 여부
        // findAny() : 하나라도 찾는 것, 이 결과가 optional로 반환됨
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 전체 반환
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 비우기
    public void clearStore() {
        store.clear();
    }
}
