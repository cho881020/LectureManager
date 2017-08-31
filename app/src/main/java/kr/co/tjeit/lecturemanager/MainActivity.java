package kr.co.tjeit.lecturemanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.DatePicker;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends BaseActivity {

    Calendar mReservationDate;
    private com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Intent intent = new Intent(mContext, DailyReplyActivity.class);
                mReservationDate = Calendar.getInstance();
                mReservationDate.set(date.getYear(),date.getMonth(),date.getDay());
                intent.putExtra("선택된 날짜1", mReservationDate);
                intent.putExtra("선택된 날짜2", date);
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
