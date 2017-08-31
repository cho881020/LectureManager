package kr.co.tjeit.lecturemanager;

import android.app.Application;
import android.content.Context;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

import kr.co.tjeit.lecturemanager.adapters.KakaoSDKAdapter;

/**
 * Created by the on 2017-08-31.
 */

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSDK.init(new KakaoSDKAdapter(getApplicationContext()));
    }
}
