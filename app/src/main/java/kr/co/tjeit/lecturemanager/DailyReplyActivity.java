package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import kr.co.tjeit.lecturemanager.adapter.ReplyAdapter;
import kr.co.tjeit.lecturemanager.util.GlobalData;

public class DailyReplyActivity extends BaseActivity {

    private android.widget.ListView replyListView;
    private android.widget.EditText replyEdt;
    private android.widget.Button replyBtn;
    private android.widget.TextView dateTxt;

    ReplyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reply);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {

    }

    @Override
    public void setValues() {
        dateTxt.setText(getIntent().getStringExtra("date"));
        mAdapter = new ReplyAdapter(mContext, GlobalData.allReplyList);
        replyListView.setAdapter(mAdapter);
    }

    @Override
    public void bindViews() {
        this.replyBtn = (Button) findViewById(R.id.replyBtn);
        this.replyEdt = (EditText) findViewById(R.id.replyEdt);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
    }
}
