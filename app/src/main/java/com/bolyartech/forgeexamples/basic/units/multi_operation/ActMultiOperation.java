package com.bolyartech.forgeexamples.basic.units.multi_operation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bolyartech.forge.android.app_unit.OperationOutcome;
import com.bolyartech.forge.android.misc.DebouncedOnClickListener;
import com.bolyartech.forge.android.misc.ViewUtils;
import com.bolyartech.forgeexamples.basic.OpActivity;
import com.bolyartech.forgeexamples.basic.R;
import com.bolyartech.forgeexamples.basic.dialogs.MyDialogs;


public class ActMultiOperation extends OpActivity<ResMultiOperation> {
    private TextView mTvInt;
    private TextView mTvFloat;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__multi_operation);

        initViews(getWindow().getDecorView());
    }


    @NonNull
    @Override
    public ResMultiOperation createResidentComponent() {
        return new ResMultiOperationImpl();
    }


    @Override
    protected void onResume() {
        super.onResume();
        handleState();
    }


    @Override
    protected synchronized void handleState() {
        switch (getRes().getOpState()) {
            case IDLE:
                MyDialogs.hideCommWaitDialog(getFragmentManager());
                showData();
                break;
            case BUSY:
                MyDialogs.showCommWaitDialog(getFragmentManager());
                break;
            case ENDED:
                MyDialogs.hideCommWaitDialog(getFragmentManager());
                switch (getRes().getCurrentOperation()) {
                    case FIRST_OPERATION:
                        handleFirstOperationOutcome();
                        break;
                    case SECOND_OPERATION:
                        handleSecondOperationOutcome();
                        break;
                }
                getRes().ack();
                break;
        }
    }


    private void initViews(View view) {
        ViewUtils.initButton(view, R.id.btn_first, new DebouncedOnClickListener() {
            @Override
            public void onDebouncedClick(View v) {
                if (getRes().isIdle()) {
                    getRes().executeFirstOperation();
                }
            }
        });


        ViewUtils.initButton(view, R.id.btn_second, new DebouncedOnClickListener() {
            @Override
            public void onDebouncedClick(View v) {
                if (getRes().isIdle()) {
                    getRes().executeSecondOperation();
                }
            }
        });


        mTvInt = ViewUtils.findTextViewX(view, R.id.tv_int);
        mTvFloat = ViewUtils.findTextViewX(view, R.id.tv_float);
    }


    private void showData() {
        OperationOutcome<Integer, Void> out1 = getRes().getFirstOperationOutcome();
        if (out1 != null) {
            showFirstData(out1);
        }

        OperationOutcome<Float, Void> out2 = getRes().getSecondOperationOutcome();
        if (out2 != null) {
            showSecondData(out2);
        }
    }


    private void showSecondData(OperationOutcome<Float, Void> out) {
        float rez = out.getResult();
        mTvFloat.setText(getString(R.string.act__multi_operation__tv_second, Float.toString(rez)));
    }


    private void showFirstData(OperationOutcome<Integer, Void> out) {
        int rez = out.getResult();
        mTvInt.setText(getString(R.string.act__multi_operation__tv_first, Integer.toString(rez)));
    }


    private void handleSecondOperationOutcome() {
        OperationOutcome<Float, Void> out = getRes().getSecondOperationOutcome();
        if (out.isSuccessful()) {
            showSecondData(out);
        } else {
            MyDialogs.showCommProblemDialog(getFragmentManager());
        }
    }


    private void handleFirstOperationOutcome() {
        OperationOutcome<Integer, Void> out = getRes().getFirstOperationOutcome();
        if (out.isSuccessful()) {
            showFirstData(out);
        } else {
            MyDialogs.showCommProblemDialog(getFragmentManager());
        }
    }
}
