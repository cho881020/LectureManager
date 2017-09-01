package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kr.co.tjeit.lecturemanager.adapter.ReplyAdapter;
import kr.co.tjeit.lecturemanager.data.Reply;

public class DailyReplyActivity extends BaseActivity {

    private android.widget.ListView replyListView;
    private android.widget.TextView selectedDayTxt;
    Calendar mCalendar;
    CalendarDay mCalendarDay;

    ReplyAdapter mAdapter;
    List<Reply> mReplyList = new ArrayList<>();
    private android.widget.Button attendanceChkBtn;
    private Button studentListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reply);


        mCalendar = (Calendar) getIntent().getSerializableExtra("선택된 날짜1");
        mCalendarDay = (CalendarDay) getIntent().getParcelableExtra("선택된 날짜2");

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        studentListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,StudentListActivity.class);
                startActivity(intent);
            }
        });

        attendanceChkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DailyCheckActivity.class);
                intent.putExtra("선택된 날짜", mCalendarDay);
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {


        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 MM월 d일");
//        selectedDayTxt.setText(myDateFormat.format(mCalendar.getTime()));
        selectedDayTxt.setText(myDateFormat.format(mCalendarDay.getDate()));

        mAdapter = new ReplyAdapter(mContext, mReplyList);
        replyListView.setAdapter(mAdapter);


    }

    @Override
    public void bindViews() {
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.attendanceChkBtn = (Button) findViewById(R.id.attendanceChkBtn);
        this.selectedDayTxt = (TextView) findViewById(R.id.selectedDayTxt);
        this.studentListBtn = (Button) findViewById(R.id.studentListBtn);
    }
}
