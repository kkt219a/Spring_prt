package springt.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//AOP는 `@aspect` annotation을 사용해줘야한다.
@Aspect
@Component
public class TimeTraceAop {

    //Around로 어디다가 적용할지 targeting으로 지정
    // springt를 포함한 하위에 적용. spring 뒤에 class등 입력 가능
    @Around("execution(* springt..*(..))")
    //호출이 될 때마다 joinpoint를 쓰고, 원하는 걸 조작이 가능
    // 메소드 호출할 때 마다 중간에서 intercept 가 걸리는 것
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            //다음 method 진행, 이거 말고도 넘어가지 않게 하거나
            // 다양한 함수를 사용 가능
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}

