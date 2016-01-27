package com.practice.nomal;

import org.junit.Test;

import java.util.Random;

/**
 * Created by zhaozh on 2015/12/26.
 */
public class RandomTest {

    @Test
    public void test(){

        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0; i < 6 ; i++){
            stringBuffer.append(random.nextInt(9));
        }

        System.out.println(stringBuffer.toString());
    }

}
