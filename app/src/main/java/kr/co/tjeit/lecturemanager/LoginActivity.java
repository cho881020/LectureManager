package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
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
import kr.co.tjeit.lecturemanager.util.GlobalData;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class LoginActivity extends BaseActivity {

    KaKaoSessionCallback ksc;
    CallbackManager cm;

    private Button signUpBtn;
    private Button loginBtn;

    public static LoginActivity myActivity;
    private com.kakao.usermgmt.LoginButton kakaoLoginBtn;
    private com.facebook.login.widget.LoginButton fbLoginBtn;
    private android.widget.EditText idEdt;
    private android.widget.EditText pwEdt;


//    아이디 / 비번 입력 후 로그인 버튼 누르면
//    1. 서버에 실제로 로그인요청
//    2. 로그인에 성공하면 학생 목록 띄워주기
//    3. 로그인에 실패하면 토스트로 "로그인에 실패했습니다. 아이디와 비번을 확인해주세요.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myActivity = this;
        bindViews();
        setupEvents();
        setValues();

//        재접속 할때마다 페이스북 로그아웃
        LoginManager.getInstance().logOut();
//        재접속 할때마다 카카오톡 로그아웅
        UserManagement.requestLogout(null);


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
                            if (json.getBoolean("result")) {
//                            Toast.makeText(mContext, json.getJSONObject("user").getString("user_id")+ "님이 로그인 했습니다.", Toast.LENGTH_SHORT).show();

//                            userData에 getUserFromJsonObject에 자주하는 파싱정보를 넣어두고
//                            tempuser에 다 담아두고~
                                User temp = User.getUserFromJsonObject(json.getJSONObject("user"));


                                String welcomMessageStr = String.format(Locale.KOREA, "%s님이 로그인 했습니다.", temp.getUserName());

//                            ContextUtil.login(mContext, tempUser);
                                ContextUtil.login(mContext, temp);


                                Intent intent = new Intent(mContext, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setTitle("로그인 실패");
                                builder.setMessage("아이디와 비번을 확인해주세요.");
                                builder.setPositiveButton("확인", null);
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


        cm = new CallbackManager.Factory().create();
        ksc = new KaKaoSessionCallback();
        Session.getCurrentSession().addCallback(ksc);
//        Session.getCurrentSession().checkAndImplicitOpen();

        ProfileTracker pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, final Profile currentProfile) {

                if (currentProfile != null) {
//                    로그인됨


                    ServerUtil.facebook_login(mContext, currentProfile.getId(), currentProfile.getName(), currentProfile.getProfilePictureUri(500, 500).toString(), new ServerUtil.JsonResponseHandler() {
                        @Override
                        public void onResponse(JSONObject json) {

                            try {
                                User tempUser = User.getUserFromJsonObject(json.getJSONObject("userInfo"));
                                ContextUtil.login(mContext, tempUser);
                                Intent intent = new Intent(mContext, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });


//                    User tempUser = new User(currentProfile.getId(), currentProfile.getName(), currentProfile.getProfilePictureUri(500, 500).toString(), "임시폰번");
//                    ContextUtil.login(mContext, tempUser);
//                    Toast.makeText(mContext, currentProfile.getName() + "로그인", Toast.LENGTH_SHORT).show();

                } else {
//                    로그인안됨

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
        this.kakaoLoginBtn = (LoginButton) findViewById(R.id.kakaoLoginBtn);
        this.fbLoginBtn = (com.facebook.login.widget.LoginButton) findViewById(R.id.fbLoginBtn);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {

        }
        super.onActivityResult(requestCode, resultCode, data);
        cm.onActivityResult(requestCode, resultCode, data);
    }

    public class KaKaoSessionCallback implements ISessionCallback {

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


                            try {
                                User temp = User.getUserFromJsonObject(json.getJSONObject("userInfo"));
                                ContextUtil.login(mContext, temp);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });
//                    User tempUser = new User(result.getId() + "", result.getNickname(), result.getProfileImagePath(), "임시폰번");
//                    ContextUtil.login(mContext, tempUser);
//                    Toast.makeText(mContext, result.getNickname() + "로그인성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, StudentListActivity.class);
                    startActivity(intent);
                    finish();

                }
            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

        }
    }
}
