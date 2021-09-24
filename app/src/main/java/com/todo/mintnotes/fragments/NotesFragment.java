package com.todo.mintnotes.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.todo.mintnotes.EditActivity;
import com.todo.mintnotes.R;
import com.todo.mintnotes.utils.Note;
import com.todo.mintnotes.utils.NoteDatabaseItem;
import com.todo.mintnotes.utils.NotesAdapter;
import com.todo.mintnotes.utils.ObjectBox;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class NotesFragment extends Fragment implements RecyclerView.OnItemTouchListener, NotesAdapter.NotesClickListener {

    public NotesFragment(){
        super(R.layout.notes_fragment);
    }
    private final Context mContext = this.getContext();
    private List<Note> mNotesList = new ArrayList<>();
    NotesAdapter mNotesAdapter = new NotesAdapter(mNotesList, this);
    Box<NoteDatabaseItem> notesBox = ObjectBox.get().boxFor(NoteDatabaseItem.class);

    ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d("DATA","Here's your result");
                    boolean dataChanged = result.getData().getBooleanExtra("changed", false);
                    if(dataChanged){
                        Log.d("DATA", "It changed");
                        updateNotesList();
                    }
                }
            });


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View notesView = inflater.inflate(R.layout.notes_fragment, container, false);

        RecyclerView mRecyclerView = notesView.findViewById(R.id.notes_view);

        setHasOptionsMenu(true);

        for(NoteDatabaseItem note : notesBox.getAll()){
            Note not = new Note();
            not.setText(note.getText());
            not.setDate(note.getDate());
            not.setId(note.getId());
            mNotesList.add(not);
        }

        mRecyclerView.setAdapter(mNotesAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mRecyclerView.addOnItemTouchListener(this);
        return notesView;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void onEditClick(View v, int position) {
        Log.d("Test", "Edit");
        Intent newIntent = new Intent(getContext(), EditActivity.class);
        newIntent.putExtra("text", mNotesList.get(position).getText());
        newIntent.putExtra("isedit", true);
        newIntent.putExtra("id", mNotesList.get(position).getId());
        mGetContent.launch(newIntent);
    }

    @Override
    public void onDeleteClick(View v, int position) {
        Log.d("Test", "Delete");
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(v.getContext())
                .setTitle("Are you sure?")
                .setMessage("Do you want to delete this note?")
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NoteDatabaseItem note = notesBox.getAll().get(position);
                        notesBox.remove(note);
                        updateNotesList();
                    }
                })
                .setNegativeButton(R.string.cancel, null);
        dialogBuilder.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.notes_edit_main:
                mGetContent.launch(new Intent(getContext(), EditActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateNotesList(){
        mNotesList.clear();
        for(NoteDatabaseItem note : notesBox.getAll()){
            Note not = new Note();
            not.setText(note.getText());
            not.setDate(note.getDate());
            not.setId(note.getId());
            mNotesList.add(not);
        }
        mNotesAdapter.notifyDataSetChanged();
    }


}
