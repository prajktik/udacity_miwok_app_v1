package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;

public class ListClickListener implements OnItemClickListener{

    private MediaPlayer mMediaPlayer;
    private Context mContext;
    private AudioManager mAudioManager;

    public ListClickListener(Context mContext){

        this.mContext = mContext;
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){

        ListView list = (ListView) parent;
        WordsAdapter adapter = (WordsAdapter) list.getAdapter();
        Word word = adapter.getItem(position);
        int rawResId = word.getmRawResId();
        releaseMediaPlayer();

        // Request audio focus for playback
        int result = mAudioManager.requestAudioFocus(afChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // Start playback
            mMediaPlayer = MediaPlayer.create(mContext, rawResId);
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
        }

    }

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp){
            releaseMediaPlayer();
        }
    };


    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener(){

        @Override
        public void onAudioFocusChange(int focusChange){

            switch(focusChange){
                case AUDIOFOCUS_GAIN:{
                    if(mMediaPlayer != null)
                        mMediaPlayer.start();
                }
                break;
                case AUDIOFOCUS_LOSS:{
                    if(mMediaPlayer != null)
                        mMediaPlayer.pause();
                    releaseMediaPlayer();
                }
                break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:{
                    if(mMediaPlayer != null){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                }
                break;
            }

        }
    };

    void releaseMediaPlayer(){
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }

}
