package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends BaseActivity {

    private com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
//                String dateStr = String.format(Locale.KOREA, "%d년 %d월 %d일", date.getYear(), date.getMonth()+1, date.getDay());
//                Toast.makeText(mContext, dateStr, Toast.LENGTH_SHORT).show();
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//                String dateA = simpleDateFormat.format(date.getDate());
//                String dateB = simpleDateFormat.format(Calendar.getInstance().getTime());
//
//                if (dateA.compareTo(dateB) < 0) {
//                    Toast.makeText(mContext, "이미 지나간 날짜입니다.", Toast.LENGTH_SHORT).show();
//                } else {
                Intent intent = new Intent(mContext, DailyReplyActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
//                }
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
    }
}
