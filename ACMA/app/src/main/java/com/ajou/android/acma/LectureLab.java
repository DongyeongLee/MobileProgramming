package com.ajou.android.acma;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LectureLab {
    private static LectureLab sLectureLab;
    private List<Lecture> mLectures;

    private LectureLab(Context context) {
        mLectures = new ArrayList<>();
        createLectures();
    }

    public List<Lecture> getLectures() {
        return mLectures;
    }

    public Lecture getLecture(UUID uuid) {
        for(Lecture lecture : mLectures) {
            if(lecture.getUUID().equals(uuid)) {
                return lecture;
            }
        }
        return null;
    }

    public Lecture getLectureByName(String name) {
        for(Lecture lecture : mLectures) {
            if(lecture.getName().equals(name)) {
                return lecture;
            }
        }
        return null;
    }

    public static LectureLab get(Context context) {
        if(sLectureLab == null) {
            sLectureLab = new LectureLab(context);
        }
        return sLectureLab;
    }

    private void createLectures() {
        Lecture MP = new Lecture("모바일프로그래밍", "소셜미디어전공", "경민호", "M012", "산419", "월A수A", 40, 4);
        mLectures.add(MP);
        Lecture CN = new Lecture("컴퓨터네트워크", "소프트웨어전공", "노병희", "F012", "팔309", "월B수B", 100, 3);
        mLectures.add(CN);
        Lecture OS = new Lecture("운영체제", "소프트웨어전공", "김재훈", "F052", "팔309", "월C수C", 90, 3);
        mLectures.add(OS);
        Lecture DC = new Lecture("디지털회로", "소프트웨어전공", "최영준", "F014", "팔410", "월D수D", 30, 4);
        mLectures.add(DC);
        Lecture DS = new Lecture("자료구조", "소프트웨어전공", "한경식", "F011", "팔309", "화D금D", 80, 4);
        mLectures.add(DS);
        Lecture BL = new Lecture("생물학실험", "생명공학전공", "강민준", "B014", "원230", "수D", 20, 1);
        mLectures.add(BL);
    }
}
