package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyCheckActivity extends BaseActivity {

    private android.widget.TextView dateTxt;

    CalendarDay mCalendarDay = null;
    private android.widget.ToggleButton studentBtn;

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

//        현재 날짜(Calendar.getInstance) 와 화면에 적힌 날짜 (mCalendarDay) 를 비교

        Calendar today = Calendar.getInstance();
        // 현재 시간을 받아오는 기능

        // 현재 시간의 Date 변수 추출
        Date todayDate = today.getTime();
        // 선택된 (열린 화면에 적힌) Date 변수 추출.
        Date selectedDate = mCalendarDay.getDate();

        // 선택된 날짜가 오늘보다 이후인가? || 오늘인가?
        if (selectedDate.after(todayDate)) {
            Toast.makeText(mContext, "오늘보다 이후의 날짜", Toast.LENGTH_SHORT).show();
        } else if (today.get(Calendar.YEAR) == mCalendarDay.getYear() && today.get(Calendar.MONTH) == mCalendarDay.getMonth() && today.get(Calendar.DAY_OF_MONTH) == mCalendarDay.getDay()) {
            Toast.makeText(mContext, "오늘날짜.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "이미 지나간 날짜입니다.", Toast.LENGTH_SHORT).show();
            studentBtn.setEnabled(false);
        }
//        boolean isbefore = todayDate.before(selectedDate);


    }

    @Override
    public void bindViews() {
        this.studentBtn = (ToggleButton) findViewById(R.id.studentBtn);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);

    }
}