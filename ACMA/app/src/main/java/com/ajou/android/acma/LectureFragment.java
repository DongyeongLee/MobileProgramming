package com.ajou.android.acma;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class LectureFragment extends Fragment{

    public static final String ARG_LECTURE_ID = "lecture_id";

    private Lecture mLecture;
    private TextView lec_name_TV;
    private TextView lec_major_TV;
    private TextView lec_professor_TV;
    private TextView lec_code_TV;
    private TextView lec_location_TV;
    private TextView lec_time_TV;
    private TextView lec_maxNum_TV;
    private TextView lec_num_TV;
    private TextView lec_grade_TV;

    private Button registerButton;

    public static LectureFragment newInstance(UUID lectureID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_LECTURE_ID, lectureID);

        LectureFragment lectureFragment = new LectureFragment();
        lectureFragment.setArguments(args);
        return lectureFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID lectureID = (UUID) getArguments().getSerializable(ARG_LECTURE_ID);
        mLecture = LectureLab.get(getActivity()).getLecture(lectureID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lecture, container, false);

        lec_name_TV = (TextView) v.findViewById(R.id.lec_name_TV);
        lec_major_TV = (TextView) v.findViewById(R.id.lec_major_TV);
        lec_professor_TV = (TextView) v.findViewById(R.id.lec_professor_TV);
        lec_grade_TV = (TextView) v.findViewById(R.id.lec_grade_TV);
        lec_code_TV = (TextView) v.findViewById(R.id.lec_code_TV);
        lec_location_TV = (TextView) v.findViewById(R.id.lec_location_TV);
        lec_time_TV = (TextView) v.findViewById(R.id.lec_time_TV);
        lec_maxNum_TV = (TextView) v.findViewById(R.id.lec_maxNum_TV);
        lec_num_TV = (TextView) v.findViewById(R.id.lec_num_TV);
        registerButton = (Button) v.findViewById(R.id.registerButton);

        lec_name_TV.setText(mLecture.getName());
        lec_major_TV.setText(mLecture.getMajor());
        lec_professor_TV.setText(mLecture.getProfessor());
        lec_grade_TV.setText(""+mLecture.getGrade());
        lec_code_TV.setText(mLecture.getCode());
        lec_location_TV.setText(mLecture.getLocation());
        lec_time_TV.setText(mLecture.getTime());
        lec_maxNum_TV.setText(""+mLecture.getMaxNum());
        lec_num_TV.setText(""+mLecture.getNum());
        /*
        중요!!
            실제 Lecture의 신청자수 1 증가 -> LectureList에선 updateUI로 전체 강의 리스트를 갱신 --> DB연동하면 다른곳에서도 증가시 update
            LectureFragment 에서 신청자수 1 증가
            토스트 출력

            DB에서 강의의 신청자수 업데이트
         */
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setMessage(mLecture.getName() + " 을(를) 수강신청 하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mLecture.setNum(mLecture.getNum() + 1);
                                lec_num_TV.setText(""+mLecture.getNum());
                                Toast.makeText(getActivity(), mLecture.getName() + " 이(가) 수강신청 되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create().show();
            }
        });
        return v;
    }
}