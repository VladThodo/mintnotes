package com.todo.mintnotes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.todo.mintnotes.R;
import com.todo.mintnotes.utils.Note;
import com.todo.mintnotes.utils.NotesAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment implements RecyclerView.OnItemTouchListener {

    public NotesFragment(){
        super(R.layout.notes_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View notesView = inflater.inflate(R.layout.notes_fragment, container, false);

        RecyclerView mRecyclerView = notesView.findViewById(R.id.notes_view);

        Note note1 = new Note();
        note1.setText("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters");
        Note note2 = new Note();
        note2.setText("it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text");
        Note note3 = new Note();
        note3.setText("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour");
        Note note4 = new Note();
        note4.setText("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old");
        Note note5 = new Note();
        note5.setText("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. ");
        List<Note> notesList = new ArrayList<Note>();

        notesList.add(note1);
        notesList.add(note2);
        notesList.add(note3);
        notesList.add(note4);
        notesList.add(note5);
        notesList.add(note3);

        NotesAdapter notesAdapter = new NotesAdapter(notesList);

        mRecyclerView.setAdapter(notesAdapter);
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

}
