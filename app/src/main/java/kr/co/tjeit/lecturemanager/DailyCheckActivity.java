package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyCheckActivity extends BaseActivity {

    private android.widget.TextView dateTxt;
    private android.widget.ToggleButton checkStudentBtn;
    private android.widget.ToggleButton checkTeacherBtn;

    CalendarDay mCalendarDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check);
        mCalendarDay = getIntent().getParcelableExtra("date");
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {

    }

    @Override
    public void setValues() {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy년 M월 dd일");
        dateTxt.setText(fm1.format(mCalendarDay.getDate()));

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
            checkStudentBtn.setEnabled(false);
            Toast.makeText(mContext, "이미 지나간 날짜입니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void bindViews() {
        this.checkTeacherBtn = (ToggleButton) findViewById(R.id.checkTeacherBtn);
        this.checkStudentBtn = (ToggleButton) findViewById(R.id.checkStudentBtn);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
    }
}
