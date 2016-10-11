package com.bolyartech.forgeexamples.basic;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.bolyartech.forge.android.app_unit.ResidentComponent;
import com.bolyartech.forge.android.app_unit.UnitActivity;


/**
 * Your unit activities will have to inherit this base class
 *
 * UnitBaseActivity is not included in the library because you may need to chose between AppCompatActivity and Activity.
 * You may have more than one unit base activity depending on your needs.
 */
abstract public class UnitBaseActivity<T extends ResidentComponent> extends AppCompatActivity
        implements UnitActivity<T> {

    private T mResident;


    @Override
    public void setResident(@NonNull T ri) {
        mResident = ri;
    }


    @NonNull
    @Override
    public T getResident() {
        return mResident;
    }


    @NonNull
    @Override
    public T getRes() {
        return getResident();
    }
}
