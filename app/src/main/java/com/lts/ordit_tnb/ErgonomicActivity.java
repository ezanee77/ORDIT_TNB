package com.lts.ordit_tnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ErgonomicActivity extends Activity {

    private EditText fullName, staffID;
    private RadioButton sitting, standing;
    private RadioGroup job;
    String selectedJob = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ergonomic);

        fullName =(EditText)findViewById(R.id.etName);
        staffID = (EditText)findViewById(R.id.etID);
        sitting = (RadioButton)findViewById(R.id.rbSitting);
        standing = (RadioButton)findViewById(R.id.rbStanding);

    }

    public void btnNext (View v) {

        String name = fullName.getText().toString();
        String staffId = staffID.getText().toString();

        if (name.isEmpty()) {
            fullName.setError("Please enter staff name!");
            fullName.requestFocus();
        }
        else if (staffId.isEmpty()) {
            staffID.setError("Please enter staff ID!");
            staffID.requestFocus();
        }
        else if (name.isEmpty() && staffId.isEmpty()) {
            Toast.makeText(ErgonomicActivity.this, "Name and staff ID are empty!", Toast.LENGTH_SHORT).show();
        }
        else if (!name.isEmpty() && !staffId.isEmpty()) {

            if (sitting.isChecked()) {
                selectedJob = "sitting";
                Intent intent = new Intent(ErgonomicActivity.this, SittingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("message", name);
                bundle.putString("message1", staffId);
                bundle.putString("message2", selectedJob);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (standing.isChecked()) {
                selectedJob = "standing";
                Intent intent = new Intent(ErgonomicActivity.this, StandingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("message", name);
                bundle.putString("message1", staffId);
                bundle.putString("message2", selectedJob);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
}