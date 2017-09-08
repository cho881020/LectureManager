package kr.co.tjeit.lecturemanager.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import kr.co.tjeit.lecturemanager.MainActivity;
import kr.co.tjeit.lecturemanager.R;

/**
 * Created by user on 2017-09-08.
 */

/*
    클래스의 종류 : Service
    Service? 화면을 켜두지 않아도 백그라운드에서 상주하며 실행되고있는 프로그램
    만드는 용도 : "푸시알림이 올 경우", 이 클래스의 메쏘드를 실행
     => 버튼이 눌리면 onClickListener를 하듯,
     => 푸시가 오면 FCMService 메쏘드 하나를 실행.
 */


public class FCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        removeMessage변수안에 푸시의 내용이 모두 저장.
//        이 변수를 활용해서 Notification 만들어서 뿌림.

//        서버에서 보내주는 메세지가 담겨있음.
        String msg = remoteMessage.toString();

        Log.d("데이터", remoteMessage.toString());

//        알림만드는 코드를 분리
        sendNotification(msg);

    }

    private void sendNotification(String msg) {


//        intent? => 화면 이동.
//        Intent 설정 이유 : 알림을 누를 경우 해당 화면으로 이동
        Intent intent = new Intent(this, MainActivity.class);
//        FLAG : 기본적으로 intent는 화면을 새로 만들어서 기존의 화면위에 덮는 역할.
//        FLAG를 설정하면, 무조건 덮는게 아니라, 다른 기능들도 수행 가능.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//        PendingIntent? 인텐트를 보관해주는 보관함.


        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT);


//        화면에 나타나는 알림 생성

//        1. 알림 소리의 종류
        Uri defaultNotiUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

//        2. 알림의 내용 생성

//        AlertDialog.Builder builder = new AlertDialog.Builder(mContext); 와 대응
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        상단 상태바에 뜨게 될 아이콘 (폰에 따라 투명/불투명만 지원 되는 경우)
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
//        알림의 제목 : 메세지의 종류를 보여줌. (댓글 알림, 좋아요)
        builder.setContentTitle("메세지 수신");
//        알림의 내용 : ~~님이 회원님의 게시글을 좋아합니다.
        builder.setContentText(msg);
//        알림 소리의 종류 : Ex. 카톡은 알림소리를 고를수도 있고, 커스텀
        builder.setSound(defaultNotiUri);
//        자동으로 사라지는지
        builder.setAutoCancel(true);
//        어느 화면으로 넘어갈지
        builder.setContentIntent(pendingIntent);


//        SystemService : 안드로이드 OS차원에서 유지하는 백그라운드 작업.
//         대표적으로 푸시 수신.
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//        알림매니저를 이용하여, 아까 준비한 알림을 실제로 띄움.
//        0이라는 숫자 : 알림의 고유 ID.
//        알림이 계속 누적되도록 만들고 싶다면,
//        0으로 고정하는게 아니라, 상황에 맞게 ID를 다른 값으로 부여
        notificationManager.notify(0, builder.build());


    }
}
