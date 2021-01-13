package com.tnb.ordit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class StandingResultActivity extends Activity {

    private TextView assResult;
    private GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standing_result);

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
                        Intent intent = new Intent(StandingResultActivity.this, ManualHandlingAcitvity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 1)
                    {
                        Intent intent = new Intent(StandingResultActivity.this, PPEActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 2)
                    {
                        Intent intent = new Intent(StandingResultActivity.this, StretchingActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
