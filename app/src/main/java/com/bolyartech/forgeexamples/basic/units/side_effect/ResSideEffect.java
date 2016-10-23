package com.bolyartech.forgeexamples.basic.units.side_effect;

import com.bolyartech.forge.android.app_unit.SideEffectOperationResidentComponent;
import com.bolyartech.forgeexamples.basic.data.ExampleData;


public interface ResSideEffect extends SideEffectOperationResidentComponent<ExampleData, Void> {
    void retrieveExampleData();
}
