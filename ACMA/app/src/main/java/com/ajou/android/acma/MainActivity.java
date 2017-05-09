package com.ajou.android.acma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity { // AppCompat version gradle 참고 - recyclerview때문에 수정함

    Student mStudent;
    TextView mGreetingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, SplashActivity.class));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStudent = new Student("vencott@naver.com", "0000", "Kimjungmin", 3, 0);

        mGreetingTextView = (TextView) findViewById(R.id.greetingTextView);
        mGreetingTextView.setText(mStudent.getName() + "님 안녕하세요!");
    }

    public void onButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.lectureRegisterButton :
                Intent intent = new Intent(this, LectureListActivity.class);
                startActivity(intent);
                break;
            case R.id.lectureRankingButton :
                System.out.println("강의랭킹");
                break;
            case R.id.timetableButton :
                System.out.println("시간표");
                break;
        }
    }
}