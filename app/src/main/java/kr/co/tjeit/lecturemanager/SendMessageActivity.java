package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class SendMessageActivity extends BaseActivity {

    private android.widget.TextView sendUserTxt;
    private android.widget.EditText messageEdt;
    private android.widget.Button sendingBtn;
    User sendUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        sendUser = (User)getIntent().getSerializableExtra("sendMessageStudent");
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        sendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ServerUtil.send_message(mContext, sendUser.getId(),
                        ContextUtil.getLoginUserData(mContext).getId(),
                        messageEdt.getText().toString(),
                        new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        messageEdt.setText("");
                        Toast.makeText(mContext, "메시지 보냄", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    @Override
    public void setValues() {
        sendUserTxt.setText(sendUser.getName());
    }

    @Override
    public void bindViews() {
        this.sendingBtn = (Button) findViewById(R.id.sendingBtn);
        this.messageEdt = (EditText) findViewById(R.id.messageEdt);
        this.sendUserTxt = (TextView) findViewById(R.id.sendUserTxt);
    }
}
