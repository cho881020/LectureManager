package kr.co.tjeit.lecturemanager.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by KJ_Studio on 2015-12-24.
 */
public class ServerUtil {

    private static final String TAG = ServerUtil.class.getSimpleName();

    private final static String BASE_URL = "http://13.124.238.13/"; // 라이브서버
//    private final static String BASE_URL = "http://share-tdd.com/"; // 개발서버

    public interface JsonResponseHandler {
        void onResponse(JSONObject json);
    }


    // 사용자 관련 함수 모음


    // 회원 가입 기능
    public static void sign_up(final Context context,
                               final String id, final String name,final String password,
                               final String profile_photo, final String phone_num,
                               final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/sign_up";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", id);
        data.put("name", name);
        data.put("password", password);
        data.put("profile_photo", profile_photo);
        data.put("phone_num", phone_num);


        AsyncHttpRequest.post(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 로그인 기능
    public static void sign_in(final Context context,
                               final String id, final String password,
                               final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/sign_in";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", id);
        data.put("password", password);

        data.put("os","Android");
        data.put("deviceToken",FirebaseInstanceId.getInstance().getToken());


        AsyncHttpRequest.post(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }





    // 회원 가입시 아이디 중복 체크
    public static void check_dupl_id(final Context context, final String id, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/check_dupl_id";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", id);

        AsyncHttpRequest.post(context, url,  data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }



    // 모든 회원 목록 받아오기
    public static void get_all_users(final Context context,
                               final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/get_all_users";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
//        data.put("user_id", id);
//        data.put("password", password);


        AsyncHttpRequest.post(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 모든 회원 목록 받아오기
    public static void update_user_info(final Context context,final String id, final String name,
                                        final String phoneNum,
                                     final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/update_user_info";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", id);
//        data.put("password", password);
        data.put("name",name);
        data.put("phone_num",phoneNum);


        AsyncHttpRequest.post(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 댓글 등록
    public static void register_reply(final Context context, final int userId, final String content,
                                        final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/register_reply";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("content",content);
        data.put("user_id",userId+"");


        AsyncHttpRequest.post(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 전체 댓글 보기
    public static void get_all_replies(final Context context, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/get_all_replies";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
//        data.put("content",content);
//        data.put("user_id",userId+"");


        AsyncHttpRequest.post(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }


////    로그인.
////    BASE_URL : 적혀있음.
////    상세주소 :mobile/sign_in
////    통신방식 : POST
////    헤더 : 추가 요구 X
////    파라미터. id : user_id, pw : password
//
//    public static void sign_in(Context context, String id, String pw, final JsonResponseHandler handler){
////        1. 주소 작성 후 String 변수에 저장
//        String url = BASE_URL+"mobile/sing_in";
////        http://13.124.238.13/mobile/sign_in 접속 주소 저장
//
////        데이터를 저장할 Map 형식 변수 생성
////        생성된 변수에 K/V 하나씩 추가.
////        보내줘야할 실제 데이터는 메쏘드의 재료로 받자.
//        Map<String , String> params = new HashMap<>();
//        params.put("user_id", id);
//        params.put("password", pw);
//
//        AsyncHttpRequest.post(context, url, params, true, new AsyncHttpRequest.HttpResponseHandler() {
//            @Override
//            public boolean onPrepare() {
//                return false;
//            }
//
//            @Override
//            public void onResponse(String response) {
//                if(handler!=null){
//                    try {
//                        JSONObject json = new JSONObject(response);
//                        handler.onResponse(json);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//
//            @Override
//            public void onCancelled() {
//
//            }
//        });
//    }






    // 페북 로그인
    public static void facebook_login(final Context context, final String uid, final String name, final String profile_url, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/facebook_login";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("uid", uid);
        data.put("name", name);
        data.put("profile_url", profile_url);

//        디바이스 토큰을 서버에 보내주는 데이터로 추가

        data.put("os","Android");
        data.put("deviceToken",FirebaseInstanceId.getInstance().getToken());

        AsyncHttpRequest.post(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }


    // 쪽지 보내기
    public static void send_message(final Context context, final int userId,final int writerId, final String content,final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/send_message)";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", userId+"");
        data.put("writer_id",writerId+"");
        data.put("content", content);

//        디바이스 토큰을 서버에 보내주는 데이터로 추가

        data.put("os","Android");
        data.put("deviceToken",FirebaseInstanceId.getInstance().getToken());

        AsyncHttpRequest.post(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }


    // 받은 쪽지 목록
    public static void get_my_message(final Context context, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/get_my_message";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
//        누구의 쪽지 목록을 받을것인가? => "로그인한 사람"의 쪽지 목록
        data.put("user_id", ContextUtil.getLoginUserData(context).getId()+"");

        AsyncHttpRequest.post(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }











    // 이미지 올리기
    public static void updateProfilePhoto(final Context context, final String user_id, Bitmap bitmap, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/updateProfilePhoto";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", user_id);

        AsyncHttpRequest.postWithImageFile(context, url,  data, bitmap,"profile", new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }






}
