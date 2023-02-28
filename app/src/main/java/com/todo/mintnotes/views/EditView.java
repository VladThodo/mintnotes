package com.todo.mintnotes.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* Custom EditText used in our EditActivity */

public class EditView extends androidx.appcompat.widget.AppCompatEditText {
    public EditView(@NonNull Context context) {
        super(context);
        this.setBackgroundResource(android.R.color.transparent);
    }

    public EditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setBackgroundResource(android.R.color.transparent);
    }


}
