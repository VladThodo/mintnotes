package com.todo.mintnotes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.todo.mintnotes.R;
import com.todo.mintnotes.utils.Reminder;
import com.todo.mintnotes.utils.RemindersAdapter;

import java.util.ArrayList;
import java.util.List;

public class RemindersFragment extends Fragment {

    public RemindersFragment(){
        super(R.layout.reminders_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View reminderView = inflater.inflate(R.layout.reminders_fragment, container, false);
        RecyclerView remindersView = reminderView.findViewById(R.id.reminders_view);

        List<Reminder> reminders = new ArrayList<Reminder>();

        reminders.add(new Reminder(12, "aa", "bb"));
        reminders.add(new Reminder(12, "aa", "bb"));
        reminders.add(new Reminder(12, "aa", "bb"));

        remindersView.setAdapter(new RemindersAdapter(reminders));
        remindersView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        return reminderView;
    }

}
