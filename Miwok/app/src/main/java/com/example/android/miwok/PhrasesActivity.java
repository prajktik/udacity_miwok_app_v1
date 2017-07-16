package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity{

    private ListClickListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        ArrayList<Word> words = new ArrayList();
        words.add(new Word("Where are you going?", "minto wuksus",Word.NO_IMAGE_PROVIDED,R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase 'nә",Word.NO_IMAGE_PROVIDED,R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...",Word.NO_IMAGE_PROVIDED,R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs ? ",Word.NO_IMAGE_PROVIDED,R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit",Word.NO_IMAGE_PROVIDED,R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs 'aa?",Word.NO_IMAGE_PROVIDED,R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",Word.NO_IMAGE_PROVIDED,R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",Word.NO_IMAGE_PROVIDED,R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis",Word.NO_IMAGE_PROVIDED,R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni 'nem",Word.NO_IMAGE_PROVIDED,R.raw.phrase_come_here));

        WordsAdapter itemsAdapter = new WordsAdapter(this, words, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        mListener = new ListClickListener(this);
        listView.setOnItemClickListener(mListener);

    }

    @Override
    protected void onStop(){
        super.onStop();
        mListener.releaseMediaPlayer();
    }
}
