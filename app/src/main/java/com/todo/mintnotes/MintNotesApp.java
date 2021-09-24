package com.todo.mintnotes;

import android.app.Application;

import com.todo.mintnotes.utils.ObjectBox;

public class MintNotesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.init(this);
    }
}
