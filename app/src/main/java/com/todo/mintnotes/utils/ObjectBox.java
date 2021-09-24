package com.todo.mintnotes.utils;

import android.content.Context;

import io.objectbox.BoxStore;

public class ObjectBox {
    private static BoxStore sBoxStore;

    public static void init(Context mContext){
        sBoxStore = MyObjectBox.builder().androidContext(mContext.getApplicationContext()).build();
    }

    public static BoxStore get() { return sBoxStore; }
}
