package com.todo.mintnotes.utils;

import io.objectbox.annotation.DefaultValue;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class NoteDatabaseItem {

    @Id
    long itemId;

    String text;
    String date;

    int pos;

    public void setText(String noteText){
        text = noteText;
    }

    public String getText(){
        return text;
    }

    public void setDate(String mDate){ date = mDate; }

    public String getDate(){ return date; }

    public long getId() { return itemId; }

    public void setPosition(int pos){ this.pos = pos; }

    public int getPosition() { return this.pos; }
}
