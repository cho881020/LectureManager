package kr.co.tjeit.lecturemanager;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private android.widget.TextView nameTxt;

    User me = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {

    }

    @Override
    public void setValues() {
        me = ContextUtil.getLoginUser(mContext);
        nameTxt.setText(me.getName());
        Glide.with(mContext).load(Uri.parse(me.getProfileURL())).into(profileimage);
    }

    @Override
    public void bindViews() {
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileimage = (CircleImageView) findViewById(R.id.profile_image);
    }
}
