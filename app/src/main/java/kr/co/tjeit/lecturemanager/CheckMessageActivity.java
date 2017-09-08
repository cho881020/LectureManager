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
    private MessageAdapter mMessageAdapter;
    List<Message> messageList = new ArrayList<>();

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

        mMessageAdapter = new MessageAdapter(mContext, messageList);
        messageListView.setAdapter(mMessageAdapter);

        ServerUtil.get_my_message(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    messageList.clear();
                    JSONArray messages = json.getJSONArray("memos");

                    for (int i = 0; i < messages.length(); i++) {
                        messageList.add(Message.getMessageFromJson(messages.getJSONObject(i)));
                    }
                    mMessageAdapter.notifyDataSetChanged();
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
