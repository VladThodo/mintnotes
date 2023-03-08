package com.todo.mintnotes.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.todo.mintnotes.R;

import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ViewHolder> {

    private List<Reminder> mRemindersList;

    public RemindersAdapter(List<Reminder> mRemindersList){
        this.mRemindersList = mRemindersList;
    }

    @NonNull
    @Override
    public RemindersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View remindersView = layoutInflater.inflate(R.layout.reminders_item, parent, false);

        return new ViewHolder(remindersView);
    }

    @Override
    public void onBindViewHolder(@NonNull RemindersAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mRemindersList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
