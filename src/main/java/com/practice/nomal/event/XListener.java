package com.practice.nomal.event;

import java.util.EventListener;

/**
 * Created by zhaozh on 2016/1/23.
 */
public interface XListener extends EventListener{

    void handlerMethod1(XEvent e);
    void handlerMethod2(XEvent e);
}
