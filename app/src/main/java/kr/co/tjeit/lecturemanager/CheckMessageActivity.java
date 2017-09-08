package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.data.Message;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class CheckMessageActivity extends BaseActivity {

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
//                기존에 사용되는 리스트를 비워주고,
//                추가되는 데이터들을 표시.

                mMessageList.clear();

                try {
                    JSONArray memos = json.getJSONArray("memos");

                    for (int i=0; i < memos.length() ; i++) {
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
