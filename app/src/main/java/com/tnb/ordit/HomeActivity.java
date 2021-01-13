package com.tnb.ordit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    TextView userName;
    GridLayout mainGrid;
    Button btnLogout;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        userName = (TextView)findViewById(R.id.tvUser);
        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        btnLogout = findViewById(R.id.buttonLogout);


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            String uid = user.getUid();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    userName.setText("Hi, "+ name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }




        //Set Event
        setSingleEvent(mainGrid);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomeActivity.this, LoginActivity.class);
                intToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intToMain);
            }
        });
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

                    if (finalI == 0) //Open Health Assessment Activity
                    {
                        Intent intent = new Intent(HomeActivity.this, HealthMenuActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 1) //Open Ergonomics Activity
                    {
                        Intent intent = new Intent(HomeActivity.this, ErgonomicActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 2) //Open Complaint Activity
                    {
                        Intent intent = new Intent(HomeActivity.this, ComplaintActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 3)
                    {
                        Intent intent = new Intent(HomeActivity.this, VECActivity.class);
                        startActivity(intent);

                    }
                }
            });
        }
    }
}