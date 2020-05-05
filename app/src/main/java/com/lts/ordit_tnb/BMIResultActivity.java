package com.lts.ordit_tnb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BMIResultActivity extends Activity {

    private TextView TVBMIResult, TVBMILevel;
    private ImageView IVBMIImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);

        TVBMIResult = (TextView)findViewById(R.id.BMIresult);
        TVBMILevel = (TextView)findViewById(R.id.BMIlevel);
        IVBMIImage = (ImageView)findViewById(R.id.BMIImage);

        Bundle getData = getIntent().getExtras();
        Float BMIResult = getData.getFloat("message");
        String BMILevel = getData.getString("message1");
        int BMIImage = getData.getInt("resID");

        IVBMIImage.setImageResource(BMIImage);
        TVBMIResult.setText(Float.toString(BMIResult)) ;
        TVBMILevel.setText(BMILevel);
    }
}
