package com.bolyartech.forgeexamples.basic.units.realistic;

import com.bolyartech.forge.android.app_unit.ResidentComponent;
import com.bolyartech.forgeexamples.basic.data.ExampleData;


interface ResRealistic extends ResidentComponent {
    void retrieveExampleData();

    ExampleData getExampleData();
}
