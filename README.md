Getting Started
To enable web-to-native communication, you need a few key components:

WebView: To render web content within your app.
JavaScript Interface: To allow communication between web content and native Android code.
Step 1: Setting Up WebView
First, add a WebView to your layout:

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <WebView
        android:id="@+id/webview"
        android:layout_width="fill_parent"
        android:layout_height="400dp"
        />
    <EditText
        android:id="@+id/title"
        android:layout_margin="20dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
    <Button
        android:id="@+id/sendToWeb"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Set Title in Web"/>


</LinearLayout>FirstIn your MainActivity.java or MainActivity.kt file, initialize the WebView and load a local HTML file:
In your MainActivity.kt file, initialize the WebView and load a local HTML file:

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
Create an assets folder under src/main and add an form.html file with some basic content:

<!DOCTYPE html>
<html>
  <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
      <title>ExploreWKWebViewJavascript</title>
      <script type="text/javascript">
          function submitForm() {
              Android.showUser(document.getElementById("name").value, document.getElementById("email").value);
          }
        function updateFromNative(message){
       document.getElementById("title").innerHTML = message;
  }
      </script>
  </head>
  <body>
    <div>
      <h2><label for="title" id="title" >Title</label></h2>
        <div>
          <label for="email">Email:</label>
          <input type="email" id="email" placeholder="Enter email" name="email">
        </div>
        <div>
          <label for="name">Name:</label>
          <input type="value" id="name" placeholder="Enter name" name="name">
        </div>
        <button onclick="submitForm()">Show data in Native</button>
    </div>
  </body>
</html>
Step 2: Enabling Web-to-Native Communication
Create a JavaScript interface FormJavascriptInterface FromJavscriptInterface.java file :

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
Add the JavaScript interface to your WebView in the onCreate method:

webView.addJavascriptInterface(new WebAppInterface(this), "Android");
Step 3: Testing the Communication
Now, when you run your app and click the button on the web page, it should trigger a toast message in your Android app and you can also change title in the webview by using your native edit text and button.
