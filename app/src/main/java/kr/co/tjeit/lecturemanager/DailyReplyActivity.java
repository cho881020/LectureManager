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

import kr.co.tjeit.lecturemanager.adapter.ReplyAdapter;
import kr.co.tjeit.lecturemanager.data.Reply;

public class DailyReplyActivity extends BaseActivity {

    private android.widget.TextView dateTxt;
    private android.widget.ListView replyListView;

    CalendarDay mCalendarDay = null;

    ReplyAdapter mAdapter;
    List<Reply> mReplyList = new ArrayList<>();
    private android.widget.Button dailyCheckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reply);
        mCalendarDay = getIntent().getParcelableExtra("클릭된날짜");
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        dailyCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, DailyCheckActivity.class);
                myIntent.putExtra("출석확인날짜", mCalendarDay);
                startActivity(myIntent);
            }
        });

    }

    @Override
    public void setValues() {

        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 M월 d일");
        dateTxt.setText(myDateFormat.format(mCalendarDay.getDate()));

        mAdapter = new ReplyAdapter(mContext, mReplyList);
        replyListView.setAdapter(mAdapter);

    }

    @Override
    public void bindViews() {
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.dailyCheckBtn = (Button) findViewById(R.id.dailyCheckBtn);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
    }
}
