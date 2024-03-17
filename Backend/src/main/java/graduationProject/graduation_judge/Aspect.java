package graduationProject.graduation_judge;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class Aspect
{
    @Around("execution(* * com.test.aop.controller.*(..))")
    public Object executionAspect(ProceedingJoinPoint joinPoint) throws Throwable
    {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

        return result;
    }
}
