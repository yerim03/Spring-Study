package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //aop로 쓰겠다는 의미
@Component  //스프링 빈에 등록
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")   //어디에 적용할 것인지 타겟팅
    public Object excut(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END:" + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
