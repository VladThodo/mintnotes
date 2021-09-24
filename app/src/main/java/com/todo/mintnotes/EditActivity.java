package com.todo.mintnotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.todo.mintnotes.utils.NoteDatabaseItem;
import com.todo.mintnotes.utils.ObjectBox;

import io.objectbox.Box;

public class EditActivity extends AppCompatActivity {

    private AppCompatEditText notesEditText;
    private Box<NoteDatabaseItem> mNotesBox;


    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.edit_activity);
        notesEditText = findViewById(R.id.notesEditText);

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
                mNotesBox.put(note);
                Intent data = new Intent();
                data.putExtra("changed", true);
                setResult(Activity.RESULT_OK, data);
                this.finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
