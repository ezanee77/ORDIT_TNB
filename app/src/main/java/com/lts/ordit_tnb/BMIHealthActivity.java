package com.lts.ordit_tnb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BMIHealthActivity extends Activity {

    private EditText weight, height;
    private TextView tvName;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String bmiRResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_bmi);
        weight = (EditText)findViewById(R.id.etWeight);
        height = (EditText)findViewById(R.id.etHeight);
        tvName = (TextView)findViewById(R.id.tvName);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("BMI");

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

    public void bmiCalculator (View v){

            bmicaculation();
            addBmi();
    }

    private void addBmi() {

        String name = tvName.getText().toString();
        String heights = height.getText().toString();
        String weights = weight.getText().toString();

        String id = databaseReference.push().getKey();

        if (heights.isEmpty()){
            height.setError("Please enter your height!");
            height.requestFocus();
        }
        else if (weights.isEmpty()){
            weight.setError("Please enter your weight!");
            height.requestFocus();
        }

        BMI bmi= new BMI(
                id,
                name,
                heights,
                weights,
                bmiRResult);

        databaseReference.child(id).setValue(bmi);


    }

    private void bmicaculation() {

        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null && !"".equals(weightStr)) {

            float heightValues = Float.parseFloat(heightStr) / 100;
            float weightValues = Float.parseFloat(weightStr);

            float bmiResult = weightValues / (heightValues * heightValues);
            bmiRResult = String.valueOf(bmiResult);

            String bmiLevel = "";

            if (Float.compare(bmiResult, 18.5f) <= 0) {
                bmiLevel = getString(R.string.underweight);
                Intent intent = new Intent(BMIHealthActivity.this, BMIResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putFloat("message", bmiResult);
                bundle.putString("message1", bmiLevel);
                bundle.putInt("resID", R.drawable.underweight3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else if (Float.compare(bmiResult, 18.5f) > 0 && Float.compare(bmiResult, 25f) <= 0) {
                bmiLevel = getString(R.string.normal);
                Intent intent = new Intent(BMIHealthActivity.this, BMIResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putFloat("message", bmiResult);
                bundle.putString("message1", bmiLevel);
                bundle.putInt("resID", R.drawable.normal3);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (Float.compare(bmiResult, 25f) > 0 && Float.compare(bmiResult, 30f) <= 0) {
                bmiLevel = getString(R.string.overweight);
                Intent intent = new Intent(BMIHealthActivity.this, BMIResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putFloat("message", bmiResult);
                bundle.putString("message1", bmiLevel);
                bundle.putInt("resID", R.drawable.overweight3);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (Float.compare(bmiResult, 30f) > 0 && Float.compare(bmiResult, 35f) <= 0) {
                bmiLevel = getString(R.string.obese);
                Intent intent = new Intent(BMIHealthActivity.this, BMIResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putFloat("message", bmiResult);
                bundle.putString("message1", bmiLevel);
                bundle.putInt("resID", R.drawable.obese3);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                bmiLevel = getString(R.string.extremely_obese);
                Intent intent = new Intent(BMIHealthActivity.this, BMIResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putFloat("message", bmiResult);
                bundle.putString("message1", bmiLevel);
                bundle.putInt("resID", R.drawable.extremely_obese3);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        }

    }

}
