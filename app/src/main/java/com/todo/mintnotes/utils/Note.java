package com.todo.mintnotes.utils;

public class Note {

    private String mNoteText;
    private String date;
    private long mNoteId;

    public Note(){}

    public void setText(String noteText){
        mNoteText = noteText;
    }

    public String getText(){
        return mNoteText;
    }

    public void setDate(String mDate){ date = mDate; }

    public String getDate(){
        return date;
    }

    public void setId(long noteId){
        mNoteId = noteId;
    }

    public long getId(){
        return mNoteId;
    }

}
