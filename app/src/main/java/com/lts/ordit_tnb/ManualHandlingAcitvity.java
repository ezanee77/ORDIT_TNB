package com.lts.ordit_tnb;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ManualHandlingAcitvity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_handling_acitvity);

        WebView webView = (WebView)findViewById(R.id.webview);

        webView.loadUrl("https://www.youtube.com/watch?v=oKNtfKe3OX4&feature=youtu.be");
    }
}