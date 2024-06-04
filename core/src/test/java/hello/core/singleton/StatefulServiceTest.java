package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10000원 주문
//        statefulService1.order("userA", 10000);
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
//        statefulService2.order("userB", 20000);
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA: A사용자 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price); //10000원을 기대했지만 20000원이 출력 -> statefulService1, statefulService2는 같은 객체를 사용하기 때문
        System.out.println("price = " + userAPrice); //10000원 출력

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    //statefulSerivce 전용 testConfig 생성
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}