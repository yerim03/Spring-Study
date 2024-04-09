package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Configuration
public class SpringConfig {

    /*private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    /*
    //JpaMemberRepository에 사용
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */


    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //@bean 애노테이션으로 MemberService를 스프링 빈에 등록
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    //timeTraceAop aop를 스프링 빈에 등록
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

    //레포지토리를 변경할 때, 다른 부분은 전혀 수정하지 않고, 이부분에서 사용할 Repository만 변경해주면 된다.
    //SpringDataJpa가 자동으로 스프링 빈에 등록해주기 때문에 여기서 스프링 빈에 등록할 필요가 없다.
//    @Bean
//    public MemberRepository memberRepository() {
//       /*//@bean 애노테이션으로 memberRepository를 스프링 빈에 등록
//        return new MemoryMemberRepository();*/
//
//        /*
//        //JdbcMemberRepository를 스프링 빈에 등록
//        return new JdbcMemberRepository(dataSource);
//        */
//
//        /*//JdbcTemplateMemberRepository를 스프링 빈에 등록
//        return new JdbcTemplateMemberRepository(dataSource);*/
//
//        /*
//        //JpaMemberRepository를 스프링 빈에 등록
//        return new JpaMemberRepository(em);
//        */
//    }
}
