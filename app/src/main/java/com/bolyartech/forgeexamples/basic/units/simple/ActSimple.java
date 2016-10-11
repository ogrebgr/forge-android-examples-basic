package com.bolyartech.forgeexamples.basic.units.simple;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;

import com.bolyartech.forge.android.app_unit.ResidentComponent;
import com.bolyartech.forgeexamples.basic.UnitBaseActivity;


public class ActSimple extends UnitBaseActivity<ResSimple> {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        // if you call here getResident() it will return null

        super.onCreate(savedInstanceState, persistentState);

        // here you can call getResident() and you will receive it
    }

    @NonNull
    @Override
    public ResidentComponent createResidentComponent() {
        return new ResSimple();
    }



}
