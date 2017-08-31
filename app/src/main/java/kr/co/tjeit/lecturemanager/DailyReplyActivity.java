package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kr.co.tjeit.lecturemanager.adapter.DailyReplyAdapter;
import kr.co.tjeit.lecturemanager.adapter.StudentAdapter;
import kr.co.tjeit.lecturemanager.data.Reply;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.GlobalData;

public class DailyReplyActivity extends BaseActivity {

    private android.widget.TextView selectDateTxt;
    private android.widget.ListView replyListView;

    List<Reply> mReplyList = new ArrayList<>();
    DailyReplyAdapter mAdapter;
    CalendarDay mCalendarDay = null;

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
        this.selectDateTxt = (TextView) findViewById(R.id.selectDateTxt);
    }
}
