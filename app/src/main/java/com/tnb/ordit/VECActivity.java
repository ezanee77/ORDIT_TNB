package com.tnb.ordit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VECActivity extends AppCompatActivity {

    private ImageView image;
    private Button next;
    private int imageV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vec);

        image = (ImageView)findViewById(R.id.ivImage);
        imageV = 0;
    }

    public void next_Image(View v){
        switch (imageV){
            case 0:
            {
                image.setImageResource(R.drawable.definisi);
                imageV = 1;
                break;
            }
            case 1:
            {
                image.setImageResource(R.drawable.objektif);
                imageV = 2;
                break;
            }
            case 2:
            {
                image.setImageResource(R.drawable.risiko);
                imageV = 3;
                break;
            }
            case 3:
            {
                image.setImageResource(R.drawable.majikan);
                imageV = 4;
                break;
            }
            case 4:
            {
                image.setImageResource(R.drawable.pekerja);
                imageV = 5;
                break;
            }
            case 5:
            {
                image.setImageResource(R.drawable.ten);
                imageV = 6;
                break;
            }
            case 6:
            {
                image.setImageResource(R.drawable.prinsip1);
                imageV = 7;
                break;
            }
            case 7:
            {
                image.setImageResource(R.drawable.prinsip2);
                imageV = 8;
                break;
            }
            case 8:
            {
                image.setImageResource(R.drawable.prinsip3);
                imageV = 9;
                break;
            }
            case 9:
            {
                image.setImageResource(R.drawable.prinsip4);
                imageV = 10;
                break;
            }
            case 10:
            {
                image.setImageResource(R.drawable.prinnsip5);
                imageV = 11;
                break;
            }
            case 11:
            {
                image.setImageResource(R.drawable.prinsip6);
                imageV = 12;
                break;
            }
            case 12:
            {
                image.setImageResource(R.drawable.prinsip7);
                imageV = 13;
                break;
            }
            case 13:
            {
                image.setImageResource(R.drawable.prinsip8);
                imageV = 14;
                break;
            }
            case 14:
            {
                image.setImageResource(R.drawable.prinsip9);
                imageV = 15;
                break;
            }
            case 15:
            {
                image.setImageResource(R.drawable.prinsip10);
                imageV = 16;
                break;
            }
            case 16:
            {
                image.setImageResource(R.drawable.table_content);
                imageV = 17;
                break;
            }
            case 17:
            {
                image.setImageResource(R.drawable.table_content2);
                imageV = 18;
                break;
            }
            case 18:
            {
                image.setImageResource(R.drawable.front2);
                imageV = 19;
                break;
            }
            case 19:
            {
                Intent intent = new Intent(VECActivity.this, HomeActivity.class);
                startActivity(intent);
                imageV = 20;
                break;
            }
        }
    }
}
