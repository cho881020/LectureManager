package kr.co.tjeit.lecturemanager;

import android.Manifest;
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
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class MyProfileActivity extends BaseActivity {

    private de.hdodenhof.circleimageview.CircleImageView profileImg;
    private android.widget.TextView nameTxt;
//    private android.widget.Button linkBtn;
//    private TextView genderTxt;
    private TextView userIdTxt;
    private TextView phoneNumTxt;
    private Button editProfileBtn;
    final int REQ_FOR_GALLEY = 1;


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

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TedPermission.with(mContext).setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
//                        모든 퍼미션이 허가를 받았을 때 실행
                        Toast.makeText(mContext, "모든 허가가 완료 되었다.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_PICK);
//                intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent,REQ_FOR_GALLEY);
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//                        퍼미션이 거부 당한 경우에
//                        어떤 어떤 퍼미션이 거부됐는지, deniedPermissions에 담겨 옴
                        Toast.makeText(mContext, "거부된 권한 : "+deniedPermissions.get(0)+".", Toast.LENGTH_SHORT).show();
                    }
                }).setDeniedMessage("퍼미션을 거부할 경우, 프로필 사진 수정 기능을 활용할 수 없습니다. 설정 -> 권한 탭에서 수정해주세요")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).check();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_FOR_GALLEY){
            if(resultCode==RESULT_OK)
            {
//                서버에 프로필 사진 전송, 후처리리
//                사진 전송 => Bitmap 따서 서버에 보낸다.

//                1. Bitmap 얻어오기


                Uri uri = data.getData();

                try {
                    final Bitmap myBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ServerUtil.updateProfilePhoto(mContext, ContextUtil.getLoginUserData(mContext).getId() + "", myBitmap, new ServerUtil.JsonResponseHandler() {
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
