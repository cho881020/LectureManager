package kr.co.tjeit.lecturemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

    private Button signUpBtn;
    private Button loginBtn;

    public static LoginActivity myActivity;
    private com.facebook.login.widget.LoginButton fbLoginBtn;
    private com.kakao.usermgmt.LoginButton comkakaologin;
    private android.widget.EditText idEdt;
    private android.widget.EditText pwEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

                ServerUtil.sign_in(mContext,
                        idEdt.getText().toString(),
                        pwEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {

                                try {
                                    if (json.getBoolean("result")) {
//                                        로그인에 성공
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                        User loginUser = User.getUserFromJsonObject(json.getJSONObject("user"));
                                        ContextUtil.login(mContext, loginUser);
//                                        실제로 로그인했다는 사실을 기록
//                                        로그인처리가 되고 나면, 실제 사용자 정보가 프로필 조회 화면에서 나타나도록 만들어 보자.

                                        Toast.makeText(mContext, loginUser.getName()+"님이 로그인했습니다.", Toast.LENGTH_SHORT).show();

//                                        로그인에 성공하면
//                                        ~~님이 로그인했습니다. Toast 띄우기.
                                    }
                                    else {
//                                        로그인에 실패
                                        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                        alert.setTitle("로그인 실패");
                                        alert.setMessage("로그인에 실패했습니다. \n아이디와 비밀번호를 확인해주세요.");
                                        alert.setPositiveButton("확인", null);
                                        alert.show();
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

//        페북 로그아웃
        LoginManager.getInstance().logOut();
//        카톡 로그아웃
        UserManagement.requestLogout(null);


        ksc = new KakaoSessionCallback();
        Session.getCurrentSession().addCallback(ksc);

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

        ProfileTracker pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {

//                    User tempUser = new User(currentProfile.getId(), currentProfile.getName(), currentProfile.getProfilePictureUri(400, 400).toString(), "임시폰번");
//                    ContextUtil.login(mContext, tempUser);

                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        };



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        cm.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void bindViews() {
        this.comkakaologin = (LoginButton) findViewById(R.id.com_kakao_login);
        this.fbLoginBtn = (com.facebook.login.widget.LoginButton) findViewById(R.id.fbLoginBtn);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
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
//                    User tempUser = new User(result.getId() + "", result.getNickname(), result.getProfileImagePath(), "임시폰번");
//                    ContextUtil.login(mContext, tempUser);

                    Intent intent = new Intent(mContext, MainActivity.class);
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
