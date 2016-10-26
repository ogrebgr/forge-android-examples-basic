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

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    switchToBusyState(Operation.FIRST_OPERATION);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mFirstOperationOutcome =  OperationOutcome.createSuccessOutcome(42);
                    switchToEndedStateSuccess();
                }
            });
            t.start();
        }
    }


    @Override
    public void executeSecondOperation() {
        if (isIdle()) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    switchToBusyState(Operation.SECOND_OPERATION);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mSecondOperationOutcome = OperationOutcome.createSuccessOutcome(3.14f);
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
