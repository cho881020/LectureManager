package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyCheckActivity extends BaseActivity {

    CalendarDay mCalendarDay = null;
    private android.widget.TextView dateTxt;
    private android.widget.ToggleButton studentToggleBtn;

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

//        현재 날짜 (Calendar.getInstance)와,
//        화면에 적힌 날짜 (mCalendarDay) 를 비교.

        Calendar today = Calendar.getInstance();
        // 현재 시간을 받아오는 기능

//        현재 시간의 Date변수 추출
        Date todayDate = today.getTime();
//        선택된 (열린 화면에 적힌) Date변수 추출.
        Date selectedDate = mCalendarDay.getDate();

//        선택된 날짜가 오늘보다 이후인가? || 오늘인가?

        if (selectedDate.after(todayDate)) {
            Toast.makeText(mContext, "오늘보다 이후의 날짜", Toast.LENGTH_SHORT).show();
        }
        else if (today.get(Calendar.YEAR) == mCalendarDay.getYear() &&
                today.get(Calendar.MONTH) == mCalendarDay.getMonth() &&
                today.get(Calendar.DAY_OF_MONTH) == mCalendarDay.getDay()) {

            Toast.makeText(mContext, "오늘 날짜", Toast.LENGTH_SHORT).show();
        }
        else {
            studentToggleBtn.setEnabled(false);
            Toast.makeText(mContext, "이미 지나간 날짜입니다.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void bindViews() {
        this.studentToggleBtn = (ToggleButton) findViewById(R.id.studentToggleBtn);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
    }
}
