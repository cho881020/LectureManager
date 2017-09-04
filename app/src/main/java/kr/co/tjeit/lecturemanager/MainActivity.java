package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import static android.R.attr.data;

public class MainActivity extends BaseActivity {

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
                Intent intent = new Intent(mContext, StudentListActivity.class);
                intent.putExtra("클릭된날짜", data);
                startActivity(intent);

//                Toast.makeText(mContext, "선택 된 날짜 : " + date.toString(), Toast.LENGTH_SHORT).show();
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
