package com.todo.mintnotes.utils;

import android.content.Context;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.todo.mintnotes.R;

import java.util.Collections;
import java.util.List;

import io.noties.markwon.Markwon;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> mNotesList;
    private NotesClickListener buttonClickHandler;

    public NotesAdapter(List<Note> notesList, NotesClickListener mListener){
        mNotesList = notesList;
        buttonClickHandler = mListener;
    }

    public interface NotesClickListener {
        void onEditClick(View v, int position);
        void onDeleteClick(View v, int position);
        void onListClick(View v, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView dateView;

        private final CardView cardView;

        private final ConstraintLayout constraintLayout;
        private NotesClickListener notesClickListener;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.notes_text);
            dateView = view.findViewById(R.id.date_text);
            constraintLayout = view.findViewById(R.id.main_layout);
            cardView = view.findViewById(R.id.card_view);

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
        public TextView getDateView(){ return dateView;  }
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
        TextView noteText = holder.getNoteView();
        TextView dateText = holder.getDateView();

        final Markwon markwon = Markwon.create(holder.getNoteView().getContext());

        final Spanned markdown = markwon.toMarkdown(mNotesList.get(position).getText());

        noteText.setText(markdown);
        dateText.setText(mNotesList.get(position).getDate());
        holder.notesClickListener = this.buttonClickHandler;
    }

    @Override
    public int getItemCount() {
        return mNotesList.size();
    }




}
