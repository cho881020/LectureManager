package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private android.widget.TextView nameTxt;

    User me;
    private TextView genderTxt;
    private TextView phoneNumTxt;
    private Button callBtn;
    private Button linkBtn;
    private Button editProfileBtn;

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
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditMyProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
//        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(final JSONObject object, GraphResponse response) {
//                Log.d("사용자 정보", object.toString());
//
//                try {
//                    if (object.getString("gender").equals("female")) {
//                        genderTxt.setText("여성");
//                    } else {
//                        genderTxt.setText("남성");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                linkBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            Intent intent = new Intent(Intent.ACTION_VIEW);
//                            intent.setData(Uri.parse(object.getString("link")));
//                            startActivity(intent);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        });
//
//        Bundle params = new Bundle();
//        params.putString("fields", "id,name,gender,link");
//        request.setParameters(params);
//        request.executeAsync();

        me = ContextUtil.getLoginUser(mContext);
        nameTxt.setText(me.getUserId());
        phoneNumTxt.setText(me.getPhoneNum());
        Log.d("로그", me.getPhoneNum());
        Glide.with(mContext).load(Uri.parse(me.getProfileURL())).into(profileimage);
    }

    @Override
    public void bindViews() {
        this.editProfileBtn = (Button) findViewById(R.id.editProfileBtn);
        this.linkBtn = (Button) findViewById(R.id.linkBtn);
        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.phoneNumTxt = (TextView) findViewById(R.id.phoneNumTxt);
        this.genderTxt = (TextView) findViewById(R.id.genderTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileimage = (CircleImageView) findViewById(R.id.profile_image);
    }
}
