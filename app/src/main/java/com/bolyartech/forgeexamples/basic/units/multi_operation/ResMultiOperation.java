package com.bolyartech.forgeexamples.basic.units.multi_operation;

import com.bolyartech.forge.android.app_unit.MultiOperationResidentComponent;
import com.bolyartech.forge.android.app_unit.OperationOutcome;


public interface ResMultiOperation extends MultiOperationResidentComponent<Operation> {

    void executeFirstOperation();
    void executeSecondOperation();

    OperationOutcome<Integer, Void> getFirstOperationOutcome();
    OperationOutcome<Float, Void> getSecondOperationOutcome();
}
