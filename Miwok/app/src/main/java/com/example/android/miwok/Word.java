package com.example.android.miwok;


public class Word{

    static final int NO_IMAGE_PROVIDED = -1;
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageId;
    private int mRawResId;


    public Word(String defaultTranslation, String miwokTranslation, int imageId, int rawResId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageId = imageId;
        mRawResId = rawResId;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    public int getImageId(){
        return mImageId;
    }

    public int getmRawResId(){
        return mRawResId;
    }

    public boolean hasImage(){
        if(mImageId != NO_IMAGE_PROVIDED){
            return true;
        } else {
            return false;
        }
    }
}