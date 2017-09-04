package kr.co.tjeit.lecturemanager;

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

    private Button signUpBtn;
    private Button loginBtn;
    public static LoginActivity myActivity;
    KakaoSessionCallback ksc;

    CallbackManager cm;
    ProfileTracker pt;
    private com.facebook.login.widget.LoginButton fbLoginBtn;
    private com.kakao.usermgmt.LoginButton comkakaologin;
    private android.widget.EditText UserIdEdt;
    private android.widget.EditText UserPwEdt;

//    아이디/비번 입력후, 로그인버튼 누르면
//    1.서버에 실제로 로그인 요청
//    2. 로그인에 성공하면, 학생 목록 띄워주기.
//    3. 로그인에 실패하면, 토스트로 "로그인에 실패했습니다. 아이디와 비번을 확인해주세요"

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

                ServerUtil.sign_in(mContext, UserIdEdt.getText().toString(), UserPwEdt.getText().toString(),
                        new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                try {
                                    if (json.getBoolean("result")) {
//                                        로그인에 성공
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

//                                        로그인에 성공하면
//                                        ---님이 로그인했습니다. Toast 띄우기.

                                       User loginUser = User.getUserFromJsonObject(json.getJSONObject("user"));

                                        ContextUtil.login(mContext, loginUser);

                                        Toast.makeText(mContext, loginUser.getName() + "님이 로그인 했습니다.", Toast.LENGTH_SHORT).show();

                                    } else {
//                                        로그인에 실패
                                        Toast.makeText(mContext, "로그인에 실패했습니다. 아이디와 비번을 확인해주세요.", Toast.LENGTH_SHORT).show();
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
//        강의의 편의를 위해 작성하는 코드. (실제로는 안짬)

//        페북 로그아웃
        LoginManager.getInstance().logOut();
//        카톡 로그아웃
        UserManagement.requestLogout(null);

        ksc = new KakaoSessionCallback();
        Session.getCurrentSession().addCallback(ksc);

        pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile == null) {
//                    로그아웃 됨.
                } else {
//                    로그인 됨.

//                    Toast.makeText(mContext, currentProfile.getName() + "님 접속", Toast.LENGTH_SHORT).show();
//
//                    User tempUser = new User(currentProfile.getId(),
//                            currentProfile.getName(),
//                            currentProfile.getProfilePictureUri(500, 500).toString(), "임시폰번");
//
//                    ContextUtil.login(mContext, tempUser);

                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);

                }
            }
        };

        cm = CallbackManager.Factory.create();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cm.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void bindViews() {
        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.comkakaologin = (LoginButton) findViewById(R.id.com_kakao_login);
        this.fbLoginBtn = (com.facebook.login.widget.LoginButton) findViewById(R.id.fbLoginBtn);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.UserPwEdt = (EditText) findViewById(R.id.UserPwEdt);
        this.UserIdEdt = (EditText) findViewById(R.id.UserIdEdt);
    }

    private class KakaoSessionCallback implements ISessionCallback {

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

//                    User tempUser = new User(result.getId() + "",
//                            result.getNickname(),
//                            result.getProfileImagePath(), "임시폰번");
//
//                    ContextUtil.login(mContext, tempUser);

                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

        }
    }

}
