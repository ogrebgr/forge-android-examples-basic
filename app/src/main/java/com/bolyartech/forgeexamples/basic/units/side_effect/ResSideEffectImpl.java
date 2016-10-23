package com.bolyartech.forgeexamples.basic.units.side_effect;

import com.bolyartech.forge.android.app_unit.AbstractSideEffectOperationResidentComponent;
import com.bolyartech.forgeexamples.basic.data.ExampleData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ResSideEffectImpl extends AbstractSideEffectOperationResidentComponent<ExampleData, Void> implements ResSideEffect {
    private final OkHttpClient mOkHttpClient = new OkHttpClient();

    @Override
    public void retrieveExampleData() {
        switchToBusyState();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://forge-samples.bolyartech.com/realistic.html")
                        .build();


                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    JSONObject json = new JSONObject(response.body().string());
                    ExampleData data = ExampleData.fromJson(json);
                    switchToEndedStateSuccess(data);
                } catch (JSONException | IOException e) {
                    switchToEndedStateFail(null);
                }
            }
        });
        t.start();
    }
}
