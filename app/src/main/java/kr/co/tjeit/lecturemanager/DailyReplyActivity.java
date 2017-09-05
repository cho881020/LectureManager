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

import kr.co.tjeit.lecturemanager.adapter.ReplyAdapter;
import kr.co.tjeit.lecturemanager.data.Reply;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class DailyReplyActivity extends BaseActivity {

    Reply mReply = null;
    ReplyAdapter mAdapter;
    List<Reply> mReplyList = new ArrayList<>();
    private android.widget.TextView dateTxt;
    private android.widget.ListView replyListView;

    CalendarDay mCalendarDay = null;
    private android.widget.Button checkBtn;
    private Button studentListBtn;
    private Button inputBtn;
    private android.widget.EditText inputEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reply);
        mCalendarDay = getIntent().getParcelableExtra("선택된날짜");
        bindViews();
        setupEvents();
        setValues();
        getRepliesFromServer();
    }

    @Override
    public void setupEvents() {

        inputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.register_reply(mContext, ContextUtil.getLoginUser(mContext).getId(),
                        inputEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                inputEdt.setText("");
                                getRepliesFromServer();
                            }
                        });



            }
        });

        studentListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StudentListActivity.class);
                startActivity(intent);
            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DailyCheckActivity.class);
                intent.putExtra("출석확인날짜", mCalendarDay);
                startActivity(intent);
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

    void getRepliesFromServer() {
        ServerUtil.get_all_replies(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
//                서버에서 모든 댓글 목록을 받아온 후에 진행할 일.

                try {
                    JSONArray replies = json.getJSONArray("replies");
                    mReplyList.clear();
                    for (int i = 0; i < replies.length(); i++) {
                        JSONObject replyJson = replies.getJSONObject(i);
                        Reply tempReply = Reply.getReplyFromJson(replyJson);
                        mReplyList.add(tempReply);

                    }

                    mAdapter.notifyDataSetChanged();

                    replyListView.smoothScrollToPosition(mReplyList.size() - 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void bindViews() {
        this.inputBtn = (Button) findViewById(R.id.inputBtn);
        this.inputEdt = (EditText) findViewById(R.id.inputEdt);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.checkBtn = (Button) findViewById(R.id.checkBtn);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
        this.studentListBtn = (Button) findViewById(R.id.studentListBtn);

    }
}
