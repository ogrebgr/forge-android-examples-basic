package com.bolyartech.forgeexamples.basic.units.realistic;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bolyartech.forge.android.misc.DebouncedOnClickListener;
import com.bolyartech.forge.android.misc.ViewUtils;
import com.bolyartech.forgeexamples.basic.R;
import com.bolyartech.forgeexamples.basic.UnitBaseActivity;
import com.bolyartech.forgeexamples.basic.data.ExampleData;
import com.bolyartech.forgeexamples.basic.dialogs.DfCommProblem;
import com.bolyartech.forgeexamples.basic.dialogs.DfCommWait;


public class ActRealistic extends UnitBaseActivity<ResRealistic> {
    private TextView mTvInt;
    private TextView mTvString;


    @NonNull
    @Override
    public ResRealistic createResidentComponent() {
        return new ResRealisticImpl();
    }


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
                showCommWaitDialog();
                getRes().retrieveExampleData();
            }
        });

        mTvInt = ViewUtils.findTextViewX(view, R.id.tv_int);
        mTvString = ViewUtils.findTextViewX(view, R.id.tv_string);
    }


    void onExampleData() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideCommWaitDialog();
                ExampleData data = getRes().getExampleData();
                mTvInt.setText(getString(R.string.act__realistic__tv_int, data.getExampleInt()));
                mTvString.setText(getString(R.string.act__realistic__tv_string, data.getExampleString()));
            }
        });
    }


    void onError() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideCommWaitDialog();
                showCommProblemDialog();
            }
        });
    }


    public void showCommProblemDialog() {
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentByTag(DfCommProblem.DIALOG_TAG) == null) {
            DfCommProblem fra = new DfCommProblem();
            fra.show(fm, DfCommProblem.DIALOG_TAG);
            fm.executePendingTransactions();
        }
    }


    public void showCommWaitDialog() {
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentByTag(DfCommWait.DIALOG_TAG) == null) {
            DfCommWait fra = new DfCommWait();
            fra.show(fm, DfCommWait.DIALOG_TAG);
            fm.executePendingTransactions();
        }
    }


    public void hideCommWaitDialog() {
        FragmentManager fm = getFragmentManager();
        DialogFragment df = (DialogFragment) fm.findFragmentByTag(DfCommWait.DIALOG_TAG);
        if (df != null) {
            df.dismiss();
        }
    }
}
