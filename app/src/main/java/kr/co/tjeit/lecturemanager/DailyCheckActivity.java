package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DailyCheckActivity extends BaseActivity {

    private android.widget.TextView dateTxt;

    CalendarDay mCalendarDay = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check);
        mCalendarDay = getIntent().getParcelableExtra("출석확인날짜");
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 M월 d일");
        dateTxt.setText(myDateFormat.format(mCalendarDay.getDate()));


    }

    @Override
    public void bindViews() {

        this.dateTxt = (TextView) findViewById(R.id.dateTxt);

    }
}