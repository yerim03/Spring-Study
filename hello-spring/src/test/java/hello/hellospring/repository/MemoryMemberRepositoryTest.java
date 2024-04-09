package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //모든 테스트는 순서와 상관없이 메소드별로 따로 실행되어야 함
    //한 테스트가 끝날때마다 리포지토리를 지워주는 역할
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    //save 메서드 테스트
    //저장이 잘 되는지 테스트한다.
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  //findById의 반환타입은 Optional -> Optional에서 .get()으로 값을 꺼낸다.

        assertThat(member).isEqualTo(result);
    }


    //findByID 테스트
    //이름으로 찾기 기능이 잘 되는지 테스트한다.
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }


    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
