package hello.spring.repository;

import hello.spring.domain.Member;
import lombok.AllArgsConstructor;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
@AllArgsConstructor
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> storage = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        storage.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(storage.values());
    }
    @Override
    public Optional<Member> findByName(String name) {
        return storage.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    public void clearStorage() {
        storage.clear();
    }
}