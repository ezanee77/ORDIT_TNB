package com.lts.ordit_tnb;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BPResultActivity extends Activity {

    private TextView TVBPLevel;
    private ImageView IVBPImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpresult);

        TVBPLevel = (TextView)findViewById(R.id.BPLevel);
        IVBPImage = (ImageView)findViewById(R.id.BPImage);

        Bundle getData = getIntent().getExtras();
        String BPLevel = getData.getString("message1");
        int BPImage = getData.getInt("resID");

        IVBPImage.setImageResource(BPImage);
        TVBPLevel.setText(BPLevel);
    }
}
