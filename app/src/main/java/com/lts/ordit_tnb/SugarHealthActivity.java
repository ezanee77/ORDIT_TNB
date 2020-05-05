package com.lts.ordit_tnb;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SugarHealthActivity extends Activity {

    private EditText glucose;
    private TextView tvName;
    private RadioGroup fasting;
    private RadioButton radioFasting, radioNotFasting;
    private String selectedFast = "1";



    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String glucoselevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar_health);

        glucose = (EditText)findViewById(R.id.etSugar);
        tvName = (TextView)findViewById(R.id.tvName);
        fasting = (RadioGroup)findViewById(R.id.rgFasting);
        radioFasting= (RadioButton)findViewById(R.id.rbFasting);
        radioNotFasting = (RadioButton)findViewById(R.id.rbNotFasting);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("GLUCOSE");

        if(user != null){
            String uid = user.getUid();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    tvName.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void glucoseCalculator (View v){
        glucosecaculation();
        addGlucose();
    }

    private void addGlucose() {

        String name = tvName.getText().toString();
        String glucoseValues = glucose.getText().toString();

        String id = databaseReference.push().getKey();

        if (glucoseValues.isEmpty()){
            glucose.setError("Please enter your glucose value!");
            glucose.requestFocus();
        }

        GLUCOSE glucose= new GLUCOSE(
                id,
                name,
                glucoseValues,
                glucoselevel);

        databaseReference.child(id).setValue(glucose);


    }

    private void glucosecaculation() {

        if (radioFasting.isChecked()) {
            selectedFast = "1";
        } else if (radioNotFasting.isChecked()) {
            selectedFast = "2";
        }

        String glucoseStr = glucose.getText().toString();

        if (glucoseStr != null && !"".equals(glucoseStr)) {

            float glucoseValues = Float.parseFloat(glucoseStr);

            String glucoseLevel = "";

            if(selectedFast == "1"){
                if (Float.compare(glucoseValues, 5.55f) <= 0) {
                    glucoseLevel = getString(R.string.glucose_normal);
                    glucoselevel = glucoseLevel;
                    Intent intent = new Intent(SugarHealthActivity.this, SugarResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", glucoseValues);
                    bundle.putString("message1", glucoseLevel);
                    bundle.putInt("resID", R.drawable.sugar_normal);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (Float.compare(glucoseValues, 5.55f) > 0 && Float.compare(glucoseValues, 7f) <= 0) {
                    glucoseLevel = getString(R.string.prediabetes);
                    glucoselevel = glucoseLevel;
                    Intent intent = new Intent(SugarHealthActivity.this, SugarResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", glucoseValues);
                    bundle.putString("message1", glucoseLevel);
                    bundle.putInt("resID", R.drawable.sugar_pre_dia);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    glucoseLevel = getString(R.string.diabetes);
                    glucoselevel = glucoseLevel;
                    Intent intent = new Intent(SugarHealthActivity.this, SugarResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", glucoseValues);
                    bundle.putString("message1", glucoseLevel);
                    bundle.putInt("resID", R.drawable.sugar_dia);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }

            else if(selectedFast == "2"){
                if (Float.compare(glucoseValues, 7.72f) <= 0) {
                    glucoseLevel = getString(R.string.glucose_normal);
                    glucoselevel = glucoseLevel;
                    Intent intent = new Intent(SugarHealthActivity.this, SugarResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", glucoseValues);
                    bundle.putString("message1", glucoseLevel);
                    bundle.putInt("resID", R.drawable.sugar_normal);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (Float.compare(glucoseValues, 7.72f) > 0 && Float.compare(glucoseValues, 11f) <= 0) {
                    glucoseLevel = getString(R.string.prediabetes);
                    glucoselevel = glucoseLevel;
                    Intent intent = new Intent(SugarHealthActivity.this, SugarResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", glucoseValues);
                    bundle.putString("message1", glucoseLevel);
                    bundle.putInt("resID", R.drawable.sugar_pre_dia);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    glucoseLevel = getString(R.string.diabetes);
                    glucoselevel = glucoseLevel;
                    Intent intent = new Intent(SugarHealthActivity.this, SugarResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", glucoseValues);
                    bundle.putString("message1", glucoseLevel);
                    bundle.putInt("resID", R.drawable.sugar_dia);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }

        }
    }
}
