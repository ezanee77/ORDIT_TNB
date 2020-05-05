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

public class HeartHealthActivity extends Activity {

    private EditText heartRate, userAge;
    private TextView tvName;
    private RadioGroup gender;
    private RadioButton radioMale, radioFemale;
    private String selectedGen = "1";
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String heartRateLevel, gend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_health);

        heartRate = (EditText)findViewById(R.id.etHeart);
        userAge = (EditText)findViewById(R.id.etAge);
        gender = (RadioGroup)findViewById(R.id.rgGender);
        radioMale= (RadioButton)findViewById(R.id.rbMale);
        radioFemale = (RadioButton)findViewById(R.id.rbFemale);
        tvName = (TextView)findViewById(R.id.tvName);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("HEART");

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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void heartCalculator (View v) {
        heartcalculation();
        addHeart();
    }

    private void addHeart() {
        String name = tvName.getText().toString();
        String age = userAge.getText().toString();
        String heartRateValues = heartRate.getText().toString();

        String id = databaseReference.push().getKey();

        if (age.isEmpty()){
            userAge.setError("Please enter your age!");
            userAge.requestFocus();
        }
        else if (heartRateValues.isEmpty()){
            heartRate.setError("Please enter your heart rate!");
            heartRate.requestFocus();
        }

        HEART heart= new HEART(
                id,
                name,
                gend,
                age,
                heartRateValues,
                heartRateLevel);

        databaseReference.child(id).setValue(heart);
    }

    public void heartcalculation() {

        if (radioMale.isChecked()) {
            selectedGen = "1";
        } else if (radioFemale.isChecked()) {
            selectedGen = "2";
        }

        String heartRate_Str = heartRate.getText().toString();
        String userAge_Str = userAge.getText().toString();

        if (heartRate_Str != null && !"".equals(heartRate_Str)
                && userAge_Str != null && !"".equals(userAge_Str)) {

            float heartRate_Values = Float.parseFloat(heartRate_Str);
            float userAge_Values = Float.parseFloat(userAge_Str);

            String heartRate_Level = "";
            String gender = "";

            if (selectedGen == "1") {
                if (Float.compare(userAge_Values, 18f) >= 0 && Float.compare(userAge_Values, 25f) <= 0) {
                    if (Float.compare(heartRate_Values, 49f) >= 0 && Float.compare(heartRate_Values, 55f) <= 0) {
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 55f) > 0 && Float.compare(heartRate_Values, 61f) <= 0) {
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 61f) > 0 && Float.compare(heartRate_Values, 65f) <= 0) {
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 65f) > 0 && Float.compare(heartRate_Values, 69f) <= 0) {
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 69f) > 0 && Float.compare(heartRate_Values, 73f) <= 0) {
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 73f) > 0 && Float.compare(heartRate_Values, 81f) <= 0) {
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                else if (Float.compare(userAge_Values, 26f) >= 0 && Float.compare(userAge_Values, 35f) <= 0) {
                    if (Float.compare(heartRate_Values, 49f) >= 0 && Float.compare(heartRate_Values, 54f) <= 0) {
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 54f) > 0 && Float.compare(heartRate_Values, 61f) <= 0) {
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 61f) > 0 && Float.compare(heartRate_Values, 65f) <= 0) {
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 65f) > 0 && Float.compare(heartRate_Values, 70f) <= 0) {
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 70f) > 0 && Float.compare(heartRate_Values, 74f) <= 0) {
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 74f) > 0 && Float.compare(heartRate_Values, 81f) <= 0) {
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                else if (Float.compare(userAge_Values, 36f) >= 0 && Float.compare(userAge_Values, 45f) <= 0) {
                    if (Float.compare(heartRate_Values, 50f) >= 0 && Float.compare(heartRate_Values, 56f) <= 0) {
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 56f) > 0 && Float.compare(heartRate_Values, 62f) <= 0) {
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 62f) > 0 && Float.compare(heartRate_Values, 66f) <= 0) {
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 66f) > 0 && Float.compare(heartRate_Values, 70f) <= 0) {
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 70f) > 0 && Float.compare(heartRate_Values, 75f) <= 0) {
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 75f) > 0 && Float.compare(heartRate_Values, 82f) <= 0) {
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                else if (Float.compare(userAge_Values, 46f) >= 0 && Float.compare(userAge_Values, 55f) <= 0) {
                    if (Float.compare(heartRate_Values, 50f) >= 0 && Float.compare(heartRate_Values, 57f) <= 0) {
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 57f) > 0 && Float.compare(heartRate_Values, 63f) <= 0) {
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 63f) > 0 && Float.compare(heartRate_Values, 67f) <= 0) {
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 67f) > 0 && Float.compare(heartRate_Values, 71f) <= 0) {
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 71f) > 0 && Float.compare(heartRate_Values, 76f) <= 0) {
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        bundle.putInt("resID", R.drawable.underweight3);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 76f) > 0 && Float.compare(heartRate_Values, 83f) <= 0) {
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                else if (Float.compare(userAge_Values, 56f) >= 0 && Float.compare(userAge_Values, 65f) <= 0) {
                    if (Float.compare(heartRate_Values, 51f) >= 0 && Float.compare(heartRate_Values, 56f) <= 0) {
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 56f) > 0 && Float.compare(heartRate_Values, 61f) <= 0) {
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 61f) > 0 && Float.compare(heartRate_Values, 67f) <= 0) {
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 67f) > 0 && Float.compare(heartRate_Values, 71f) <= 0) {
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 71f) > 0 && Float.compare(heartRate_Values, 75f) <= 0) {
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 75f) > 0 && Float.compare(heartRate_Values, 81f) <= 0) {
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                    }
                } else {
                    if (Float.compare(heartRate_Values, 50f) >= 0 && Float.compare(heartRate_Values, 55f) <= 0) {
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 55f) > 0 && Float.compare(heartRate_Values, 61f) <= 0) {
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 61f) > 0 && Float.compare(heartRate_Values, 65f) <= 0) {
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 65f) > 0 && Float.compare(heartRate_Values, 69f) <= 0) {
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 69f) > 0 && Float.compare(heartRate_Values, 73f) <= 0) {
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if (Float.compare(heartRate_Values, 73f) > 0 && Float.compare(heartRate_Values, 79f) <= 0) {
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Male";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        bundle.putInt("resID", R.drawable.underweight3);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            }
////////////////////////////////////////////////////////FEMALE//////////////////////////////////////////////////////////////////////////////
             else if (selectedGen == "2") {
                if(Float.compare(userAge_Values, 18f) >= 0 && Float.compare(userAge_Values, 25f) <= 0){
                    if(Float.compare(heartRate_Values, 54f) >= 0 && Float.compare(heartRate_Values, 60f) <= 0){
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 60f) > 0 && Float.compare(heartRate_Values, 65f) <= 0){
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 65f) > 0 && Float.compare(heartRate_Values, 69f) <= 0){
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 69f) > 0 && Float.compare(heartRate_Values, 73f) <= 0){
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 73f) > 0 && Float.compare(heartRate_Values, 78f) <= 0){
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 78f) > 0 && Float.compare(heartRate_Values, 84f) <= 0){
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else{
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                else if(Float.compare(userAge_Values, 26f) >= 0 && Float.compare(userAge_Values, 35f) <= 0){
                    if(Float.compare(heartRate_Values, 54f) >= 0 && Float.compare(heartRate_Values, 59f) <= 0){
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 59f) > 0 && Float.compare(heartRate_Values, 64f) <= 0){
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 64f) > 0 && Float.compare(heartRate_Values, 68f) <= 0){
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 68f) > 0 && Float.compare(heartRate_Values, 72f) <= 0){
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 72f) > 0 && Float.compare(heartRate_Values, 76f) <= 0){
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 76f) > 0 && Float.compare(heartRate_Values, 82f) <= 0){
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else{
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                else if(Float.compare(userAge_Values, 36f) >= 0 && Float.compare(userAge_Values, 45f) <= 0){
                    if(Float.compare(heartRate_Values, 54f) >= 0 && Float.compare(heartRate_Values, 59f) <= 0){
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 59f) > 0 && Float.compare(heartRate_Values, 64f) <= 0){
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 64f) > 0 && Float.compare(heartRate_Values, 69f) <= 0){
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 69f) > 0 && Float.compare(heartRate_Values, 73f) <= 0){
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 73f) > 0 && Float.compare(heartRate_Values, 78f) <= 0){
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 78f) > 0 && Float.compare(heartRate_Values, 84f) <= 0){
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else{
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                else if(Float.compare(userAge_Values, 46f) >= 0 && Float.compare(userAge_Values, 55f) <= 0){
                    if(Float.compare(heartRate_Values, 54f) >= 0 && Float.compare(heartRate_Values, 60f) <= 0){
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 60f) > 0 && Float.compare(heartRate_Values, 65f) <= 0){
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 65f) > 0 && Float.compare(heartRate_Values, 69f) <= 0){
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 69f) > 0 && Float.compare(heartRate_Values, 73f) <= 0){
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 73f) > 0 && Float.compare(heartRate_Values, 77f) <= 0){
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 77f) > 0 && Float.compare(heartRate_Values, 83f) <= 0){
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else{
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                else if(Float.compare(userAge_Values, 56f) >= 0 && Float.compare(userAge_Values, 65f) <= 0){
                    if(Float.compare(heartRate_Values, 54f) >= 0 && Float.compare(heartRate_Values, 59f) <= 0){
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 59f) > 0 && Float.compare(heartRate_Values, 64f) <= 0){
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 64f) > 0 && Float.compare(heartRate_Values, 68f) <= 0){
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 68f) > 0 && Float.compare(heartRate_Values, 73f) <= 0){
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 73f) > 0 && Float.compare(heartRate_Values, 77f) <= 0){
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 77f) > 0 && Float.compare(heartRate_Values, 83f) <= 0){
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else{
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                else{
                    if(Float.compare(heartRate_Values, 54) >= 0 && Float.compare(heartRate_Values, 59f) <= 0){
                        heartRate_Level = getString(R.string.athlete);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 59f) > 0 && Float.compare(heartRate_Values, 64f) <= 0){
                        heartRate_Level = getString(R.string.heart_excellent);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 64f) > 0 && Float.compare(heartRate_Values, 68f) <= 0){
                        heartRate_Level = getString(R.string.heart_good);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 68f) > 0 && Float.compare(heartRate_Values, 72f) <= 0){
                        heartRate_Level = getString(R.string.heart_abv_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 72f) > 0 && Float.compare(heartRate_Values, 76f) <= 0){
                        heartRate_Level = getString(R.string.heart_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else if(Float.compare(heartRate_Values, 76f) > 0 && Float.compare(heartRate_Values, 84f) <= 0){
                        heartRate_Level = getString(R.string.heart_blw_av);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else{
                        heartRate_Level = getString(R.string.heart_poor);
                        heartRateLevel = heartRate_Level;
                        gender = "Female";
                        gend = gender;
                        Intent intent = new Intent(HeartHealthActivity.this, HeartResultActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("message", heartRate_Values);
                        bundle.putString("message1", heartRate_Level);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }

            }

        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
