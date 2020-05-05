package com.lts.ordit_tnb;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
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
