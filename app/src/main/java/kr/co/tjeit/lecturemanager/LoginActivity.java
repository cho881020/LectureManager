package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;

public class LoginActivity extends BaseActivity {

    KakaoSessionCallback ksc;
    CallbackManager cm;
    ProfileTracker pt;

    private Button signUpBtn;
    private Button loginBtn;

    public static LoginActivity myActivity;
    private com.kakao.usermgmt.LoginButton kakaoLoginBtn;
    private com.facebook.login.widget.LoginButton fbLoginBtn;

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
                Intent intent = new Intent(LoginActivity.this, StudentListActivity.class);
                startActivity(intent);
                finish();
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
                    ContextUtil.login(mContext,
                            new User(currentProfile.getId(),
                            currentProfile.getName(),
                            currentProfile.getProfilePictureUri(500,500).toString()));
                    Intent intent  = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
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
                    ContextUtil.login(mContext,
                            new User(result.getId()+"",result.getNickname(),result.getProfileImagePath()));
                    Intent intent  = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                }
            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

        }
    }

}
