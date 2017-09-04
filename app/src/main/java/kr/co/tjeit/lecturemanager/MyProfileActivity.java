package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.utill.ContextUtill;

public class MyProfileActivity extends BaseActivity {

    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private android.widget.TextView nameTxt;
    private TextView idTxt;
    private TextView phoneNumTxt;
    private Button callBtn;
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

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditMyprofileActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void setValues() {

////        페이스북 Graph API 이용해서, 더 많은 정보를 불러오기
//
//        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(final JSONObject object, GraphResponse response) {
//                Log.d("사용자정보", object.toString());
//
////                페이스북 페이지 방문 버튼을 누르면 이 사람의 페이지로 방문할 수 있도록 설정
//
//                linkBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        이 사람의 페이지로 방문할 수 있도록 설정
////                        intent 기능 활용
////                        Intent intent = new Intent(mContext, );
////                        startActivity(intent);
//
//                        Intent intent = new Intent();
//                        intent.setAction(intent.ACTION_VIEW);
//                        try {
//                            String link = object.getString("link");
//                            intent.setData(Uri.parse(link));
//                            startActivity(intent);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//            }
//        });
//
//        Bundle params = new Bundle();
//        params.putString("fields", "id, name, link, email");
//        request.setParameters(params);
//        request.executeAsync();

        phoneNumTxt.setText(ContextUtill.getLoginUser(mContext).getPhoneNum());
        idTxt.setText(ContextUtill.getLoginUser(mContext).getUserId());
        nameTxt.setText(ContextUtill.getLoginUser(mContext).getName());
        Glide.with(mContext).load(ContextUtill.getLoginUser(mContext).getProfileURL()).into(profileimage);

    }

    @Override
    public void bindViews() {
        this.editProfileBtn = (Button) findViewById(R.id.editProfileBtn);
        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.phoneNumTxt = (TextView) findViewById(R.id.phoneNumTxt);
        this.idTxt = (TextView) findViewById(R.id.idTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileimage = (CircleImageView) findViewById(R.id.profile_image);
    }
}
