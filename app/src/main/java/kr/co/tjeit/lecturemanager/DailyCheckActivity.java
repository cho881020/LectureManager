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

    CalendarDay mCalendarDay;
    private android.widget.TextView selectedDayTxt;
    private android.widget.ToggleButton teacherConfirmTB;
    private android.widget.ToggleButton studentConfirmTB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check);
        mCalendarDay = (CalendarDay)getIntent().getParcelableExtra("선택된 날짜");
        bindViews();
        setupEvents();
        setValues();



    }

    @Override
    public void setupEvents() {
//        studentConfirmTB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Date today = Calendar.getInstance().getTime();
//                Date attendenceday = mCalendarDay.getDate();
//                if( today.after(attendenceday)){
//                    Toast.makeText(mContext, "이미 지나간 시간입니다", Toast.LENGTH_SHORT).show();
//                    studentConfirmTB.setText("출첵불가");
//                    studentConfirmTB.setEnabled(false);
//                }
//            }
//        });

    }

    @Override
    public void setValues() {
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 M월 d일");
        selectedDayTxt.setText(myDateFormat.format(mCalendarDay.getDate()));
        teacherConfirmTB.setEnabled(false);
        Date today = Calendar.getInstance().getTime();
        Date selectedDay = mCalendarDay.getDate();

//        선택된 날짜가 오늘보다 이후인가? ||  오늘인가
        if(selectedDay.after(today)){
            if((Calendar.getInstance().get(Calendar.YEAR) == mCalendarDay.getYear() &&
                    Calendar.getInstance().get(Calendar.MONTH) == mCalendarDay.getMonth() &&
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == mCalendarDay.getDay())){
                Toast.makeText(mContext, "오늘보다 이후의 날짜", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(mContext, "오늘보다 이후의 날짜", Toast.LENGTH_SHORT).show();

        }
        else{
            studentConfirmTB.setEnabled(false);
            Toast.makeText(mContext, "오늘보다 이후의 날짜", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void bindViews() {
        this.studentConfirmTB = (ToggleButton) findViewById(R.id.studentConfirmTB);
        this.teacherConfirmTB = (ToggleButton) findViewById(R.id.teacherConfirmTB);
        this.selectedDayTxt = (TextView) findViewById(R.id.selectedDayTxt);
    }
}
