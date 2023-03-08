package com.todo.mintnotes.utils;

/* Generic reminder item */

public class Reminder {

    private long reminderId;

    private String reminderText;

    private String reminderDate;


    // Constructors

    public Reminder(){
        this.reminderId = 0;
        this.reminderDate = "";
        this.reminderText = "";
    }

    public Reminder(long reminderId, String reminderText, String reminderDate){
        this.reminderId = reminderId;
        this.reminderText = reminderText;
        this.reminderDate = reminderDate;
    }


    // Getters & setters

    public void setReminderId(long reminderId){
        this.reminderId = reminderId;
    }

    public long getReminderId() { return this.reminderId; }

    public void setReminderText(String reminderText){
        this.reminderText = reminderText;
    }

    public String getReminderText() { return this.reminderText; }

    public void setReminderDate(String reminderDate){
        this.reminderDate = reminderDate;
    }

    public String getReminderDate(){ return this.reminderDate; }
}
