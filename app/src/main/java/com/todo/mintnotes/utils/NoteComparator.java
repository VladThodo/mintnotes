package com.todo.mintnotes.utils;

import java.util.Comparator;

public class NoteComparator implements Comparator<Note> {
    @Override
    public int compare(Note t1, Note t2) {
        return Integer.compare(t1.getPosition(), t2.getPosition());
    }
}
