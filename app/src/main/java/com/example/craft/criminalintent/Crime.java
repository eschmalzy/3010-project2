package com.example.craft.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by craft on 2/15/2017.
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    private boolean mSolved;
    private Date mDate;

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId(){
        return mId;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String title){
        mTitle = title;
    }

    public void setSolved(boolean solved){
        mSolved = solved;
    }

    public boolean isSolved(){
        return mSolved;
    }

    public Date getDate(){
        return mDate;
    }

    public void setDate(Date date){
        mDate = date;
    }

}
