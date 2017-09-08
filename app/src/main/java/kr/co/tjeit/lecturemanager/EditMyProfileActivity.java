package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class EditMyProfileActivity extends BaseActivity {

    //    내 사용자 정보 저장
//     폰번 표시.
    User myInfo;
    private android.widget.EditText nameEdt;
    private android.widget.EditText pwEdt;
    private android.widget.EditText phoneEdt;
    private android.widget.Button editProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_profile);
        myInfo = ContextUtil.getLoginUser(mContext);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.update_user_info(mContext,
                        myInfo.getUserId(),
                        nameEdt.getText().toString(),
                        phoneEdt.getText().toString(),
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
        nameEdt.setText(myInfo.getName());
        phoneEdt.setText(myInfo.getPhoneNum());
    }

    @Override
    public void bindViews() {
        this.editProfileBtn = (Button) findViewById(R.id.editProfileBtn);
        this.phoneEdt = (EditText) findViewById(R.id.phoneEdt);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);

    }
}
