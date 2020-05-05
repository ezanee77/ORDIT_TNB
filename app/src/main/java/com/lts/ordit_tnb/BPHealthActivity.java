package com.lts.ordit_tnb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BPHealthActivity extends Activity {

    private EditText blood_top, blood_low;
    private TextView bpresult;
    private TextView tvName;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String bpRResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bphealth);

        blood_top = (EditText)findViewById(R.id.etBloodTop);
        blood_low = (EditText)findViewById(R.id.etBloodLow);
        bpresult = (TextView)findViewById(R.id.bpResult);
        tvName = (TextView)findViewById(R.id.tvName);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("BP");

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

    public void bpCalculator (View v){

        bpcaculation();
        addBp();

    }

    private void addBp() {

        String name = tvName.getText().toString();
        String uppNo = blood_top.getText().toString();
        String lowNo = blood_low.getText().toString();

        String id = databaseReference.push().getKey();

        if (uppNo.isEmpty()){
            blood_top.setError("Please enter your blood pressure upper number!");
            blood_top.requestFocus();
        }
        else if (lowNo.isEmpty()){
            blood_low.setError("Please enter your blood pressure lower number!");
            blood_low.requestFocus();
        }

        BP bp= new BP(
                id,
                name,
                uppNo,
                lowNo,
                bpRResult);

        databaseReference.child(id).setValue(bp);


    }


    private void bpcaculation() {

        String blood_topStr = blood_top.getText().toString();
        String blood_lowStr = blood_low.getText().toString();

        if(blood_topStr != null && !"".equals(blood_topStr)
                && blood_lowStr != null && !"".equals(blood_lowStr)){

            float blood_topValues = Float.parseFloat(blood_topStr);
            float blood_lowValues = Float.parseFloat(blood_lowStr);


            String bpLevel = "";

            if(Float.compare(blood_topValues, 120f) <= 0){
                if (Float.compare(blood_lowValues, 80f) <= 0) {
                    bpLevel = getString(R.string.bp_normal);
                    bpRResult = bpLevel;
                    Intent intent = new Intent(BPHealthActivity.this, BPResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("message1", bpLevel);
                    bundle.putInt("resID", R.drawable.bp_normal3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }else if (Float.compare(blood_topValues, 120f) > 0 && Float.compare(blood_topValues, 129f) <= 0){
                if (Float.compare(blood_lowValues, 80f) <= 0) {
                    bpLevel = getString(R.string.elevated);
                    bpRResult = bpLevel;
                    Intent intent = new Intent(BPHealthActivity.this, BPResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("message1", bpLevel);
                    bundle.putInt("resID", R.drawable.bp_elevated3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }else if (Float.compare(blood_topValues, 130f) > 0 && Float.compare(blood_topValues, 139f) <= 0) {
                if (Float.compare(blood_lowValues, 80f) > 0 && Float.compare(blood_lowValues, 89f) <= 0) {
                    bpLevel = getString(R.string.stage_one);
                    bpRResult = bpLevel;
                    Intent intent = new Intent(BPHealthActivity.this, BPResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("message1", bpLevel);
                    bundle.putInt("resID", R.drawable.bp_high1_ver3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }else if (Float.compare(blood_topValues, 140f) > 0 && Float.compare(blood_topValues, 180f) <= 0) {
                if (Float.compare(blood_lowValues, 90f) > 0 && Float.compare(blood_lowValues, 120f) <= 0) {
                    bpLevel = getString(R.string.stage_two);
                    bpRResult = bpLevel;
                    Intent intent = new Intent(BPHealthActivity.this, BPResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("message1", bpLevel);
                    bundle.putInt("resID", R.drawable.bp_high2_ver3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }else {
                if (Float.compare(blood_lowValues, 120f) > 0){
                    bpLevel = getString(R.string.stage_three);
                    bpRResult = bpLevel;
                    Intent intent = new Intent(BPHealthActivity.this, BPResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("message1", bpLevel);
                    bundle.putInt("resID", R.drawable.bp_high3_ver3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

        }
    }
}
