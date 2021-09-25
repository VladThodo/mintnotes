package com.todo.mintnotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.todo.mintnotes.utils.NoteDatabaseItem;
import com.todo.mintnotes.utils.ObjectBox;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.objectbox.Box;

public class EditActivity extends AppCompatActivity {

    private AppCompatEditText notesEditText;
    private Box<NoteDatabaseItem> mNotesBox;
    private boolean isEdit = false;
    private long noteEditId;


    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.edit_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            notesEditText = findViewById(R.id.notesEditText);
            notesEditText.setText(getIntent().getExtras().getString("text", ""));
            isEdit = getIntent().getExtras().getBoolean("isedit", false);
            noteEditId = getIntent().getExtras().getLong("id", 0);
        } catch (Exception e){
            Log.d("Error", e.toString());
        }
        mNotesBox = ObjectBox.get().boxFor(NoteDatabaseItem.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.notes_save:
                NoteDatabaseItem note = new NoteDatabaseItem();
                note.setText(notesEditText.getText().toString());
                note.setDate(new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date()));
                if(!isEdit) {
                    mNotesBox.put(note);
                } else {
                    mNotesBox.remove(noteEditId);
                    mNotesBox.put(note);
                }
                Intent data = new Intent();
                data.putExtra("changed", true);
                setResult(Activity.RESULT_OK, data);
                this.finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
