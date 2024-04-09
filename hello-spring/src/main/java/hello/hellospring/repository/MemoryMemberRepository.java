package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();   //회원 저장소
    private static long sequence = 0L;  //일련 번호

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //회원 저장 시 일련번호 값(sequence) 1 증가 -> id값 세팅
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //store에서 id를 꺼낸다.
        //null이 반환될 경우를 대비해서 Optional로 감싸서 반환한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //같은 name을 가진 객체를 반환, 없으면 null 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
