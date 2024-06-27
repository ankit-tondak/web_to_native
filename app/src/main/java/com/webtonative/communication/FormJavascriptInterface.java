package com.webtonative.communication;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class FormJavascriptInterface {
    private Context context;

    FormJavascriptInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showUser(String name, String email) {
        Toast.makeText(context,"Name:"+name+" Email:"+ email,Toast.LENGTH_LONG).show();
    }
}