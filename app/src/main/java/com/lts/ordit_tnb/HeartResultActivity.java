package com.lts.ordit_tnb;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HeartResultActivity extends Activity {

    private TextView TVHEARTResult, TVHEARTLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_result);

        TVHEARTResult = (TextView)findViewById(R.id.heartRateresult);
        TVHEARTLevel = (TextView)findViewById(R.id.heartRatelevel);

        Bundle getData = getIntent().getExtras();
        Float heartRate_Values = getData.getFloat("message");
        String heartRate_Level = getData.getString("message1");


        TVHEARTResult.setText(Float.toString(heartRate_Values)) ;
        TVHEARTLevel.setText(heartRate_Level);
    }
}
