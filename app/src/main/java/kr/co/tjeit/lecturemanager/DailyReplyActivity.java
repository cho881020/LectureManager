package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kr.co.tjeit.lecturemanager.adapters.ReplyAdapter;
import kr.co.tjeit.lecturemanager.datas.Reply;
import kr.co.tjeit.lecturemanager.utils.GloblaData;

public class DailyReplyActivity extends BaseActivity {

    private List<Reply> replyList = new ArrayList<>();
    ReplyAdapter mReplyAdapter;
    CalendarDay mCalendarDay;

    private ListView replyListView;
    private TextView dateTxt;
    private EditText replyEdt;
    private Button addBtn;
    private TextView checkTxt;
    private TextView studentListTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reply);

        bindViews();
        setValues();
        setUpEvents();
    }

    @Override
    public void setUpEvents() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replyList.add(new Reply(replyEdt.getText().toString(), Calendar.getInstance(), GloblaData.allUsers.get(0)));
                mReplyAdapter.notifyDataSetChanged();
                replyEdt.setText("");
            }
        });

        checkTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DailyCheckActivity.class);
                intent.putExtra("date", mCalendarDay);
                startActivity(intent);
            }
        });

        studentListTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StudentListActivity.class);
                intent.putExtra("date", mCalendarDay);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
//        String dateStr = getIntent().getParcelableExtra("날짜");
        mCalendarDay = getIntent().getParcelableExtra("날짜");
        mReplyAdapter = new ReplyAdapter(mContext, replyList);
        replyListView.setAdapter(mReplyAdapter);
        mReplyAdapter.notifyDataSetChanged();
//        dateTxt.setText(dateStr);
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy년 M월 dd일");
        dateTxt.setText(fm1.format(mCalendarDay.getDate()));
    }

    @Override
    public void bindViews() {
        this.addBtn = (Button) findViewById(R.id.addBtn);
        this.replyEdt = (EditText) findViewById(R.id.replyEdt);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.studentListTxt = (TextView) findViewById(R.id.studentListTxt);
        this.checkTxt = (TextView) findViewById(R.id.checkTxt);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
    }
}
