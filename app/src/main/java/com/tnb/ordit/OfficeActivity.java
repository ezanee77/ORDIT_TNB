package com.tnb.ordit;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class OfficeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        WebView webView = (WebView)findViewById(R.id.webview);

        webView.loadUrl("https://www.youtube.com/watch?v=SjGuHhAL0Xc");
    }
}
