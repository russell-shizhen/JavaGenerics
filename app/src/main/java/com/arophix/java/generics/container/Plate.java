/*
 * -----------------------------------------------------------------------------
 *
 * Copyright (c) 2019.   -  Zhang Shizhen
 *
 * -----------------------------------------------------------------------------
 * @Author: Zhang Shizhen
 * @Contact: shizhen.chn@gmail.com
 * -----------------------------------------------------------------------------
 */

package com.arophix.java.generics.container;

public class Plate <T> {

    private T mItem;
    
    public Plate(T t) {
        mItem = t;
    }
    
    public void setItem(T items) {
        mItem = items;
    }
    
    public T getItem() {
        return mItem;
    }
}
