package com.todo.mintnotes;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.todo.mintnotes.utils.NoteDatabaseItem;
import com.todo.mintnotes.utils.ObjectBox;

import io.noties.markwon.Markwon;
import io.objectbox.Box;

public class NoteViewActivity extends AppCompatActivity {

    private long mCurrentNoteId;
    Box<NoteDatabaseItem> notesBox = ObjectBox.get().boxFor(NoteDatabaseItem.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_view);

        try {
           mCurrentNoteId = getIntent().getExtras().getLong("noteid", 0);
        } catch (Exception e){
            Log.d("Error recieving note ID", e.toString());
            Toast.makeText(this, "Couldn' open note", Toast.LENGTH_LONG).show();
            this.finish();
        }

      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView = findViewById(R.id.noteViewTextView);
        Markwon markwon = Markwon.create(this);
        markwon.setMarkdown(textView, notesBox.get(mCurrentNoteId).getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
