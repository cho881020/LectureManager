package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import kr.co.tjeit.lecturemanager.utill.ContextUtill;
import kr.co.tjeit.lecturemanager.utill.ServerUtil;

public class DailyReplyActivity extends BaseActivity {


    DailyReplyAdapter mAdapter;
    private android.widget.ListView replyListView;
    private android.widget.TextView dateTxt;

    CalendarDay mCalendarDay = null;

    List<Reply> mReplyList = new ArrayList<>();
    private android.widget.Button uploadBtn;
    private android.widget.EditText contentEdt;

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

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.register_reply(mContext, contentEdt.getText().toString(), ContextUtill.getLoginUser(mContext).getId(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
//                        댓글 등록후의 동작 구현

                    }
                });
            }
        });


        replyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, MyProfileActivity.class);
            }
        });
    }

    @Override
    public void setValues() {

        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 M월 d일");
//        dateTxt.setText(myDateFormat.format(mCalendarDay)+"");

        mAdapter = new DailyReplyAdapter(mContext, mReplyList);
        replyListView.setAdapter(mAdapter);

        ServerUtil.get_all_replies(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
//                서버에서 모든 댓글 목록을 받아온 후에 진행할 일
                try {
                    JSONArray replies = json.getJSONArray("replies");
                    for (int i = 0; i < replies.length(); i++){
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
        this.uploadBtn = (Button) findViewById(R.id.uploadBtn);
        this.contentEdt = (EditText) findViewById(R.id.contentEdt);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
    }
}
