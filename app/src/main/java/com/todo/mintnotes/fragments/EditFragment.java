package com.todo.mintnotes.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.todo.mintnotes.MintNotesApp;
import com.todo.mintnotes.R;
import com.todo.mintnotes.utils.NoteDatabaseItem;
import com.todo.mintnotes.utils.ObjectBox;
import com.todo.mintnotes.views.EditView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;

import io.noties.markwon.Markwon;
import io.noties.markwon.editor.MarkwonEditor;
import io.noties.markwon.editor.MarkwonEditorTextWatcher;
import io.objectbox.Box;

public class EditFragment extends Fragment {

    protected EditView mEditView;
    protected boolean isEdit = false;

    Box<NoteDatabaseItem> mNotesBox = ObjectBox.get().boxFor(NoteDatabaseItem.class);

    public EditFragment(){
       super(R.layout.edit_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View editView = inflater.inflate(R.layout.edit_fragment, container, false);
        ((MintNotesApp) getActivity().getApplication()).setNoteTextChanged(false);
        mEditView = editView.findViewById(R.id.notesEditText);
        Markwon markwon = Markwon.create(getContext());
        MarkwonEditor mEditor = MarkwonEditor.create(markwon);

        mEditView.addTextChangedListener(MarkwonEditorTextWatcher.withPreRender(mEditor, Executors.newCachedThreadPool(), mEditView));

        mEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ((MintNotesApp) getActivity().getApplication()).setCurrentNoteText(mEditView.getText().toString());
                ((MintNotesApp) getActivity().getApplication()).setNoteTextChanged(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return editView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
