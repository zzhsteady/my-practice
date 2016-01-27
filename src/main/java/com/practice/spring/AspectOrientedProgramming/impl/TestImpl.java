package com.practice.spring.AspectOrientedProgramming.impl;

import com.practice.spring.AspectOrientedProgramming.api.TestInterface;
import org.springframework.stereotype.Component;

/**
 * Created by zhaozh on 2015/12/29.
 */
@Component
public class TestImpl implements TestInterface{

    @Override
    public void findByEveryThing() {
        System.out.println("test");
    }
}
