package com.lts.ordit_tnb;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SugarResultActivity extends Activity {

    private TextView gluResult, gluLevel;
    private ImageView gluImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar_result);

        gluResult = (TextView)findViewById(R.id.GluResult);
        gluLevel = (TextView)findViewById(R.id.GluLevel);
        gluImage = (ImageView)findViewById(R.id.GluImage);

        Bundle getData = getIntent().getExtras();
        Float GluResult = getData.getFloat("message");
        String GluLevel = getData.getString("message1");
        int GluImage = getData.getInt("resID");

        gluImage.setImageResource(GluImage);
        gluResult.setText(Float.toString(GluResult)) ;
        gluLevel.setText(GluLevel);
    }
}
