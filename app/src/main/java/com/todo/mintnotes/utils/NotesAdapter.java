package com.todo.mintnotes.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.todo.mintnotes.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.noties.markwon.Markwon;
import io.objectbox.Box;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> implements NoteMoveCallback.NotesGesturesListener {

    private List<Note> mNotesList;
    private NotesClickListener buttonClickHandler;
    NoteMoveCallback mNoteMove = new NoteMoveCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT, this);
    ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mNoteMove);

    Box<NoteDatabaseItem> notesBox = ObjectBox.get().boxFor(NoteDatabaseItem.class);

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public NotesAdapter(List<Note> notesList, NotesClickListener mListener) {
        mNotesList = notesList;
        buttonClickHandler = mListener;
    }

    @Override
    public void onNoteDeleted(int position) {

    }

    @Override
    public void onNoteMoved(int newPosition, int formerPosition, long noteId) {

        if (formerPosition < newPosition) {
            for (int i = formerPosition; i < newPosition; i++) {
                Collections.swap(mNotesList, i, i + 1);
            }
        } else {
            for (int i = formerPosition; i > newPosition; i--) {
                Collections.swap(mNotesList, i, i - 1);
            }
        }

        for (int i = 0; i < mNotesList.size(); i++) {
            NoteDatabaseItem noteDb = notesBox.get(mNotesList.get(i).getId());
            noteDb.setPosition(i);
            notesBox.put(noteDb);
        }

        notifyItemMoved(newPosition, formerPosition);
    }

    @Override
    public void onNoteSelected(int notePosition) {
        Log.d("Selected note at position: ", String.valueOf(notePosition));
    }

    public interface NotesClickListener {
        void onEditClick(View v, int position);

        void onDeleteClick(View v, int position);

        void onListClick(View v, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView dateView;

        private final View timeView;

        private NotesClickListener notesClickListener;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.notes_text);
            dateView = view.findViewById(R.id.date_text);
            timeView = view.findViewById(R.id.time_view);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notesClickListener.onListClick(view, getAdapterPosition());
                }
            });
        }

        public TextView getNoteView() {
            return textView;
        }

        public TextView getDateView() {
            return dateView;
        }

        public View getTimeView(){ return timeView; }
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);

        View notesView = mLayoutInflater.inflate(R.layout.notes_item, parent, false);

        return new ViewHolder(notesView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        TextView noteText = holder.getNoteView();
        TextView dateText = holder.getDateView();
        View timeView     = holder.getTimeView();

        final Markwon markwon = Markwon.create(holder.getNoteView().getContext());

        final Spanned markdown = markwon.toMarkdown(mNotesList.get(position).getText());

        noteText.setText(markdown);
        dateText.setText(mNotesList.get(position).getDate());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        String dateNow = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date());

        try {
            Date d1 = dateFormat.parse(mNotesList.get(position).getDate());
            Date d2 = dateFormat.parse(dateNow);

            if(TimeUnit.MILLISECONDS.toMinutes(d2.getTime() - d1.getTime()) > 5){
                timeView.setBackgroundColor(Color.YELLOW);
            }

            if(TimeUnit.MILLISECONDS.toMinutes(d2.getTime() - d1.getTime()) > 10){
                timeView.setBackgroundColor(Color.RED);
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        holder.notesClickListener = this.buttonClickHandler;
    }

    @Override
    public int getItemCount() {
        return mNotesList.size();
    }
}
