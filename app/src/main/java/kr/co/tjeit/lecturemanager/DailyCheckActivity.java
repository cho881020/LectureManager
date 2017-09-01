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
        checkStudentBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    public void setValues() {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy년 M월 dd일");
        dateTxt.setText(fm1.format(mCalendarDay.getDate()));
    }

    @Override
    public void bindViews() {
        this.checkTeacherBtn = (ToggleButton) findViewById(R.id.checkTeacherBtn);
        this.checkStudentBtn = (ToggleButton) findViewById(R.id.checkStudentBtn);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
    }
}
