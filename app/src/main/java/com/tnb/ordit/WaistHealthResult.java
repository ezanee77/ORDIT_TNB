package com.tnb.ordit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WaistHealthResult extends Activity {

    private TextView TVWHAResult, TVWHALevel;
    private ImageView IVWHAImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waist_health_result);

        TVWHAResult = (TextView)findViewById(R.id.waistresult);
        TVWHALevel = (TextView)findViewById(R.id.waistLevel);
        IVWHAImage = (ImageView)findViewById(R.id.waistImage);

        Bundle getData = getIntent().getExtras();
        Float waistResult = getData.getFloat("message");
        String waistLevel = getData.getString("message1");
        int waistImage = getData.getInt("resID");

        IVWHAImage.setImageResource(waistImage);
        TVWHAResult.setText(Float.toString(waistResult)) ;
        TVWHALevel.setText(waistLevel);
    }
}
