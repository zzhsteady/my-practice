package com.practice.nomal.event;

import java.util.Date;
import java.util.EventObject;

/**
 * Created by zhaozh on 2016/1/23.
 */
public class XEvent extends EventObject{

    String customer;
    int banlance;
    Date dateWidthraw;

    public XEvent(Object source, String customer, int banlance, Date dateWidthraw) {
        super(source);
        this.customer = customer;
        this.banlance = banlance;
        this.dateWidthraw = dateWidthraw;
    }
}
