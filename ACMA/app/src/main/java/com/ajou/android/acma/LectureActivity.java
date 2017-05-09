package com.ajou.android.acma;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class LectureActivity extends SingleFragmentActivity {

    private static final String EXTRA_LECTURE_ID = "com.ajou.android.acma.lecture_id";

    public static Intent newIntent(Context packageContext, UUID lectureID) {
        Intent intent = new Intent(packageContext, LectureActivity.class);
        intent.putExtra(EXTRA_LECTURE_ID, lectureID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        super.mTextView.setText("과목 상세");
        UUID lectureID = (UUID) getIntent().getSerializableExtra(EXTRA_LECTURE_ID);
        return LectureFragment.newInstance(lectureID);
    }
}
