package kr.co.tjeit.lecturemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import java.util.Locale;

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
                                JSONObject user = json.getJSONObject("user");
                                User temp = User.getUserFromJsonObject(user);
                                Toast.makeText(mContext, temp.getProfileImgPath(), Toast.LENGTH_SHORT).show();
                                String welcomMessageStr = String.format(Locale.KOREA,"%s님이 로그인 했습니다", temp.getName());
                                Toast.makeText(mContext, welcomMessageStr, Toast.LENGTH_SHORT).show();
                                ContextUtil.login(mContext, temp);
                                Intent intent = new Intent(mContext, MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setTitle("로그인 실패");
                                builder.setMessage("아이디와 비밀번호를 입력해 주세요.");
                                builder.setPositiveButton("확인",null);
                                builder.show();
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
                    ServerUtil.facebook_login(mContext, currentProfile.getId(), currentProfile.getName(), currentProfile.getProfilePictureUri(500,500).toString(), new ServerUtil.JsonResponseHandler() {
                        @Override
                        public void onResponse(JSONObject json) {
                            Log.d("json",json.toString());
                            try {
                                ContextUtil.login(mContext,
                                        User.getUserFromJsonObject(json.getJSONObject("userInfo")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Intent intent  = new Intent(mContext, MainActivity.class);
                            startActivity(intent);

                        }
                    });



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
                    ServerUtil.facebook_login(mContext, result.getId() + "", result.getNickname(), result.getProfileImagePath(), new ServerUtil.JsonResponseHandler() {
                                @Override
                                public void onResponse(JSONObject json) {
                                    Log.d("json",json.toString());
                                    try {
                                        ContextUtil.login(mContext,
                                                User.getUserFromJsonObject(json.getJSONObject("userInfo")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Intent intent  = new Intent(mContext, MainActivity.class);
                                    startActivity(intent);

                                }
                            });
                }
            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

        }
    }

}
