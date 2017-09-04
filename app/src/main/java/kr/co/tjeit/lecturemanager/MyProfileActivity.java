package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private TextView genderTxt;
    private Button linkBtn;
    private TextView userIdTxt;
    private TextView phoneTxt;
    private Button callBtn;
    private Button profileEditBtn;

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

        profileEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditMyProfile.class);
                startActivity(intent);
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + me.getPhoneNum()));
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
        me = ContextUtil.getLoginUser(mContext);
        nameTxt.setText(me.getUserName());
        userIdTxt.setText(me.getUserId());
        phoneTxt.setText(me.getPhoneNum());
        Glide.with(mContext).load(me.getUserProfilImg()).into(profileImg);

//        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(final JSONObject object, GraphResponse response) {
//                Log.d("사용자정보", object.toString());
//                try {
//                    String gender = object.getString("gender");
//                    if(gender.equals("male")) {
//                        genderTxt.setText("남성");
//                    }
//                    else {
//                        genderTxt.setText("여성");
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                linkBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent();
//                        intent.setAction(Intent.ACTION_VIEW);
//
//                        try {
//                            String link = object.getString("link");
//                            intent.setData(Uri.parse(link));
//                            startActivity(intent);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        });
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "id,gender,name,link");
//        request.setParameters(parameters);
//        request.executeAsync();
    }

    @Override
    public void bindViews() {
        this.testBtn = (Button) findViewById(R.id.testBtn);
        this.linkBtn = (Button) findViewById(R.id.linkBtn);
        this.profileEditBtn = (Button) findViewById(R.id.profileEditBtn);
        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.phoneTxt = (TextView) findViewById(R.id.phoneTxt);
        this.userIdTxt = (TextView) findViewById(R.id.userIdTxt);
        this.genderTxt = (TextView) findViewById(R.id.genderTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}
