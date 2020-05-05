package com.lts.ordit_tnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity{

    //Define view object
    EditText editTextName, editTextID, editTextEmail, editTextPassword;
    Button buttonSignup;
    TextView tvSignin;

    //Define firebase
    FirebaseAuth firebaseAuth;

    //Define Database
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextName = findViewById(R.id.editTextName);
        editTextID = findViewById(R.id.editTextID);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        tvSignin = findViewById(R.id.textViewSignin);
        buttonSignup = findViewById(R.id.buttonSignup);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting email and password from edit texts
                final String name = editTextName.getText().toString();
                final String staffID = editTextID.getText().toString();
                final String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                //Check if email and password are empty
                if(name.isEmpty()) {
                    editTextName.setError("Please enter your name.");
                    editTextName.requestFocus();
                }
                else if(staffID.isEmpty()) {
                    editTextID.setError("Please enter your staff ID.");
                    editTextID.requestFocus();
                }
                else if(email.isEmpty()) {
                    editTextEmail.setError("Please enter your email.");
                    editTextEmail.requestFocus();
                }
                else if (password.isEmpty()){
                    editTextPassword.setError("Please enter your password.");
                    editTextPassword.requestFocus();
                }
                else if (name.isEmpty() && staffID.isEmpty() && email.isEmpty() && password.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if (!(name.isEmpty() && staffID.isEmpty() && email.isEmpty() && password.isEmpty())){
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                finish();
                                User information = new User(
                                        name,
                                        staffID,
                                        email
                                );

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        Toast.makeText(MainActivity.this,"Registration Successful!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
