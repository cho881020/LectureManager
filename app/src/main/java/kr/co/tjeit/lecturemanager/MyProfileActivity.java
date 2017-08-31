package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.datas.UserData;
import kr.co.tjeit.lecturemanager.utils.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    private android.widget.ImageView profileImg;
    private android.widget.TextView nameTxt;
    private UserData me = null;
    private android.widget.Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        bindViews();
        setValues();
        setUpEvents();
    }

    @Override
    public void setUpEvents() {
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                    }
                });
                finishAffinity();
                Session.getCurrentSession().clearCallbacks();
                Session.getCurrentSession().close();
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
        me = ContextUtil.getLoginUser(mContext);
        nameTxt.setText(me.getUserName());
        Glide.with(mContext).load(me.getUserProfilImg()).into(profileImg);
    }

    @Override
    public void bindViews() {
        this.testBtn = (Button) findViewById(R.id.testBtn);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}
