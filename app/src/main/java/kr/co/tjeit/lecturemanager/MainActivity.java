package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.CalendarView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Locale;

public class MainActivity extends BaseActivity {

    private MaterialCalendarView calendarView;
    CalendarDay date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setValues();
        setUpEvents();
    }

    @Override
    public void setUpEvents() {
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

//                String str = String.format(Locale.KOREA, "%d년 %d월 %d일", date.getYear(), (date.getMonth()+1), date.getDay());

                Intent intent = new Intent(mContext, DailyReplyActivity.class);
                intent.putExtra("날짜", date);
                startActivity(intent);
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
