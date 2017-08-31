package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.utill.ContextUtill;

public class MyProfileActivity extends BaseActivity {

    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private android.widget.TextView nameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        nameTxt.setText(ContextUtill.getLoginUser(mContext).getName());
        Glide.with(mContext).load(ContextUtill.getLoginUser(mContext).getProfileURL()).into(profileimage);

    }

    @Override
    public void bindViews() {
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileimage = (CircleImageView) findViewById(R.id.profile_image);
    }
}
