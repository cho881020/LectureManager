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
    private Button EditProfileBtn;
    private TextView idTxt;
    private TextView phonNumTxt;
    private Button callBtn;

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

        EditProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditMyProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {

//        페이스북의 Graph API 이용해서, 더 많은 정보를 불러오기.

//        GraphRequest request = GraphRequest.newMeRequest(
//                AccessToken.getCurrentAccessToken(),
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(final JSONObject object, GraphResponse response) {
//
//                        Log.d("사용자정보", object.toString());
//
////                      페이스북 페이지 방문 버튼을 누르면
//                        linkBtn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
////                       이 사람의 페이지로 방문할 수 있도록 설정.
////                                Intent 기능 활용
//
//                                Intent intent = new Intent();
//                                intent.setAction(Intent.ACTION_VIEW);
//                                try {
//                                    String link = object.getString("link");
//
//                                    intent.setData(Uri.parse(link));
//                                    startActivity(intent);
//
//
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//
//                            }
//                        });
//
////                        성별 표시 기능.
//
//
//                        String gender = null;
//                        try {
//                            gender = object.getString("gender");
//
//                            if (gender.equals("male")) {
//                                genderTxt.setText("남성");
//                            }
//                            else {
//                                genderTxt.setText("여성");
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }
//        );
//        Bundle params = new Bundle();
//        params.putString("fields", "id,name,gender,link");
//        request.setParameters(params);
//        request.executeAsync();

        phonNumTxt.setText(ContextUtil.getLoginUser(mContext).getPhoneNum());

        idTxt.setText(ContextUtil.getLoginUser(mContext).getUserId());

        nameTxt.setText(ContextUtil.getLoginUser(mContext).getName());

        Glide.with(mContext).load(ContextUtil.getLoginUser(mContext).getProfileURL()).into(profileImg);
    }

    @Override
    public void bindViews() {
        this.linkBtn = (Button) findViewById(R.id.linkBtn);
        this.EditProfileBtn = (Button) findViewById(R.id.EditProfileBtn);
        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.phonNumTxt = (TextView) findViewById(R.id.phonNumTxt);
        this.idTxt = (TextView) findViewById(R.id.idTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}
