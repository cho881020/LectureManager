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

    private android.widget.TextView dateTxt;
    private android.widget.ListView replyListView;

    CalendarDay mCalendarDay = null;

    ReplyAdapter mAdapter;
    List<Reply> mReplyList = new ArrayList<>();
    private android.widget.Button checkBtn;
    private Button studentListBtn;
    private Button registerBtn;
    private android.widget.EditText replyEdt;

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

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.register_reply(mContext,
                        ContextUtil.getLoginUser(mContext).getId(),
                        replyEdt.getText().toString(),
                        new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
//                                댓글 등록후의 동작 구현

                                replyEdt.setText("");
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

        getRepliesFromServer();

    }

    void getRepliesFromServer() {

        ServerUtil.get_all_replies(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
//                서버에서 모든 댓글 목록을 받아온 후에 진행할 일.

                try {
                    JSONArray replies = json.getJSONArray("replies");

                    mReplyList.clear();

                    for (int i=0 ; i < replies.length() ; i++) {
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
        this.registerBtn = (Button) findViewById(R.id.registerBtn);
        this.replyEdt = (EditText) findViewById(R.id.replyEdt);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.checkBtn = (Button) findViewById(R.id.checkBtn);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
        this.studentListBtn = (Button) findViewById(R.id.studentListBtn);
    }
}
