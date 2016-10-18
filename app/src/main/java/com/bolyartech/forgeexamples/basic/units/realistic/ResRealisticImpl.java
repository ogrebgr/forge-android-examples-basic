package com.bolyartech.forgeexamples.basic.units.realistic;


import com.bolyartech.forge.android.app_unit.ResidentComponentAdapter;
import com.bolyartech.forge.android.app_unit.UnitActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


class ResRealisticImpl extends ResidentComponentAdapter implements ResRealistic {
    private final OkHttpClient mOkHttpClient = new OkHttpClient();

    private ExampleData mExampleData;
    private ActRealistic mActivity;


    @Override
    public synchronized void onActivityResumed(UnitActivity activity) {
        super.onActivityResumed(activity);
        mActivity = (ActRealistic) activity;
    }


    @Override
    public synchronized void onActivityPaused() {
        super.onActivityPaused();
        mActivity = null;
    }


    @Override
    public void retrieveExampleData() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                mExampleData = null;

                Request request = new Request.Builder()
                        .url("http://forge-samples.bolyartech.com/realistic.html")
                        .build();


                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    JSONObject json = new JSONObject(response.body().string());
                    mExampleData = ExampleData.fromJson(json);
                    onData();
                } catch (JSONException | IOException e) {
                    onError();
                }
            }
        });
        t.start();

    }


    private synchronized void onError() {
        if (mActivity != null) {
            mActivity.onError();
        }
    }


    private synchronized void onData() {
        if (mActivity != null) {
            mActivity.onExampleData();
        }
    }


    @Override
    public ExampleData getExampleData() {
        return mExampleData;
    }
}
