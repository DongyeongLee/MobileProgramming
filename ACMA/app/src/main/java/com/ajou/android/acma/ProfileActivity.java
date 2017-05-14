package com.ajou.android.acma;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ProfileActivity";

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button buttonDelete;
    private Button buttonLectureRegister;
    private Button buttonMyLectureList;
    private Button buttonLectureRanking;
    private Button buttonTimetable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewUserEmail = (TextView) findViewById(R.id.textviewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonLectureRegister = (Button)findViewById(R.id.LectureRegister);
        buttonMyLectureList = (Button)findViewById(R.id.MyLectureList);
        buttonLectureRanking = (Button)findViewById(R.id.LectureRanking);
        buttonTimetable = (Button)findViewById(R.id.Timetable);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail.setText("반갑습니다.\n"+ user.getEmail()+" 님!");

        //logout button event
        buttonLogout.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonLectureRegister.setOnClickListener(this);
        buttonMyLectureList.setOnClickListener(this);
        buttonLectureRanking.setOnClickListener(this);
        buttonTimetable.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLogout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.buttonDelete:
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(ProfileActivity.this);
                alert_confirm.setMessage("정말 계정을 삭제 할까요?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String DeleteID = firebaseAuth.getCurrentUser().getEmail();
                                String result = DeleteID.replaceAll("[.]","");
                                DatabaseManager.databaseReference.child("users").child(result).removeValue();
                                user.delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(ProfileActivity.this, "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
                                                finish();
                                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                            }
                                        });
                            }
                        }
                );
                alert_confirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ProfileActivity.this, "취소", Toast.LENGTH_LONG).show();
                    }
                });
                alert_confirm.show();
                break;
            case R.id.LectureRegister:
                Intent intent = new Intent(this, LectureListActivity.class);
                startActivity(intent);
                break;
            case R.id.MyLectureList:
                break;
            case R.id.LectureRanking:
                break;
            case R.id.Timetable:
                break;

        }
    }
}
