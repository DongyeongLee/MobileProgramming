package com.ajou.android.acma;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;
import java.util.zip.Inflater;

public class Lecture {
    private UUID uuid;
    private String name; // 강의명
    private String major; // 개설전공
    private String professor; // 교수명
    private String code; // 과목코드
    private String location; // 강의실
    private String time; // 시간
    private int maxNum; // 제한인원
    private long num; // 신청인원
    private int grade; // 학점

    public Lecture(String name, String major, String professor, String code,
                   String location, String time, int maxNum, int grade) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.major = major;
        this.professor = professor;
        this.code = code;
        this.location = location;
        this.time = time;
        this.maxNum = maxNum;
        setNum();
        this.grade = grade;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public String getProfessor() {
        return professor;
    }

    public String getCode() {
        return code;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public long getNum() {
        return num;
    }

    public int getGrade() {
        return grade;
    }

    public void setNum() {
        DatabaseManager.databaseReference.child("lectures").child(this.code).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Lecture.this.num = (Long) dataSnapshot.getValue();

                System.out.println(Lecture.this.name + " " + Lecture.this.num);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}