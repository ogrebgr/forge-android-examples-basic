package com.bolyartech.forgeexamples.basic;

import com.bolyartech.forge.android.app_unit.OperationResidentComponent;
import com.bolyartech.forge.android.app_unit.ResidentComponent;


abstract public class OpActivity<T extends ResidentComponent> extends UnitBaseActivity<T>
        implements OperationResidentComponent.Listener {


    @Override
    public void onResidentOperationStateChanged() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                handleState();
            }
        });
    }


    abstract protected void handleState();
}
