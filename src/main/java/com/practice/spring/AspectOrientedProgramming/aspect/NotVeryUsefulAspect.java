package com.practice.spring.AspectOrientedProgramming.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by zhaozh on 2015/12/29.
 */
@Aspect
public class NotVeryUsefulAspect {

    @Pointcut("execution(public * com.practice.spring.AspectOrientedProgramming.api.*.find*(..))")
    private void anyOldTransfer(){}

    @Before("anyOldTransfer()")
    public void doAccessCheck(){
        System.out.println("doAccessCheck before is run");
    }

    @After("anyOldTransfer()")
    public void doAfterCheck(){
        System.out.println("doAccessCheck after is run");
    }
}
