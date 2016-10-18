package com.bolyartech.forgeexamples.basic.units.realistic;

import org.json.JSONException;
import org.json.JSONObject;


class ExampleData {
    final int exampleInt;
    final String exampleString;


    ExampleData(int exampleInt, String exampleString) {
        this.exampleInt = exampleInt;
        this.exampleString = exampleString;
    }


    static ExampleData fromJson(JSONObject json) throws JSONException {
        return new ExampleData(
                json.getInt("example_int"),
                json.getString("example_string")
        );
    }
}
