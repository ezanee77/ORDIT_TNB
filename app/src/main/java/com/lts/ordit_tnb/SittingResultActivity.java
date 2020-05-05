package com.lts.ordit_tnb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class SittingResultActivity extends Activity {

    private TextView assResult;
    private GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitting_result);

        assResult = (TextView)findViewById(R.id.tvResult);
        mainGrid = (GridLayout)findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);

        Bundle getData = getIntent().getExtras();
        String score = getData.getString("message");

        assResult.setText(score);



    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for(int i = 0; i<mainGrid.getChildCount(); i++)
        {
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI == 0)
                    {
                        Intent intent = new Intent(SittingResultActivity.this, OfficeActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 1) //Open Ergonomics Activity
                    {
                        Intent intent = new Intent(SittingResultActivity.this, MouseKeyboardActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 2) //Open Complaint Activity
                    {
                        Intent intent = new Intent(SittingResultActivity.this, StretchingActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
