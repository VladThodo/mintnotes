package com.todo.mintnotes.utils;

public class Note {

    private String mNoteText;
    private int mNoteId;

    public Note(){}

    public void setText(String noteText){
        mNoteText = noteText;
    }

    public String getText(){
        return mNoteText;
    }

    public void setDate(){}

    public String getDate(){
        return "";
    }

    public void setId(int noteId){
        mNoteId = noteId;
    }

    public int getId(){
        return mNoteId;
    }

}
