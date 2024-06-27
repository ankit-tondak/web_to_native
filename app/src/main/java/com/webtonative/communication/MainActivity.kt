package com.webtonative.communication

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val formWebView: WebView = findViewById(R.id.webview)
        formWebView.settings.javaScriptEnabled = true
        formWebView.addJavascriptInterface(FormJavascriptInterface(this), "Android")
        formWebView.loadUrl("file:///android_asset/form.html")
        val editText=findViewById<EditText>(R.id.title)
        val sendToWeb=findViewById<Button>(R.id.sendToWeb)
        sendToWeb.setOnClickListener{
            formWebView.evaluateJavascript(
                "javascript: " +"updateFromNative(\"" + editText.text + "\")",null)
        }
        }

}