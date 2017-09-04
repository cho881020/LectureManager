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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import kr.co.tjeit.lecturemanager.adapter.ReplyAdapter;
import kr.co.tjeit.lecturemanager.data.Reply;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.GlobalData;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class DailyReplyActivity extends BaseActivity {

    private android.widget.ListView replyListView;
    private android.widget.EditText replyEdt;
    private android.widget.Button replyBtn;
    private android.widget.TextView dateTxt;
    CalendarDay mCalendarDay;

    ReplyAdapter mAdapter;
    private TextView checkTxt;
    private TextView studentListTxt;

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

        studentListTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StudentListActivity.class);
                startActivity(intent);
            }
        });

        replyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ContextUtil.getLoginUser(mContext).getId() + "";
                ServerUtil.register_reply(mContext, id, replyEdt.getText().toString(),
                        new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                try {
                                    if (json.getBoolean("result")) {
                                        Toast.makeText(mContext, json.getString("message"), Toast.LENGTH_SHORT).show();
                                        mAdapter.notifyDataSetChanged();
                                        replyListView.smoothScrollToPosition(GlobalData.allReplyList.size() - 1);
                                        replyEdt.setText("");
                                    } else {
                                        Toast.makeText(mContext, json.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void setValues() {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy년 M월 dd일");
        dateTxt.setText(fm1.format(mCalendarDay.getDate()));

        mAdapter = new ReplyAdapter(mContext, GlobalData.allReplyList);
        replyListView.setAdapter(mAdapter);

        ServerUtil.get_all_replies(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    JSONArray replies = json.getJSONArray("replies");
                    for (int i=0; i<replies.length(); i++) {
                        Reply tmpReply = Reply.getReplyFromJson(replies.getJSONObject(i));
                        GlobalData.allReplyList.add(tmpReply);
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void bindViews() {
        this.replyBtn = (Button) findViewById(R.id.replyBtn);
        this.replyEdt = (EditText) findViewById(R.id.replyEdt);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.studentListTxt = (TextView) findViewById(R.id.studentListTxt);
        this.dateTxt = (TextView) findViewById(R.id.dateTxt);
        this.checkTxt = (TextView) findViewById(R.id.checkTxt);
    }
}
