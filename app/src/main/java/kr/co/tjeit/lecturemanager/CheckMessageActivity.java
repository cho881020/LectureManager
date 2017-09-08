package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.adapter.CheckMessageAdapter;
import kr.co.tjeit.lecturemanager.data.Message;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class CheckMessageActivity extends BaseActivity {

    CheckMessageAdapter mAdapter;
    private android.widget.ListView messageListView;
    List<Message> mMessageList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_message);
        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        ServerUtil.get_my_message(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("json",json.toString());
                mMessageList.clear();
                try {
                    JSONArray memos = json.getJSONArray("memos");
                    for(int i = 0; i< memos.length(); i++){
                        mMessageList.add(Message.getMessageFromJson(memos.getJSONObject(i)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        mAdapter = new CheckMessageAdapter(mContext, mMessageList);
        messageListView.setAdapter(mAdapter);

    }

    @Override
    public void bindViews() {
        this.messageListView = (ListView) findViewById(R.id.messageListView);
    }
}
