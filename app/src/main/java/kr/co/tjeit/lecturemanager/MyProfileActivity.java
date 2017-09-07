package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class MyProfileActivity extends BaseActivity {

    final int REQ_FOR_GALLERY = 1;
    User me = null;

    private de.hdodenhof.circleimageview.CircleImageView profileImg;
    private android.widget.TextView nameTxt;
    private android.widget.Button linkBtn;
    private TextView genderTxt;
    private Button profileEdtBtn;
    private TextView idTxt;
    private TextView phoneTxt;
    private TextView editProfileBtn;
    private TextView userIdTxt;

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

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQ_FOR_GALLERY);


            }
        });

        profileEdtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditMyProfileActivity.class);
                startActivity(intent);
            }
        });


//        페이스북의 Graph API 이용해서 더 많은 정보를 불러오기

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_FOR_GALLERY) {
            if (resultCode == RESULT_OK) {
//                서버에 프로필 사진 전송, 후 처리
//                사진전송 = Bitmap 따서 서버에 보낸다.

//                1. Bitmap 얻어오기
                Uri uri = data.getData();

                try {
                    final Bitmap myBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    ServerUtil.updateProfilePhoto(mContext, ContextUtil.getLoginUser(mContext).getId() + "", myBitmap, new ServerUtil.JsonResponseHandler() {
                        @Override
                        public void onResponse(JSONObject json) {
                            Toast.makeText(mContext, "서버에 이미지파일 업로드 완료", Toast.LENGTH_SHORT).show();
                            profileImg.setImageBitmap(myBitmap);

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void setValues() {

        phoneTxt.setText(ContextUtil.getLoginUser(mContext).getPhoneNum());
        userIdTxt.setText(ContextUtil.getLoginUser(mContext).getUserId());
        nameTxt.setText(ContextUtil.getLoginUser(mContext).getUserName());


//        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(final JSONObject object, GraphResponse response) {
//                        Log.d("사용자정보", object.toString());
//
//
//                        linkBtn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
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
//                                    } else {
//                                        genderTxt.setText("여성");
//                                    }
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
//
//        Bundle params = new Bundle();
//        params.putString("fields", "id,name,gender,link,email");
//        request.setParameters(params);
//        request.executeAsync();


        me = ContextUtil.getLoginUser(mContext);
        nameTxt.setText(me.getUserName());
//        Glide.with(mContext).load(me.getProfileUrl()).into(profileImg);


    }

    @Override
    public void bindViews() {
        this.profileEdtBtn = (Button) findViewById(R.id.profileEdtBtn);
        this.phoneTxt = (TextView) findViewById(R.id.phoneTxt);
        this.userIdTxt = (TextView) findViewById(R.id.userIdTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}
