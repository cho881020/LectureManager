package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.util.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    private de.hdodenhof.circleimageview.CircleImageView profileImg;
    private android.widget.TextView nameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        bindViews();
        setValues();
        setupEvents();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        nameTxt.setText(ContextUtil.getUserName(mContext));

        Glide.with(this).load(ContextUtil.getUserProfileUrl(mContext)).into(profileImg);
    }

    @Override
    public void bindViews() {
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}
