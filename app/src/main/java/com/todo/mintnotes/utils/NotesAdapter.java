package com.todo.mintnotes.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.todo.mintnotes.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> mNotesList;

    public NotesAdapter(List<Note> notesList){
        mNotesList = notesList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView dateView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.notes_text);
            dateView = view.findViewById(R.id.date_text);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater mLayouInflater = LayoutInflater.from(context);

        View notesView = mLayouInflater.inflate(R.layout.notes_item, parent, false);

        return new ViewHolder(notesView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        TextView noteText = holder.textView;
        TextView dateText = holder.dateView;
        noteText.setText(mNotesList.get(position).getText());
        dateText.setText("10-09-2021 13:53");
    }

    @Override
    public int getItemCount() {
        return mNotesList.size();
    }
}
