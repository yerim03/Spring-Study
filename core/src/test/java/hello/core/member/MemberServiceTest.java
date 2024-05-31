package hello.core.member;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    //테스트 실행 전에 appConfig로 memberService
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given - 이러한 환경에서
        Member member = new Member(1L, "member1", Grade.VIP);

        //when - 이렇게 실행했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);   //id=1인 회원조회


        //then - 결과가 이렇게
        Assertions.assertThat(member).isEqualTo(findMember);    //member와 member1이 같은지 확인
    }
}
