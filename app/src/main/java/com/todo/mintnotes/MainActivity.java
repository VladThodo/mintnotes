package com.todo.mintnotes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.todo.mintnotes.fragments.EditFragment;
import com.todo.mintnotes.utils.NoteDatabaseItem;
import com.todo.mintnotes.utils.ObjectBox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    FloatingActionButton mainButton;

    EditFragment mEditFragment;

    protected boolean isEdit = false;

    Box<NoteDatabaseItem> mNotesBox = ObjectBox.get().boxFor(NoteDatabaseItem.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bottom navigation init
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        mainButton = findViewById(R.id.action_fab);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEdit) {
                    navController.navigate(R.id.notes_to_edit);
                    isEdit = true;
                } else {
                    setMainNav();
                    if(((MintNotesApp) getApplication()).isNoteTextChanged()){
                        saveCurrentNote();
                    } else {
                        Toasty.normal(getApplicationContext(), "Empty note not saved").show();
                    }
                    navController.navigate(R.id.edit_to_notes);
                    isEdit = false;
                }
            }
        });

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.edit) {
                    setNavForEdit();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(isEdit){
            isEdit = false;
            setMainNav();
        }
        super.onBackPressed();
    }

    /** Handle navigation view item selection and switch fragments accordingly */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Toasty.success(this, item.getTitle(), Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    /* Trying to save current note and then refresh the list somehow */

    public void saveCurrentNote(){
        NoteDatabaseItem note = new NoteDatabaseItem();
        note.setText(((MintNotesApp) this.getApplication()).getCurrentNoteText());
        note.setDate(new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date()));
        if(!isEdit) {
            mNotesBox.put(note);
        } else {
            mNotesBox.put(note);
        }
    }

    public void setNavForEdit(){
        mainButton.setImageResource(R.drawable.baseline_save_24);
        bottomNavigationView.getMenu().clear();
        bottomNavigationView.inflateMenu(R.menu.edit_bottom_menu);
    }

    public void setMainNav(){
        mainButton.setImageResource(R.drawable.ic_edit_dark);
        bottomNavigationView.getMenu().clear();
        bottomNavigationView.inflateMenu(R.menu.bottom_navigation_menu);
    }

}