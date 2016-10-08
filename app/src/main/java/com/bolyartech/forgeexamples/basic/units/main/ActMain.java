package com.bolyartech.forgeexamples.basic.units.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bolyartech.forge.android.app_unit.ResidentComponent;
import com.bolyartech.forge.android.misc.DebouncedOnClickListener;
import com.bolyartech.forge.android.misc.ViewUtils;
import com.bolyartech.forgeexamples.basic.R;
import com.bolyartech.forgeexamples.basic.UnitBaseActivity;


/**
 * This activity creates ResMain resident component and it is bind to it on re-creation after configuration change
 * Try to rotate your device and you will see that the activity shows one and the same value that is stored in the
 * resident component (which you can retrieve with <code>getStored()</code>). You can tell the resident component to
 * generate a new value with calling <code>newValue()</code>
 */
public class ActMain extends UnitBaseActivity<ResMain> {
    private TextView mTvValue;


    /**
     * This method will be called from the UnitManager in order to create the resident component
     * @return Resident component
     */
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


    /**
     * It is good practice to have view initialization in separate in a method
     * @param view View
     */
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
