package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

    private ListView replyListView;
    private TextView dateTxt;
    private EditText replyEdt;
    private Button addBtn;

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
        this.addBtn = (Button) findViewById(R.id.addBtn);
        this.replyEdt = (EditText) findViewById(R.id.replyEdt);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
    }
}
