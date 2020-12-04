package org.example.crudoperations.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* org.example.crudoperations.controllers.*.*(..))")
    private void forControllerPackage() {
        //  pointcut declarations for all classes methods in contoller package
    }


    @Pointcut("execution(* org.example.crudoperations.service.*.*(..))")
    private void forServicePackage() {
        //  pointcut declarations for all classes methods in service package

    }

    @Pointcut("within(org.example.crudoperations.repository.*Repository+)")
    private void forRepositoryPackage() {
        //  pointcut declarations for all classes methods which implement Repository interfaces

    }

    @Pointcut("forControllerPackage() || forServicePackage() || forRepositoryPackage()")
    private void forAppFlow() {
        //  pointcut declarations for all classes methods in contoller package ,service package ,repository package
    }

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {

        // display method we are calling
        String theMethod = theJoinPoint.getSignature().toString();
        log.info(" @Before: calling method: " + theMethod);

        // get the arguments
        Object[] args = theJoinPoint.getArgs();

        // loop through and display args
        for (Object arg : args) {
            log.info("argument: " + arg);
        }

    }


    // add @AfterReturning advice
    @AfterReturning(
            pointcut="forAppFlow()",
            returning="theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

        // display method we are returning from
        String theMethod = theJoinPoint.getSignature().toShortString();
        log.info(" in @AfterReturning: from method: " + theMethod);

        // display data returned
        log.info("=====>> result: " + theResult);

    }
    @AfterThrowing(pointcut = "forAppFlow()",
            throwing = "error")
    public void afterThrowingAdvice(JoinPoint theJoinPoint, Throwable error){
        String theMethod = theJoinPoint.getSignature().toShortString();

        log.info(" in @AfterThrowing: from method: " + theMethod);
        log.info(" in @AfterThrowing: exception : " + error);

    }
}
