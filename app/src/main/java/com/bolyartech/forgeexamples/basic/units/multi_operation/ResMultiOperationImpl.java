package com.bolyartech.forgeexamples.basic.units.multi_operation;

import com.bolyartech.forge.android.app_unit.AbstractMultiOperationResidentComponent;
import com.bolyartech.forge.android.app_unit.OperationOutcome;


public class ResMultiOperationImpl extends AbstractMultiOperationResidentComponent<Operation>
        implements ResMultiOperation {

    private OperationOutcome<Integer, Void> mFirstOperationOutcome;
    private OperationOutcome<Float, Void> mSecondOperationOutcome;


    @Override
    public void executeFirstOperation() {
        switchToBusyState(Operation.FIRST_OPERATION);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                onFirstOperationCompleted(42);
            }
        });
        t.start();
    }


    private void onFirstOperationCompleted(int i) {
        mFirstOperationOutcome = new OperationOutcome<>(true, 42, null);
        switchToEndedStateSuccess();
    }


    @Override
    public void executeSecondOperation() {
        switchToBusyState(Operation.SECOND_OPERATION);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                onSecondOperationCompleted(3.14f);
            }
        });
        t.start();
    }


    @Override
    public OperationOutcome<Integer, Void> getFirstOperationOutcome() {
        return mFirstOperationOutcome;
    }


    @Override
    public OperationOutcome<Float, Void> getSecondOperationOutcome() {
        return mSecondOperationOutcome;
    }


    private void onSecondOperationCompleted(float v) {
        mSecondOperationOutcome = new OperationOutcome<>(true, v, null);
        switchToEndedStateSuccess();
    }
}
