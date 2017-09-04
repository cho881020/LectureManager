package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.utill.ContextUtill;
import kr.co.tjeit.lecturemanager.utill.ServerUtil;

public class EditMyprofileActivity extends BaseActivity {

    //    내 사용자 정보 저장
    User myInfo;
    private android.widget.EditText nameEdt;
    private android.widget.EditText phoneNumEdt;
    private android.widget.Button editProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_myprofile);
        myInfo = ContextUtill.getLoginUser(mContext);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.update_user_info(mContext, myInfo.getUserId(), nameEdt.getText().toString(), phoneNumEdt.getText().toString(),
                        new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {

                            }
                        });
            }
        });
    }

    @Override
    public void setValues() {
        phoneNumEdt.setText(myInfo.getPhoneNum());
        nameEdt.setText(myInfo.getName());

    }

    @Override
    public void bindViews() {
        this.editProfileBtn = (Button) findViewById(R.id.editProfileBtn);
        this.phoneNumEdt = (EditText) findViewById(R.id.phoneNumEdt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
    }
}
