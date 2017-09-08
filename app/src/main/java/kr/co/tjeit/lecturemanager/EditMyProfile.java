package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import kr.co.tjeit.lecturemanager.datas.UserData;
import kr.co.tjeit.lecturemanager.utils.ContextUtil;
import kr.co.tjeit.lecturemanager.utils.ServerUtil;

public class EditMyProfile extends BaseActivity {

    private UserData myInfo = null;

    private android.widget.EditText nameEdt;
    private android.widget.EditText phoneEdt;
    private android.widget.Button editProfileBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_profile);
        myInfo = ContextUtil.getLoginUser(mContext);
        bindViews();
        setValues();
        setupEvents();
    }

    @Override
    public void setupEvents() {
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.update_user_info(mContext, myInfo.getUserId(), nameEdt.getText().toString(), phoneEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        try {
                            String message = json.getString("message");
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finish();
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
        this.phoneEdt = (EditText) findViewById(R.id.phoneEdt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
    }
}
