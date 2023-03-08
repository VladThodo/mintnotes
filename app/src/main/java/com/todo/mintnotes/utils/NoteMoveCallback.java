package com.todo.mintnotes.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;

import io.objectbox.Box;

public class NoteMoveCallback extends ItemTouchHelper.SimpleCallback {

    Box<NoteDatabaseItem> notesBox = ObjectBox.get().boxFor(NoteDatabaseItem.class);

    private NotesGesturesListener mGestureListener;

    public interface NotesGesturesListener {
        void onNoteDeleted(int position);
        void onNoteMoved(int newPosition, int formerPosition, long noteId);

        void onNoteSelected(int notePosition);
    }

    /**
     * Creates a Callback for the given drag and swipe allowance. These values serve as
     * defaults
     * and if you want to customize behavior per ViewHolder, you can override
     * {@link #getSwipeDirs(RecyclerView, ViewHolder)}
     * and / or {@link #getDragDirs(RecyclerView, ViewHolder)}.
     *
     * @param dragDirs  Binary OR of direction flags in which the Views can be dragged. Must be
     *                  composed of {@link #LEFT}, {@link #RIGHT}, {@link #START}, {@link
     *                  #END},
     *                  {@link #UP} and {@link #DOWN}.
     * @param swipeDirs Binary OR of direction flags in which the Views can be swiped. Must be
     *                  composed of {@link #LEFT}, {@link #RIGHT}, {@link #START}, {@link
     *                  #END},
     *                  {@link #UP} and {@link #DOWN}.
     */
    public NoteMoveCallback(int dragDirs, int swipeDirs, NotesGesturesListener mListener) {
        super(dragDirs, swipeDirs);
        this.mGestureListener = mListener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        final int from = viewHolder.getAbsoluteAdapterPosition();
        final int to   = target.getAbsoluteAdapterPosition();
        final long noteId = notesBox.getAll().get(from).getId();
        mGestureListener.onNoteMoved(to, from, noteId);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        mGestureListener.onNoteDeleted(viewHolder.getAbsoluteAdapterPosition());
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }
}
