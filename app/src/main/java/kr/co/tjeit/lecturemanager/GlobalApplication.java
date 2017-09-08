package kr.co.tjeit.lecturemanager;

import android.app.Application;

import com.kakao.auth.KakaoSDK;

import kr.co.tjeit.lecturemanager.adapter.KakaoSDKAdapter;

/**
 * Created by user on 2017-08-31.
 */

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSDK.init(new KakaoSDKAdapter(getApplicationContext()));
    }
}
