package com.bolyartech.forgeexamples.basic.units.main;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bolyartech.forge.android.app_unit.ResidentComponent;
import com.bolyartech.forge.android.app_unit.UnitActivity;
import com.bolyartech.forge.android.misc.ActivityUtils;
import com.bolyartech.forge.android.misc.DebouncedOnClickListener;
import com.bolyartech.forge.android.misc.ViewUtils;
import com.bolyartech.forgeexamples.basic.R;
import com.bolyartech.forgeexamples.basic.UnitBaseActivity;


public class ActMain extends UnitBaseActivity<ResMain> {
    private Button mBtnNewValue;
    private TextView mTvValue;


    @NonNull
    @Override
    public ResidentComponent createResidentComponent() {
        return new ResMain();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        initViews(getWindow().getDecorView());
    }


    private void initViews(View view) {
        mTvValue = ViewUtils.findTextViewX(view, R.id.tv_value);
        ViewUtils.initButton(view, R.id.btn_new_value, new DebouncedOnClickListener() {
            @Override
            public void onDebouncedClick(View v) {
                getRes().newValue();
                updateValueView();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateValueView();
    }


    private void updateValueView() {
        mTvValue.setText(getString(R.string.act__main__tv_value, getRes().getStored()));
    }
}
