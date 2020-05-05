package com.lts.ordit_tnb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

public class HealthMenuActivity extends Activity {

    LinearLayout healthLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_menu);

        healthLayout = (LinearLayout)findViewById(R.id.HealthLayout);


        //Set Event
        setSingleEvent(healthLayout);
    }

    private void setSingleEvent(LinearLayout healthLayout) {

        //Loop all child item of Health Layout

        for(int i=0; i < healthLayout.getChildCount(); i++){

            CardView cardView = (CardView)healthLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (finalI == 0)
                    {
                        Intent intent = new Intent(HealthMenuActivity.this, BMIHealthActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 1)
                    {
                        Intent intent = new Intent(HealthMenuActivity.this, WaistHealthActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 2)
                    {
                        Intent intent = new Intent(HealthMenuActivity.this, BPHealthActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 3)
                    {
                        Intent intent = new Intent(HealthMenuActivity.this, SugarHealthActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 4)
                    {
                        Intent intent = new Intent(HealthMenuActivity.this, HeartHealthActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
