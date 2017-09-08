package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class SendMessageActivity extends BaseActivity {

    User mUser;
    private android.widget.TextView receiveUserNameTxt;
    private android.widget.EditText contentEdt;
    private android.widget.Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        mUser = (User) getIntent().getSerializableExtra("student");
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.send_message(mContext,
                        contentEdt.getText().toString(),
                        mUser.getId(),
                        new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                contentEdt.setText("");
                                Toast.makeText(mContext, "쪽지 전송이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    public void setValues() {
        receiveUserNameTxt.setText(mUser.getName());
    }

    @Override
    public void bindViews() {
        this.sendBtn = (Button) findViewById(R.id.sendBtn);
        this.contentEdt = (EditText) findViewById(R.id.contentEdt);
        this.receiveUserNameTxt = (TextView) findViewById(R.id.receiveUserNameTxt);

    }
}
