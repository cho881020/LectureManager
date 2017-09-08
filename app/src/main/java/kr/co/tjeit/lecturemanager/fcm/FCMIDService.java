package kr.co.tjeit.lecturemanager.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by tjoeun on 2017-09-08.
 */

/*
    Service : 백그라운드 동작
    동작목적 : 기기 ID가 등록될 경우 -> 우리 자체 서버에 등록시켜주기 위한 기능.

    상세동작 : 구글 서버에 고유 ID 부여 요청. => 받아 오는 시간 까지 대기
              => 등록이 완료되면 메쏘드 하나를 실행.
 */


public class FCMIDService extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh() {

//        onTokenRefresh 메쏘드 : 토큰값이 변경됨을 확인했을때.
//        없다가 생기는것도 마찬가지 동작.

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("token값", token);
    }
}
