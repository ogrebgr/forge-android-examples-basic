package com.bolyartech.forgeexamples.basic.units.main;

import com.bolyartech.forge.android.app_unit.ResidentComponentAdapter;

import java.util.Random;


public class ResMain extends ResidentComponentAdapter {
    private final Random mRandom = new Random();
    private int mStored;


    public ResMain() {
        newValue();
    }


    public void newValue() {
        mStored = mRandom.nextInt();
    }


    public int getStored() {
        return mStored;
    }

}
