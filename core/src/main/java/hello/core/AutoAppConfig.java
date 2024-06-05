package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //탐색 시작 위치 지정
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class,   //지정한 클래스의 패키지(hello.core)가 탐색 시작 위치

        //컴포넌트를 사용하면 기존에 만들어둔 AppConfig, TestConfig 설정 정보도 함께 등록됨
        // -> excludeFilters를 사용해서 컴포넌트 스캔 대상에서 제외함(보통 실무에서는 제외시키지 않지만 학습을 위해 제외시킴)
        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)  //자동으로 스프링 빈 등록
public class AutoAppConfig {

    //수동빈 등록 과 자동빈 등록의 충돌 예시
    //수동빈과 자동빈의 이름이 같을 경우, 수동 빈 등록이 우선권을 가진다.
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
