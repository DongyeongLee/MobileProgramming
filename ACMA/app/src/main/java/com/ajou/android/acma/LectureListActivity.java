package com.ajou.android.acma;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LectureListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        super.mTextView.setText("강의 목록");
        return new LectureListFragment();
    }
}
