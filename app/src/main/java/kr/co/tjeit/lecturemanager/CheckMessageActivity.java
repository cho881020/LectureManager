package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.adapter.MessageAdapter;
import kr.co.tjeit.lecturemanager.data.Message;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class CheckMessageActivity extends BaseActivity {

    private android.widget.ListView messageListView;
    List<Message> mMessageList = new ArrayList<>();
    MessageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_message);
        bindViews();
        setupEvents();
        setValues();

        ServerUtil.get_mymessage(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

            }
        });
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        mAdapter = new MessageAdapter(mContext, mMessageList);
        messageListView.setAdapter(mAdapter);

        ServerUtil.get_mymessage(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                mMessageList.clear();

                try {

                    JSONArray memos = json.getJSONArray("memos");

                    for (int i=0; i< memos.length(); i++){
                        mMessageList.add(Message.getMessageFromJson(memos.getJSONObject(i)));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void bindViews() {

        this.messageListView = (ListView) findViewById(R.id.messageListView);
    }
}
