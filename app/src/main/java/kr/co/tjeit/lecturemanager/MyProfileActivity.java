package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    private de.hdodenhof.circleimageview.CircleImageView profileImg;
    private android.widget.TextView nameTxt;
//    private android.widget.Button linkBtn;
//    private TextView genderTxt;
    private TextView userIdTxt;
    private TextView phoneNumTxt;
    private Button editProfileBtn;


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

//        페이스북의 Graph API를 이용해서, 더 많은 정보를 불러오기.
//        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(final JSONObject object, GraphResponse response) {
//
//                        try {
//                            if(object.getString("gender").equals("male")){
//                                genderTxt.setText("남자");
//                            }
//                            else {
//                                genderTxt.setText("여지");
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        linkBtn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
////                                이 사람의 페이지로 방문할 수 있도록 설정.
////                                Intent 기능 활용
//                                try {
//
//                                    String link = object.getString("link");
//                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
//                                    startActivity(intent);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//
//
//
//
//                            }
//                        });
//
//                    }
//                }
//        );
//        Bundle params = new Bundle();
//        params.putString("fields", "id,name,gender,link");
//        request.setParameters(params);
//        request.executeAsync();


        User me = ContextUtil.getLoginUserData(mContext);
        nameTxt.setText(me.getName());
        Glide.with(mContext).load(me.getProfileImgPath()).into(profileImg);
        userIdTxt.setText(me.getUserId());
        phoneNumTxt.setText(me.getPhoneNum());


    }

    @Override
    public void bindViews() {
        this.editProfileBtn = (Button) findViewById(R.id.editProfileBtn);
        this.phoneNumTxt = (TextView) findViewById(R.id.phoneNumTxt);
        this.userIdTxt = (TextView) findViewById(R.id.userIdTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}
