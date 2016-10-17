package com.bolyartech.forgeexamples.basic;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.bolyartech.forge.android.app_unit.ResidentComponent;
import com.bolyartech.forge.android.app_unit.UnitActivity;
import com.bolyartech.forge.android.app_unit.UnitActivityDelegate;


/**
 * Your unit activities will have to inherit this base class
 *
 * UnitBaseActivity is not included in the library because you may need to chose between AppCompatActivity and Activity.
 * You may have more than one unit base activity depending on your needs.
 */
abstract public class UnitBaseActivity<T extends ResidentComponent> extends AppCompatActivity
        implements UnitActivity<T> {

    private UnitActivityDelegate<T> mDelegate = new UnitActivityDelegate<>();


    @Override
    public void setResident(@NonNull T resident) {
        mDelegate.setResident(resident);
    }


    @Override
    @NonNull
    public T getResident() {
        return mDelegate.getResident();
    }


    @Override
    @NonNull
    public T getRes() {
        return mDelegate.getRes();
    }
}

