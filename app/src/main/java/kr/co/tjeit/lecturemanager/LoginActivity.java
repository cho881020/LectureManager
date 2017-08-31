package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

public class LoginActivity extends BaseActivity {

    KakaoSessionCallback ksc;

    private Button signUpBtn;
    private Button loginBtn;

    public static LoginActivity myActivity;
    private com.kakao.usermgmt.LoginButton kakaoLoginBtn;

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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void setValues() {

        ksc = new KakaoSessionCallback();
        Session.getCurrentSession().addCallback(ksc);

    }

    @Override
    public void bindViews() {
        this.kakaoLoginBtn = (LoginButton) findViewById(R.id.kakaoLoginBtn);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode,resultCode,data)){
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
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
                    Toast.makeText(mContext, result.getNickname()+"님이 로그인", Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

        }
    }

}
