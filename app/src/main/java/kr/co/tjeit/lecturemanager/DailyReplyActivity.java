package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import kr.co.tjeit.lecturemanager.adapter.ReplyAdapter;
import kr.co.tjeit.lecturemanager.util.GlobalData;

public class DailyReplyActivity extends BaseActivity {

    private android.widget.ListView replyListView;
    private android.widget.EditText replyEdt;
    private android.widget.Button replyBtn;
    private android.widget.TextView dateTxt;
    CalendarDay mCalendarDay;

    ReplyAdapter mAdapter;
    private TextView checkTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reply);
        mCalendarDay = getIntent().getParcelableExtra("date");
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        checkTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DailyCheckActivity.class);
                intent.putExtra("date", mCalendarDay);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy년 M월 dd일");
        dateTxt.setText(fm1.format(mCalendarDay.getDate()));

        mAdapter = new ReplyAdapter(mContext, GlobalData.allReplyList);
        replyListView.setAdapter(mAdapter);
    }

    @Override
    public void bindViews() {
        this.replyBtn = (Button) findViewById(R.id.replyBtn);
        this.replyEdt = (EditText) findViewById(R.id.replyEdt);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
        this.checkTxt = (TextView) findViewById(R.id.checkTxt);
    }
}
