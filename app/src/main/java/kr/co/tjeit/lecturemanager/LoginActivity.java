package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class LoginActivity extends BaseActivity {

    KaKaoSessionCallback ksc;
    CallbackManager cm;

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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void setValues() {


        cm = new CallbackManager.Factory().create();
        ksc = new KaKaoSessionCallback();
        Session.getCurrentSession().addCallback(ksc);
        Session.getCurrentSession().checkAndImplicitOpen();

        ProfileTracker pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    Toast.makeText(mContext, currentProfile.getName() + "로그인", Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(mContext, result.getNickname() + "로그인성공", Toast.LENGTH_SHORT).show();

                }
            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

        }
    }
}
