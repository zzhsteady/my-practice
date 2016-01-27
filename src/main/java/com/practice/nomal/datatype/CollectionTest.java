package com.practice.nomal.datatype;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by zhaozh on 2016/1/26.
 */
public class CollectionTest {

    @Test
    public void QueueTest(){
        Collection<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        Deque<String> queue = new LinkedList();
        queue.addAll(list);
        Assert.assertEquals("1",queue.pollFirst());
        Assert.assertEquals("3",queue.pollLast());
        for (String e:queue){
            System.out.println(e);
        }

        Stack<String> stack = new Stack();
        stack.addAll(list);
        Assert.assertEquals("3",stack.pop());
        for (String e:stack){
            System.out.println(e);
        }

    }
}
