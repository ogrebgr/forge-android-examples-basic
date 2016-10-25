package com.bolyartech.forgeexamples.basic.units.multi_operation;

import com.bolyartech.forge.android.app_unit.AbstractMultiOperationResidentComponent;
import com.bolyartech.forge.android.app_unit.OperationOutcome;


public class ResMultiOperationImpl extends AbstractMultiOperationResidentComponent<Operation>
        implements ResMultiOperation {

    private OperationOutcome<Integer, Void> mFirstOperationOutcome;
    private OperationOutcome<Float, Void> mSecondOperationOutcome;


    @Override
    public void executeFirstOperation() {
        if (isIdle()) {
            switchToBusyState(Operation.FIRST_OPERATION);

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mFirstOperationOutcome = new OperationOutcome<>(true, 42, null);
                    switchToEndedStateSuccess();
                }
            });
            t.start();
        }
    }


    @Override
    public void executeSecondOperation() {
        if (isIdle()) {
            switchToBusyState(Operation.SECOND_OPERATION);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mSecondOperationOutcome = new OperationOutcome<>(true, 3.14f, null);
                    switchToEndedStateSuccess();
                }
            });
            t.start();
        }
    }


    @Override
    public OperationOutcome<Integer, Void> getFirstOperationOutcome() {
        return mFirstOperationOutcome;
    }


    @Override
    public OperationOutcome<Float, Void> getSecondOperationOutcome() {
        return mSecondOperationOutcome;
    }
}
