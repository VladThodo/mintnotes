package com.todo.mintnotes.utils;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ReminderDatabaseItem {

    @Id
    long reminderId;

    String reminderText;

    String reminderDate;

    public ReminderDatabaseItem(){}

    public long getReminderId() { return this.reminderId; }
}
