package com.ajou.android.acma;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LectureListFragment extends Fragment {

    private RecyclerView mLectureRecyclerView;
    private LectureAdapter mAdapter;
    private EditText mSearchET;
    private Button mSearchButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lecure_list, container, false);
        mLectureRecyclerView = (RecyclerView) view.findViewById(R.id.lecture_recycler_view);
        mLectureRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        mSearchET = (EditText) view.findViewById(R.id.searchET);
        mSearchButton = (Button) view.findViewById(R.id.searchButton);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { search(String.valueOf(mSearchET.getText())); }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(); // 중요! 리스트뷰를 새로 볼때마다 업데이트 -> 아마 num은 DB에서 받아와야 할것
    }

    private void updateUI() {
        LectureLab lectureLab = LectureLab.get(getActivity());
        List<Lecture> lectures = lectureLab.getLectures();

        if(mAdapter == null) {
            mAdapter = new LectureAdapter(lectures);
            mLectureRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void search(String input) {
        LectureLab lectureLab = LectureLab.get(getActivity());
        Lecture lecture = lectureLab.getLectureByName(input);

        if(lecture == null)
            Toast.makeText(getActivity(), input + " 을(를) 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
        else {
            Intent intent = LectureActivity.newIntent(getActivity(), lecture.getUUID());
            startActivity(intent);
        }
    }

    private class LectureHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Lecture mLecture;
        public TextView mNameTV;
        public TextView mCodeTV;
        public TextView mProfessorTV;
        public TextView mMajorTV;

        public LectureHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTV = (TextView) itemView.findViewById(R.id.item_name_TV);
            mCodeTV = (TextView) itemView.findViewById(R.id.item_code_TV);
            mProfessorTV = (TextView) itemView.findViewById(R.id.item_professor_TV);
            mMajorTV = (TextView) itemView.findViewById(R.id.item_major_TV);
        }

        public void bindLecture(Lecture lecture) {
            mLecture = lecture;
            mNameTV.setText(mLecture.getName());
            mCodeTV.setText(mLecture.getCode());
            mProfessorTV.setText(mLecture.getProfessor());
            mMajorTV.setText(mLecture.getMajor());
        }

        @Override
        public void onClick(View v) {
            Intent intent = LectureActivity.newIntent(getActivity(), mLecture.getUUID());
            startActivity(intent);
        }
    }

    private class LectureAdapter extends RecyclerView.Adapter<LectureHolder> {

        private List<Lecture> mLectureList;

        public LectureAdapter(List<Lecture> lectureList) {
            mLectureList = lectureList;
        }

        @Override
        public LectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_lecture, parent, false);
            return new LectureHolder(view);
        }

        @Override
        public void onBindViewHolder(LectureHolder holder, int position) {
            Lecture lecture = mLectureList.get(position);
            holder.bindLecture(lecture);
        }

        @Override
        public int getItemCount() {
            return mLectureList.size();
        }
    }
}