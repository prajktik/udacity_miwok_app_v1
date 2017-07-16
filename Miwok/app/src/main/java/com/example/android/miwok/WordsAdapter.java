package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


class WordsAdapter extends ArrayAdapter<Word>{

    private final Context mContext;
    private int mColorId;

    public WordsAdapter(@NonNull Context context,@NonNull List objects, int colorId){
        super(context, 0, objects);
        mColorId = colorId;
        mContext= context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }

        LinearLayout layout = (LinearLayout) listItemView.findViewById(R.id.word_layout);
        int color = ContextCompat.getColor(getContext(), mColorId);
        layout.setBackgroundColor(color);

        Word currentWord = (Word) getItem(position);
        TextView tvDefault = (TextView) listItemView.findViewById(R.id.tv_default_translation);
        tvDefault.setText(currentWord.getDefaultTranslation());

        TextView tvMiwok = (TextView) listItemView.findViewById(R.id.tv_miwok_translation);
        tvMiwok.setText(currentWord.getMiwokTranslation());

        ImageView ivImage = (ImageView) listItemView.findViewById(R.id.iv_image);
        if(currentWord.hasImage()) {
            ivImage.setImageResource(currentWord.getImageId());
        } else {
            ivImage.setVisibility(View.GONE);
        }


        return listItemView;
    }
}
