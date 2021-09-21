package com.todo.mintnotes.fragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.todo.mintnotes.R;

public class AboutFragment extends MaterialAboutFragment {

    public AboutFragment(){
        super();
    }

    @Override
    @NonNull
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {
        MaterialAboutCard.Builder cardBuilder = new MaterialAboutCard.Builder();
        MaterialAboutTitleItem titleItem = new MaterialAboutTitleItem.Builder()
                .text("Mintnotes")
                .icon(R.drawable.ic_info_outline_white_24dp)
                .build();
        cardBuilder.addItem(titleItem);
        MaterialAboutCard card = cardBuilder.build();

        return new MaterialAboutList.Builder()
                .addCard(card)
                .build();
    }
}

