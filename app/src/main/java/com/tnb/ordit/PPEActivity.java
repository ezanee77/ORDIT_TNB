package com.tnb.ordit;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class PPEActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppe);

        WebView webView = (WebView)findViewById(R.id.webview);

        webView.loadUrl("https://www.youtube.com/watch?v=zKIby2U2L4E");

    }
}
