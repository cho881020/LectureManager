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
import java.util.Calendar;
import java.util.List;

import kr.co.tjeit.lecturemanager.adapters.ReplyAdapter;
import kr.co.tjeit.lecturemanager.datas.Reply;
import kr.co.tjeit.lecturemanager.utils.ContextUtil;
import kr.co.tjeit.lecturemanager.utils.GloblaData;
import kr.co.tjeit.lecturemanager.utils.ServerUtil;

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
//
                ServerUtil.register_reply(mContext, replyEdt.getText().toString(), ContextUtil.getLoginUser(mContext).getId(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        try {
                            if (json.getBoolean("result")) {
                                getRepliesFromServer();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

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
        getRepliesFromServer();


    }

    void getRepliesFromServer() {
        ServerUtil.get_all_replies(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    JSONArray replys = json.getJSONArray("replies");

                    for (int i = 0; i <replys.length(); i++) {
                        JSONObject reply = replys.getJSONObject(i);
                        Reply tempReply = Reply.getReplyFromJsonObject(reply);
                        replyList.add(tempReply);
                    }
                    mReplyAdapter.notifyDataSetChanged();
                    replyListView.smoothScrollToPosition(replyList.size() - 1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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
