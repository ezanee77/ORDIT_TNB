package com.tnb.ordit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComplaintActivity extends Activity {

    private EditText fname, staffId, body, others;
    private Button btnSave;

    FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);


        fname = (EditText)findViewById(R.id.etName);
        staffId = (EditText)findViewById(R.id.etstaffID);
        body = (EditText)findViewById(R.id.etbody);
        others = (EditText)findViewById(R.id.etOthers);
        btnSave = (Button)findViewById(R.id.btnSave);

        databaseReference = FirebaseDatabase.getInstance().getReference("complaint");
        firebaseAuth = FirebaseAuth.getInstance();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComplaint();
            }
        });
    }


    private void addComplaint() {
        String name = fname.getText().toString();
        String staffID = staffId.getText().toString();
        String bodyPain = body.getText().toString();
        String otherDesc = others.getText().toString();

        if (name.isEmpty()){
            fname.setError("Please Enter your name!");
            fname.requestFocus();
        }
        else if (staffID.isEmpty()){
            staffId.setError("Please enter your staff ID!");
        }
        else {

            String id = databaseReference.push().getKey();

            Complaint complaint= new Complaint(
                    id,
                    name,
                    staffID,
                    bodyPain,
                    otherDesc);

            databaseReference.child(id).setValue(complaint);

            Intent intent = new Intent(ComplaintActivity.this, HomeActivity.class);
            Toast.makeText(ComplaintActivity.this, "Submitted Successfully!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

}
