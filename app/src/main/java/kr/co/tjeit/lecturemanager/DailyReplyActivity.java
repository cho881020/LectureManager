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
import java.util.List;

import kr.co.tjeit.lecturemanager.adapter.DailyReplyAdapter;
import kr.co.tjeit.lecturemanager.data.Reply;

public class DailyReplyActivity extends BaseActivity {

    private android.widget.TextView selectDateTxt;
    private android.widget.ListView replyListView;

    List<Reply> mReplyList = new ArrayList<>();
    DailyReplyAdapter mAdapter;
    CalendarDay mCalendarDay = null;
    private android.widget.Button attendBtn;
    private Button studentListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reply);
        mCalendarDay = getIntent().getParcelableExtra("DayData");
        bindViews();
        setValues();
        setupEvents();
    }

    @Override
    public void setupEvents() {
        attendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DailyCheckActivity.class);
                intent.putExtra("DayData2", mCalendarDay);
                startActivity(intent);
            }
        });

        studentListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StudentListActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {
        mAdapter = new DailyReplyAdapter(mContext, mReplyList);
        replyListView.setAdapter(mAdapter);

        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 M월 d일");
        selectDateTxt.setText(myDateFormat.format(mCalendarDay.getDate()));
    }

    @Override
    public void bindViews() {
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.attendBtn = (Button) findViewById(R.id.attendBtn);
        this.studentListBtn = (Button) findViewById(R.id.studentListBtn);
        this.selectDateTxt = (TextView) findViewById(R.id.selectDateTxt);
    }
}
