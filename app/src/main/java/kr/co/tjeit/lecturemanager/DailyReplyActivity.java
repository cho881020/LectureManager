package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.adapters.ReplyAdapter;
import kr.co.tjeit.lecturemanager.datas.Reply;
import kr.co.tjeit.lecturemanager.utils.GloblaData;

public class DailyReplyActivity extends BaseActivity {

    private List<Reply> replyList = new ArrayList<>();
    ReplyAdapter mReplyAdapter;

    private android.widget.ListView replyListView;
    private android.widget.TextView dateTxt;
    private android.widget.EditText replyTxt;
    private android.widget.Button addTxt;

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

    }

    @Override
    public void setValues() {
        String dateStr = getIntent().getStringExtra("날짜");
        mReplyAdapter = new ReplyAdapter(mContext, replyList);
        replyListView.setAdapter(mReplyAdapter);
        mReplyAdapter.notifyDataSetChanged();
        dateTxt.setText(dateStr);
    }

    @Override
    public void bindViews() {
        this.addTxt = (Button) findViewById(R.id.addTxt);
        this.replyTxt = (EditText) findViewById(R.id.replyTxt);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
    }
}
