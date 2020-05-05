package com.lts.ordit_tnb;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class StretchingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretching);

        WebView webView = (WebView) findViewById(R.id.webview);

        webView.loadUrl("https://www.youtube.com/watch?v=l0_bZwvUxjE&t=1s");
    }
}
