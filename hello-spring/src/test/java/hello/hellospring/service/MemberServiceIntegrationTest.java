package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
/* @Transactional : 테스트 시작 전에 트랜잭션 시작, 테스트 완료후에 항상 롤백
                    -> DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
                    @BeforeEach, @AfterEach 를 사용하지 않아도 됨
*/
@Transactional
class MemberServiceIntegrationTest {

    //스프링 컨테이너에 MemberService와 memberRepository를 받아서 사용한다.

    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;


    //테스트 메서드명은 한글로 바꿔도 상관없다!
    @Test
    void 회원가입() {
        //given(이렇게 주어진 상황에서)
        Member member = new Member();
        member.setName("spring100");

        //when(이것을 실행했을 때)
        Long saveId = memberService.join(member);

        //then(결과가 이렇게 나와야 함)
        //저장한 것이 리포지토리에 있는 것과 같은지 확인
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }


    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //asserThrows를 이용한 예외
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //람다식 안에 로직을 실행했을 때, 앞의 예외가 발생해야 함.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}