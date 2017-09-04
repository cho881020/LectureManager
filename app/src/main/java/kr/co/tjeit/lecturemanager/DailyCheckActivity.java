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

    private android.widget.TextView selectDateTxt;
    private android.widget.ToggleButton studentCheckBtn;
    private android.widget.ToggleButton teacherCheckBtn;

    CalendarDay mCalendarDay = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check);
        mCalendarDay = getIntent().getParcelableExtra("DayData2");
        bindViews();
        setValues();
        setupEvents();
    }

    @Override
    public void setupEvents() {

        Calendar today = Calendar.getInstance();

        Date todayDate = today.getTime();
        Date selectedDate = mCalendarDay.getDate();

        if (selectedDate.after(todayDate)) {
            Toast.makeText(mContext, "아직 찍으실 수 없습니다.", Toast.LENGTH_SHORT).show();
            studentCheckBtn.setEnabled(false);
        }
        else if (today.get(Calendar.YEAR) == mCalendarDay.getYear() &&
                today.get(Calendar.MONTH) == mCalendarDay.getMonth() &&
                today.get(Calendar.DAY_OF_MONTH) == mCalendarDay.getDay()) {
            Toast.makeText(mContext, "오늘 날짜", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "이미 지나간 날짜입니다.", Toast.LENGTH_SHORT).show();
            studentCheckBtn.setEnabled(false);
        }

    }

    @Override
    public void setValues() {
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 M월 d일");
        selectDateTxt.setText(myDateFormat.format(mCalendarDay.getDate()));
    }

    @Override
    public void bindViews() {
        this.teacherCheckBtn = (ToggleButton) findViewById(R.id.teacherCheckBtn);
        this.studentCheckBtn = (ToggleButton) findViewById(R.id.studentCheckBtn);
        this.selectDateTxt = (TextView) findViewById(R.id.selectDateTxt);
    }
}
