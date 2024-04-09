package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;



class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    /*이렇게 하면 MemberSerivce에서의 MemoryMemberRepository 와 MemberServiceTest에서의 MemoryMemberRepository가 다른 리포지토리가 된다.
     -> 같은 리포지토리를 사용하도록 바꿔주는게 좋다!
     */
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();


    //같은 리포지토리 사용
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    //테스트 메서드명은 한글로 바꿔도 상관없다!
    @Test
    void 회원가입() {
        //given(이렇게 주어진 상황에서)
        Member member = new Member();
        member.setName("hello");

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


        //try-catch를 이용한 예외
        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then


    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}