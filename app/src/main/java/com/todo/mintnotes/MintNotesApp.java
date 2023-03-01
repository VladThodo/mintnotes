package com.todo.mintnotes;

import android.app.Application;

import com.todo.mintnotes.utils.ObjectBox;

public class MintNotesApp extends Application {

    protected String mNoteText = "";

    protected boolean noteTextChanged = false;

    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.init(this);
    }

    public void setCurrentNoteText(String mText){
        mNoteText = mText;
    }

    public String getCurrentNoteText(){
        return mNoteText;
    }

    public void setNoteTextChanged(boolean noteTextChanged) {
        this.noteTextChanged = noteTextChanged;
    }

    public boolean isNoteTextChanged(){
        return this.noteTextChanged;
    }
}
