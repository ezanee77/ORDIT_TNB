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

import org.w3c.dom.Text;

public class WaistHealthActivity extends Activity{

    private EditText waist, hip;
    private TextView waistresult, tvName;
    private RadioGroup gender;
    private RadioButton radioMale, radioFemale;
    private String selectedGen = "1";

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String waisthipResult, gend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waist_health);

        waist = (EditText)findViewById(R.id.etWaist);
        hip = (EditText)findViewById(R.id.etHip);
        waistresult = (TextView)findViewById(R.id.waistResult);
        gender = (RadioGroup)findViewById(R.id.rgGender);
        radioMale= (RadioButton)findViewById(R.id.rbMale);
        radioFemale = (RadioButton)findViewById(R.id.rbFemale);
        tvName = (TextView)findViewById(R.id.tvName);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("WHA");

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

    public void waistCalculator (View v){

        whacaculation();
        addwha();
    }

    private void addwha() {

        String name = tvName.getText().toString();
        String waistValue = waist.getText().toString();
        String hipValue = hip.getText().toString();

        String id = databaseReference.push().getKey();

        if (waistValue.isEmpty()){
            waist.setError("Please enter your waist value!");
            waist.requestFocus();
        }
        else if (hipValue.isEmpty()){
            hip.setError("Please enter your hip value!");
            hip.requestFocus();
        }

        WHA wha= new WHA(
                id,
                name,
                gend,
                waistValue,
                hipValue,
                waisthipResult);

        databaseReference.child(id).setValue(wha);
    }


    private void whacaculation() {

        if (radioMale.isChecked()) {
            selectedGen = "1";
        } else if (radioFemale.isChecked()) {
            selectedGen = "2";
        }

        String waistStr = waist.getText().toString();
        String hipStr = hip.getText().toString();

        if (waistStr != null && !"".equals(waistStr)
                && hipStr != null && !"".equals(hipStr)) {

            float waistValues = Float.parseFloat(waistStr);
            float hipValues = Float.parseFloat(hipStr);

            float waistResult = waistValues / hipValues;
            waisthipResult = String.valueOf(waistResult);

            String waistLevel = "";
            String gender = "";

            if (selectedGen == "1") {
                if (Float.compare(waistResult, 0.85f) <= 0) {
                    waistLevel = getString(R.string.excellent);
                    gender = "Male";
                    gend = gender;
                    Intent intent = new Intent(WaistHealthActivity.this, WaistHealthResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", waistResult);
                    bundle.putString("message1", waistLevel);
                    bundle.putInt("resID", R.drawable.underweight3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if (Float.compare(waistResult, 0.85f) > 0 && Float.compare(waistResult, 0.90f) <= 0) {
                    waistLevel = getString(R.string.good);
                    gender = "Male";
                    gend = gender;
                    Intent intent = new Intent(WaistHealthActivity.this, WaistHealthResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", waistResult);
                    bundle.putString("message1", waistLevel);
                    bundle.putInt("resID", R.drawable.underweight3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if (Float.compare(waistResult, 0.90f) > 0 && Float.compare(waistResult, 0.95f) <= 0) {
                    waistLevel = getString(R.string.moderate);
                    gender = "Male";
                    gend = gender;
                    Intent intent = new Intent(WaistHealthActivity.this, WaistHealthResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", waistResult);
                    bundle.putString("message1", waistLevel);
                    bundle.putInt("resID", R.drawable.underweight3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if (Float.compare(waistResult, 0.95f) > 0 && Float.compare(waistResult, 1.0f) <= 0) {
                    waistLevel = getString(R.string.at_risk);
                    gender = "Male";
                    gend = gender;
                    Intent intent = new Intent(WaistHealthActivity.this, WaistHealthResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", waistResult);
                    bundle.putString("message1", waistLevel);
                    bundle.putInt("resID", R.drawable.underweight3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    waistLevel = getString(R.string.high_risk);
                    gender = "Male";
                    gend = gender;
                    Intent intent = new Intent(WaistHealthActivity.this, WaistHealthResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", waistResult);
                    bundle.putString("message1", waistLevel);
                    bundle.putInt("resID", R.drawable.underweight3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
            else if (selectedGen == "2") {
                if (Float.compare(waistResult, 0.75f) <= 0) {
                    waistLevel = getString(R.string.excellent);
                    gender = "Female";
                    gend = gender;
                    Intent intent = new Intent(WaistHealthActivity.this, WaistHealthResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", waistResult);
                    bundle.putString("message1", waistLevel);
                    bundle.putInt("resID", R.drawable.underweight3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if (Float.compare(waistResult, 0.75f) > 0 && Float.compare(waistResult, 0.80f) <= 0) {
                    waistLevel = getString(R.string.good);
                    gender = "Female";
                    gend = gender;
                    Intent intent = new Intent(WaistHealthActivity.this, WaistHealthResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", waistResult);
                    bundle.putString("message1", waistLevel);
                    bundle.putInt("resID", R.drawable.underweight3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if (Float.compare(waistResult, 0.80f) > 0 && Float.compare(waistResult, 0.85f) <= 0) {
                    waistLevel = getString(R.string.moderate);
                    gender = "Female";
                    gend = gender;
                    Intent intent = new Intent(WaistHealthActivity.this, WaistHealthResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", waistResult);
                    bundle.putString("message1", waistLevel);
                    bundle.putInt("resID", R.drawable.underweight3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if (Float.compare(waistResult, 0.85f) > 0 && Float.compare(waistResult, 0.9f) <= 0) {
                    waistLevel = getString(R.string.at_risk);
                    gender = "Female";
                    gend = gender;
                    Intent intent = new Intent(WaistHealthActivity.this, WaistHealthResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", waistResult);
                    bundle.putString("message1", waistLevel);
                    bundle.putInt("resID", R.drawable.underweight3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    waistLevel = getString(R.string.high_risk);
                    gender = "Female";
                    gend = gender;
                    Intent intent = new Intent(WaistHealthActivity.this, WaistHealthResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("message", waistResult);
                    bundle.putString("message1", waistLevel);
                    bundle.putInt("resID", R.drawable.underweight3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        }
    }
}
