package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kr.co.tjeit.lecturemanager.datas.UserData;
import kr.co.tjeit.lecturemanager.utils.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    private android.widget.ImageView profileImg;
    private android.widget.TextView nameTxt;
    private UserData me = null;

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

    }

    @Override
    public void setValues() {
        me = ContextUtil.getLoginUser(mContext);
        nameTxt.setText(me.getUserName());
        Glide.with(mContext).load(me.getUserProfilImg()).into(profileImg);
    }

    @Override
    public void bindViews() {
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (ImageView) findViewById(R.id.profileImg);
    }
}
