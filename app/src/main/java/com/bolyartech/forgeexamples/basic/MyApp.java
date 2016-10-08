package com.bolyartech.forgeexamples.basic;


import com.bolyartech.forge.android.app_unit.UnitApplication;
import com.bolyartech.forge.android.app_unit.UnitManagerImpl;
import com.bolyartech.forge.base.misc.TimeProviderImpl;


public class MyApp extends UnitApplication {
    @Override
    public void onCreate() {
        setUnitManager(new UnitManagerImpl());
        setTimeProvider(new TimeProviderImpl());
        super.onCreate();
    }
}
