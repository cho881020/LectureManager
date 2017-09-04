package kr.co.tjeit.lecturemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class LoginActivity extends BaseActivity {

    KakaoSessionCallback ksc;
    CallbackManager cm;
    ProfileTracker pt;

    private Button signUpBtn;
    private Button loginBtn;


    public static LoginActivity myActivity;
    private com.kakao.usermgmt.LoginButton kakaoLoginBtn;
    private com.facebook.login.widget.LoginButton fbLoginBtn;
    private android.widget.EditText idEdt;
    private android.widget.EditText pwEdt;

//    아이디 / 비번 입력 후 로그인 버튼 누르면
//    1. 서버에 실제로 로그인 요청
//    2. 로그인에 성공하면 학생 목록 띄워주기
//    3. 로그인에 실패하면 토스트로 "로그인에 실패햇습니다. 아이디와 비번을 확인해 주세요"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        myActivity = this;

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(myIntent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.sign_in(mContext, idEdt.getText().toString(), pwEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        try {
                            if(json.getBoolean("result")){

//                                실제로 로그인 했다느 ㄴ사실을 기록
//                                로그인 처리가 되고나면, 실제 사용자 정보가
//                                프로필 조회화면에서 나타나도록
                                User loginUser = User.getUserFromJsonObject(json.getJSONObject("user"));
//                                로그인에 성공하면
//                                ~~~님 접속했습니다.
                                Toast.makeText(mContext, loginUser.getName()+"님이 접속했습니다.", Toast.LENGTH_SHORT).show();
                                ContextUtil.login(mContext,loginUser);
                                Log.d("result", json.toString());
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();



                            }
                            else {
                                Toast.makeText(mContext, "로그인에 실패햇습니다. 아이디와 비번을 확인해 주세요", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });

    }

    @Override
    public void setValues() {

//        화면이 시작되면 무조건 로그아웃 처리
//        수업의 편의를 위해 작성하는 코드. (실제로는 안됨)

//        페북 로그아웃
        LoginManager.getInstance().logOut();;
//        카톡 로그아웃
        UserManagement.requestLogout(null);
        ContextUtil.logout(mContext);


        ksc = new KakaoSessionCallback();
        Session.getCurrentSession().addCallback(ksc);

        cm = CallbackManager.Factory.create();

        pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if(currentProfile!=null){
//                    ContextUtil.login(mContext,
//                            new User(currentProfile.getId(),
//                            currentProfile.getName(),
//                                    currentProfile.getProfilePictureUri(500, 500).toString(), "임시 폰번"));
//                    Intent intent  = new Intent(mContext, MainActivity.class);
//                    startActivity(intent);
                }
            }
        };
        fbLoginBtn.registerCallback(cm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });



    }

    @Override
    public void bindViews() {
        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.fbLoginBtn = (com.facebook.login.widget.LoginButton) findViewById(R.id.fbLoginBtn);
        this.kakaoLoginBtn = (LoginButton) findViewById(R.id.kakaoLoginBtn);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode,resultCode,data)){
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
        cm.onActivityResult(requestCode,resultCode,data);
    }

    private class KakaoSessionCallback implements ISessionCallback{

        @Override
        public void onSessionOpened() {
            UserManagement.requestMe(new MeResponseCallback() {
                @Override
                public void onSessionClosed(ErrorResult errorResult) {

                }

                @Override
                public void onNotSignedUp() {

                }

                @Override
                public void onSuccess(UserProfile result) {
//                    ContextUtil.login(mContext,
//                            new User(result.getId() + "", result.getNickname(), result.getProfileImagePath(), "임시폰번"));
//                    Intent intent  = new Intent(mContext, MainActivity.class);
//                    startActivity(intent);
                }
            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

        }
    }

}
