package com.bolyartech.forgeexamples.basic;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.bolyartech.forge.android.app_unit.ResidentComponent;
import com.bolyartech.forge.android.app_unit.UnitActivity;


/**
 * Created by ogre on 2016-01-10 12:45
 */
abstract public class UnitBaseActivity<T extends ResidentComponent>
        extends AppCompatActivity implements UnitActivity<T> {

    private T mResidentInterface;


    @Override
    public void setResident(@NonNull T ri) {
        mResidentInterface = ri;
    }


    @NonNull
    @Override
    public T getResident() {
        return mResidentInterface;
    }


    @NonNull
    @Override
    public T getRes() {
        return getResident();
    }
}
