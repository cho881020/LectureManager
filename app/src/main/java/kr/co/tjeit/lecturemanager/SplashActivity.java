package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import kr.co.tjeit.lecturemanager.utils.GloblaData;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        bindViews();
        setValues();
        setUpEvents();

    }

    @Override
    public void setUpEvents() {

    }

    @Override
    public void setValues() {
        GloblaData.initGlobaldata();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(myIntent);
                finish();

            }
        }, 2000);
    }

    @Override
    public void bindViews() {

    }
}
