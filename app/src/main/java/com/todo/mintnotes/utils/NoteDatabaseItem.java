package com.todo.mintnotes.utils;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class NoteDatabaseItem {

    @Id
    long itemId;

    String text;
    String date;

    public void setText(String noteText){
        text = noteText;
    }

    public String getText(){
        return text;
    }

}
