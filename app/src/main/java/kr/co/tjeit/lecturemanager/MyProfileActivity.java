package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.util.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    private de.hdodenhof.circleimageview.CircleImageView profileImg;
    private android.widget.TextView nameTxt;
    private android.widget.Button linkBtn;
    private TextView genderTxt;
    private TextView userIdTxt;
    private Button profileEdtBtn;


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

        profileEdtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditMyProfile.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {
//        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(final JSONObject object, GraphResponse response) {
//
////                페이스북 페이지 방문 버튼을 누르면 이 사람의 페이지로 방문할 수 있도록 설정
//
//                        linkBtn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent();
//                                intent.setAction(Intent.ACTION_VIEW);
//                                try {
//                                    String link = object.getString("link");
//                                    intent.setData(Uri.parse(link));
//                                    startActivity(intent);
//
//                                    String gender = object.getString("gender");
//                                    if (gender.equals("male")) {
//                                        genderTxt.setText("남성");
//                                    }
//                                    else {
//                                        genderTxt.setText("여성");
//                                    }
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//
//
//                    }
//                }
//        );
//        Bundle params = new Bundle();
//        params.putString("fields", "id,name,link,email");
//        request.setParameters(params);
//        request.executeAsync();

        nameTxt.setText(ContextUtil.getLoginUser(mContext).getName());
        Glide.with(this).load(ContextUtil.getLoginUser(mContext).getProfileURL()).into(profileImg);
    }

    @Override
    public void bindViews() {
        this.profileEdtBtn = (Button) findViewById(R.id.profileEdtBtn);
        this.userIdTxt = (TextView) findViewById(R.id.userIdTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}
