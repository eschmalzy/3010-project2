package com.example.craft.budget2;

import java.util.UUID;

/**
 * Created by craft on 2/15/2017.
 */

public class Budget {
    private UUID mId;
    private String mTitle;
    private int mAmount;

    public Budget(){
        mId = UUID.randomUUID();
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

    public void setAmount(int amount){
        mAmount = amount;
    }

    public int getAmount(){return mAmount;}

}
