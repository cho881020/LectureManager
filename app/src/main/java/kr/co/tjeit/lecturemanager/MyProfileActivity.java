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

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class MyProfileActivity extends BaseActivity {

    final int REQ_FOR_GALLERY = 1;

    private de.hdodenhof.circleimageview.CircleImageView profileImg;
    private android.widget.TextView nameTxt;
    private Button editProfileBtn;
    private TextView userIdTxt;
    private TextView phoneTxt;
    private Button checkMessageBtn;

//    아이디, 폰번 표시

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

        checkMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CheckMessageActivity.class);
                startActivity(intent);
            }
        });

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TedPermission.with(mContext)
                        .setPermissionListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {
//                                모든 퍼미션이 허가를 받았을 때 실행

                                Toast.makeText(mContext, "모든 허가가 완료 되었다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, REQ_FOR_GALLERY);

                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//                                퍼미션이 거부 당한 경우에
//                                어떤 어떤 퍼미션이 거부됐는지, deniedPermissions에 담겨 옴.
                                Toast.makeText(mContext, "거부된 권한 :" + deniedPermissions.get(0), Toast.LENGTH_SHORT).show();
                            }
                        })
                .setDeniedMessage("퍼미션을 거부할 경우, 프로필 사진 수정 기능을 활용할 수 없습니다. 설정 -> 권한 탭에서 수정해주세요.")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();


            }
        });

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditMyProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_FOR_GALLERY) {
            if (resultCode == RESULT_OK) {
//                서버에 프로필 사진 전송, 후처리.
//                사진 전송 => Bitmap 따서 서버에 보낸다.

//                1. Bitmap 얻어오기
                Uri uri = data.getData();
//                갤러리를 통해 받아온것? 선택된 사진이 어디에 있는지 위치 정보.

//                경로를 찾아가서 해당 사진 파일을 Bitmap으로 받아와야함.
//                MediaStore 클래스가 사진 파일 => 비트맵으로 변환해서 가져옴.

//                try : 한번 시도해봐. try 내부는 언제 에러가 터질지 모르는 부분. (예외 발생 가능 지점)
                try {
//                uri 통해서 사진파일로 찾아감.
//                사진파일 있으면, 비트맵으로 변환. (변환을 해주는 객체 : getContentResolver())
//                그냥 이 문장만 쓰면 에러가 남. 왜? 예외처리 필요.
                    final Bitmap myBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    ServerUtil.updateProfilePhoto(mContext, ContextUtil.getLoginUser(mContext).getId() + "",
                            myBitmap, new ServerUtil.JsonResponseHandler() {
                                @Override
                                public void onResponse(JSONObject json) {


                                    Toast.makeText(mContext, "서버에 이미지파일 업로드 완료", Toast.LENGTH_SHORT).show();
                                    profileImg.setImageBitmap(myBitmap);
                                }
                            });


                } catch (IOException e) {
//                    예외가 실제로 발생하면 대처하는 부분 : catch
//                    앱이 죽지 않고 실행상태를 유지하도록 대처하는 부분.

                    Toast.makeText(mContext, "사진을 불러오는 중에 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();

//                    어떤 예외가 발생했는지 로그로 기록.
                    e.printStackTrace();
                }

            }
        }

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

        phoneTxt.setText(ContextUtil.getLoginUser(mContext).getPhoneNum());

        userIdTxt.setText(ContextUtil.getLoginUser(mContext).getUserId());

        nameTxt.setText(ContextUtil.getLoginUser(mContext).getName());

//        Glide.with(mContext).load(ContextUtil.getLoginUser(mContext).getProfileURL()).into(profileImg);
    }

    @Override
    public void bindViews() {
        this.editProfileBtn = (Button) findViewById(R.id.editProfileBtn);
        this.checkMessageBtn = (Button) findViewById(R.id.checkMessageBtn);
        this.phoneTxt = (TextView) findViewById(R.id.phoneTxt);
        this.userIdTxt = (TextView) findViewById(R.id.userIdTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}
