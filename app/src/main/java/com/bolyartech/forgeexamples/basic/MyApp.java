package com.bolyartech.forgeexamples.basic;


import com.bolyartech.forge.android.app_unit.UnitApplication;


/**
 * My application
 * We must inherit {@link UnitApplication} in order forge units to work
 * We don't override onCreate() because we will use the default UnitManager and TimeProvider
 */
public class MyApp extends UnitApplication {

}
