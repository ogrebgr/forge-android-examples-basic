package com.bolyartech.forgeexamples.basic.units.realistic;

import org.json.JSONException;
import org.json.JSONObject;


class ExampleData {
    private final int mExampleInt;
    private final String mExampleString;


    public ExampleData(int exampleInt, String exampleString) {
        this.mExampleInt = exampleInt;
        this.mExampleString = exampleString;
    }


    static ExampleData fromJson(JSONObject json) throws JSONException {
        return new ExampleData(
                json.getInt("example_int"),
                json.getString("example_string")
        );
    }


    public int getExampleInt() {
        return mExampleInt;
    }


    public String getExampleString() {
        return mExampleString;
    }
}
