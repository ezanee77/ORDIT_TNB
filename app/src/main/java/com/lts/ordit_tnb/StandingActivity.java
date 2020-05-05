package com.lts.ordit_tnb;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class StandingActivity extends Activity {

    private ImageView image;
    private TextView question, questionBM, frmErgo, submitted;
    private RadioButton yes, no;
    private RadioGroup answer;
    private Button next, submit_btn;
    private int selectedAns, questions;
    String select_ans1, select_ans2, select_ans3, select_ans4, select_ans5, select_ans6, select_ans7, select_ans8, select_ans9, select_ans10,
             select_ans11, select_ans12, select_ans13, select_ans14, select_ans15, select_ans16, select_ans17, select_ans18;
    String staffName, staffID, jobD, final_score;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standing);

        image = (ImageView)findViewById(R.id.ivImage);
        question = (TextView)findViewById(R.id.tvQuestion);
        questionBM = (TextView)findViewById(R.id.tvQuestionbm);
        yes = (RadioButton)findViewById(R.id.rdYes);
        no = (RadioButton)findViewById(R.id.rdNo);
        answer = (RadioGroup)findViewById(R.id.rgAns);
        next = (Button)findViewById(R.id.btnNext);
        submit_btn = (Button)findViewById(R.id.btnSubmit);
        submitted = (TextView)findViewById(R.id.submittedBy);

        //For Question Part
        questions = 0;
        selectedAns = 0;

        //Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("STAND");

        if(user != null) {
            String uid = user.getUid();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    submitted.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        Bundle getData = getIntent().getExtras();
        String staff_Name = getData.getString("message");
        staffName = staff_Name;
        String staff_ID = getData.getString("message1");
        staffID = staff_ID;
        String jobDesc = getData.getString("message2");
        jobD = jobDesc;

    }


    private void addStandingAct() {
        String submit_name = submitted.getText().toString();

        String id = databaseReference.push().getKey();

        STAND stand= new STAND(
                id,
                staffName,
                staffID,
                jobD,
                select_ans1,
                select_ans2,
                select_ans3,
                select_ans4,
                select_ans5,
                select_ans6,
                select_ans7,
                select_ans8,
                select_ans9,
                select_ans10,
                select_ans11,
                select_ans12,
                select_ans13,
                select_ans14,
                select_ans15,
                select_ans16,
                select_ans17,
                select_ans18,
                final_score,
                submit_name);

        databaseReference.child(id).setValue(stand);
    }

    public void standing_calculation(View view){
        String selectedAnswer = "";

        switch (questions){
            case 0:
            {
                answer.setVisibility(View.VISIBLE);
                yes.setChecked(false);
                no.setChecked(false);
                yes.setEnabled(true);
                no.setEnabled(true);
                next.setText("Next");
                selectedAns = 0;


                image.setImageResource(R.drawable.stand_headback);
                question.setText("Are you working with hand above the head/the elbow above the shoulder?(> 2 hours)");
                questionBM.setText("Adakah tangan dinaikkan melangkui kepada siku melngkaui bahu?");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans1 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans1 = selectedAnswer;
                }
                questions=1;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 1:
            {
                image.setImageResource(R.drawable.bahu_mengangkat);
                question.setText("Are you working with your shoulder raised? (> 2 hour)");
                questionBM.setText("Adakah bahu anda terangkat semasa berkerja?");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans2 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans2 = selectedAnswer;
                }
                questions=2;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 2:
            {
                image.setImageResource(R.drawable.stand_headback);
                question.setText("Are you work repetitively by raising the hand above the head/elbow above shoulder? (> 2 hour)");
                questionBM.setText("Adakah tangan dinaikkan melangkui kepala/siku melangkui bahu berulang kali?");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans3 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans3 = selectedAnswer;
                }
                questions=3;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 3:
            {
                image.setImageResource(R.drawable.stand_headbent);
                question.setText("Are you working with your head bent downwards? (> 45 degrees and > 2 hour)");
                questionBM.setText("Adakah kepala anda menunduk? (> 45 darjah and > 2 jam)");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans4 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans4 = selectedAnswer;
                }
                questions=4;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 4:
            {
                image.setImageResource(R.drawable.stand_headback);
                question.setText("Are you working with back bent backwards? (> 2 hour)");
                questionBM.setText("Adakah kepala anda mendongkak ke belakang? (> 2 jam)");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans5 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans5 = selectedAnswer;
                }
                questions=5;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 5:
            {
                image.setImageResource(R.drawable.head_side);
                question.setText("Are you working with head bent sideways (> 2 hour)");
                questionBM.setText("Adakah kepala anda menyerang ke sisi? (> 2 jam)");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans6 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans6 = selectedAnswer;
                }
                questions=6;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 6:
            {
                image.setImageResource(R.drawable.back_forward);
                question.setText("Are you working with back bent forward? (> 30 degrees and > 2 hour)");
                questionBM.setText("Adakah badan anda membongkok ke depan? (> 30 darjah and > 2 jam)");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans7 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans7 = selectedAnswer;
                }
                questions=7;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 7:
            {
                image.setImageResource(R.drawable.body_twist);
                question.setText("Are you working with body twisted? (> 2 hour)");
                questionBM.setText("Adakah badan anda terpusing/terputar? (> 2 jam)");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans8 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans8 = selectedAnswer;
                }
                questions=8;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 8:
            {
                image.setImageResource(R.drawable.body_twist);
                question.setText("Are you working with wrist flexion/radial deviation/extension (> 15 degrees and > 2 hours)");
                questionBM.setText("Adakah kepala anda bekerja dengan lekapan pergelangan tangan/penyimpangan radial/lanjutan? (> 15 darjah and > 2 jam)");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans9 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans9 = selectedAnswer;
                }
                questions=9;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 9:
            {
                image.setImageResource(R.drawable.stand_armab);
                question.setText("Are you working in arm abducted sideways");
                questionBM.setText("Adakah anda bekerja dengan mengembangkan lengan?");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans10 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans10 = selectedAnswer;
                }
                questions=10;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 10:
            {
                image.setImageResource(R.drawable.squatting);
                question.setText("Are you working arm extended forward (> 45 degrees) or arm extended backward (> 20 degrees)");
                questionBM.setText("Adakah lengan anda dilanjutkan ke depan (> 45 darjah)/dilanjutkan ke belakang(> 20 darjah)?");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans11 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans11 = selectedAnswer;
                }
                questions=11;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 11:
            {
                image.setImageResource(R.drawable.squatting);
                question.setText("Are you working in squat position? (> 2 hours)");
                questionBM.setText("Adakah anda kerja dalam posisi mencangkung? (> 2 jam)");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans12 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans12 = selectedAnswer;
                }
                questions=12;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 12:
            {
                image.setImageResource(R.drawable.kneeling);
                question.setText("Are you working in kneeling position? (> 2 hours)");
                questionBM.setText("Adakah anda melutut semasa bekerja? (> 45 darjah and > 2 jam)");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans13 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans13 = selectedAnswer;
                }
                questions=13;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 13:
            {
                image.setImageResource(R.drawable.back_forward);
                question.setText("Are you working in static awkward posture? (> 2 hours)");
                questionBM.setText("Adakah berada dalam posisi statik yang janggal? (> 2 jam)");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans14 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans14 = selectedAnswer;
                }
                questions=14;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 14:
            {
                image.setImageResource(R.drawable.use_of_hand);
                question.setText("Are you working in standing position with minimal leg movement? (> 30 mins)");
                questionBM.setText("Adakah anda berdiri tanpa banyak pegerakkan? (> 30 minit)");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans15 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans15 = selectedAnswer;
                }
                questions=15;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 15:
            {
                image.setImageResource(R.drawable.carry);
                question.setText("Carrying or lifting an object (> 20kg)");
                questionBM.setText("Adakah membawa atau mengangkat objek yang melebihi 20kg?");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans16 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans16 = selectedAnswer;
                }
                questions=16;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 16:
            {
                image.setImageResource(R.drawable.carry);
                question.setText("Is your work involving repetitive sequence of movement?(> 2 per mins)");
                questionBM.setText("Adakah anda melakukan pegerakkan yang sama berulang kali? (> 2 kali seminit)");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans17 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans17 = selectedAnswer;
                }
                questions=17;
                yes.setChecked(true);
                no.setChecked(false);
                break;
            }
            case 17:
            {
                image.setImageResource(R.drawable.carry);
                question.setText("Is your work involving repetitive shoulder/arm movement with pauses or continuous shoulder/arm movement?");
                questionBM.setText("Adakah anda melakukan pegerakkan bahu/tangan berulang kali sama ada berterusan atau secara berjeda?");
                yes.setText("Yes");
                no.setText("No");

                if(yes.isChecked()){
                    selectedAns = selectedAns+1;
                    selectedAnswer = "Yes";
                    select_ans18 = selectedAnswer;
                }else if (no.isChecked()){
                    selectedAnswer = "No";
                    select_ans18 = selectedAnswer;
                }
                questions=18;
                yes.setChecked(true);
                no.setChecked(false);
                submit_btn.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
                break;
            }
        }

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String score = "";

                if(selectedAns>=0 && selectedAns<=5){
                    final_score = String.valueOf(selectedAns);
                    score = "You're in low risk!\nThere's no need to create control plan to eliminate the risk or to increased awareness level.\n";
                    Intent intent = new Intent(StandingActivity.this, StandingResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("message", score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    final_score = String.valueOf(selectedAns);
                    score = "Risk is exist!\nPlease take care on your working posture.\nBelow are the list of the mitigation plan that can guide you to the right posture!\nLet's watch it!";
                    Intent intent = new Intent(StandingActivity.this, StandingResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("message", score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                addStandingAct();
            }
        });
    }
}
