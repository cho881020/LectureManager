package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class EditMyProfileActivity extends BaseActivity {

    private android.widget.EditText nameEdt;
    private android.widget.EditText pwEdt;
    private android.widget.EditText bitrhEdt;
    private android.widget.EditText phoneNumEdt;
    private android.widget.Button editProfileBtn;

    User loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_profile);
        loginUser = ContextUtil.getLoginUser(mContext);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.update_user_info(mContext, loginUser.getId(), nameEdt.getText().toString(), phoneNumEdt.getText().toString(),
                        new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                try {
                                    if (json.getBoolean("result")) {
                                        Toast.makeText(mContext, json.getString("message"), Toast.LENGTH_SHORT).show();
                                        finish();
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

    }

    @Override
    public void bindViews() {
        this.editProfileBtn = (Button) findViewById(R.id.editProfileBtn);
        this.phoneNumEdt = (EditText) findViewById(R.id.phoneNumEdt);
        this.bitrhEdt = (EditText) findViewById(R.id.bitrhEdt);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
    }
}
