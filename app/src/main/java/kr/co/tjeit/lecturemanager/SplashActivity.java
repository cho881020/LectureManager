package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.co.tjeit.lecturemanager.utill.GlobalData;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(myIntent);
                finish();

            }

        }, 2000);

        GlobalData.initGlobalData();
    }

}
