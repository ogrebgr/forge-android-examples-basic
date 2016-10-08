package com.bolyartech.forgeexamples.basic.units.main;

import com.bolyartech.forge.android.app_unit.ResidentComponentAdapter;

import java.util.Random;


/**
 * On creation a random int is generated and kept.
 *
 * Activity can use {@link #getStored()} to retrieve the value or {@link #newValue()} to generate a new one.
 * The purpose of mStored is to show that resident really stays resident while activity is recreated on configuration
 * change
 *
 * Please note that access level is 'default/package' (i.e. no one except the ActMain will use the ResMain)
 */
class ResMain extends ResidentComponentAdapter {
    private final Random mRandom = new Random();
    private int mStored;


    ResMain() {
        newValue();
    }


    void newValue() {
        mStored = mRandom.nextInt();
    }


    int getStored() {
        return mStored;
    }
}
