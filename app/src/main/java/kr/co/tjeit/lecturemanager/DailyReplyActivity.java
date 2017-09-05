package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.adapter.DailyReplyAdapter;
import kr.co.tjeit.lecturemanager.data.Reply;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.GlobalData;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class DailyReplyActivity extends BaseActivity {

    private android.widget.TextView selectDateTxt;
    private android.widget.ListView replyListView;

    List<Reply> mReplyList = new ArrayList<>();
    DailyReplyAdapter mAdapter;
    CalendarDay mCalendarDay = null;
    private android.widget.Button attendBtn;
    private Button studentListBtn;
    private android.widget.EditText replyContentEdt;
    private Button replyBtn;

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

        replyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.register_reply(mContext, ContextUtil.getLoginUser(mContext).getId(), replyContentEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        replyContentEdt.setText("");
                        getRepliesFromServer();
                    }
                });
            }
        });


    }

    @Override
    public void setValues() {

        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 M월 d일");
        selectDateTxt.setText(myDateFormat.format(mCalendarDay.getDate()));

        mAdapter = new DailyReplyAdapter(mContext, mReplyList);
        replyListView.setAdapter(mAdapter);

        getRepliesFromServer();
    }

    void getRepliesFromServer () {
        ServerUtil.get_all_replies(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    JSONArray replies = json.getJSONArray("replies");

                    mReplyList.clear();

                    for (int i = 0; i < replies.length(); i++) {
                        JSONObject replyJson = replies.getJSONObject(i);

                        Reply tempReply = Reply.getReplyFromJson(replyJson);

                        mReplyList.add(tempReply);
                    }

                    mAdapter.notifyDataSetChanged();
                    replyListView.smoothScrollToPosition(mReplyList.size()-1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void bindViews() {
        this.replyBtn = (Button) findViewById(R.id.replyBtn);
        this.replyContentEdt = (EditText) findViewById(R.id.replyContentEdt);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.attendBtn = (Button) findViewById(R.id.attendBtn);
        this.studentListBtn = (Button) findViewById(R.id.studentListBtn);
        this.selectDateTxt = (TextView) findViewById(R.id.selectDateTxt);
    }
}
