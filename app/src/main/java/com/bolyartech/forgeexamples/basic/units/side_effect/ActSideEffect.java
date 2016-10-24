package com.bolyartech.forgeexamples.basic.units.side_effect;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bolyartech.forge.android.misc.DebouncedOnClickListener;
import com.bolyartech.forge.android.misc.ViewUtils;
import com.bolyartech.forgeexamples.basic.R;
import com.bolyartech.forgeexamples.basic.data.ExampleData;
import com.bolyartech.forgeexamples.basic.dialogs.MyDialogs;
import com.bolyartech.forgeexamples.basic.OpActivity;


public class ActSideEffect extends OpActivity<ResSideEffect> {
    private TextView mTvInt;
    private TextView mTvString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_realistic);

        initViews(getWindow().getDecorView());
    }


    private void initViews(View view) {
        ViewUtils.initButton(view, R.id.btn_retrieve, new DebouncedOnClickListener() {
            @Override
            public void onDebouncedClick(View v) {
                MyDialogs.showCommWaitDialog(getFragmentManager());
                getRes().retrieveExampleData();
            }
        });

        mTvInt = ViewUtils.findTextViewX(view, R.id.tv_int);
        mTvString = ViewUtils.findTextViewX(view, R.id.tv_string);
    }


    @NonNull
    @Override
    public ResSideEffect createResidentComponent() {
        return new ResSideEffectImpl();
    }


    @Override
    protected synchronized void handleState() {
        switch (getRes().getOpState()) {
            case IDLE:
                MyDialogs.hideCommWaitDialog(getFragmentManager());
                break;
            case BUSY:
                MyDialogs.showCommWaitDialog(getFragmentManager());
                break;
            case ENDED:
                MyDialogs.hideCommWaitDialog(getFragmentManager());
                if (getRes().isEndedSuccessfully()) {
                    onExampleData();
                } else {
                    onError();
                }
                getRes().endedStateAcknowledged();
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        handleState();
    }


    private void onExampleData() {
        ExampleData data = getRes().getLastResult();
        mTvInt.setText(getString(R.string.act__realistic__tv_int, data.getExampleInt()));
        mTvString.setText(getString(R.string.act__realistic__tv_string, data.getExampleString()));
    }


    private void onError() {
        MyDialogs.showCommProblemDialog(getFragmentManager());
    }
}
