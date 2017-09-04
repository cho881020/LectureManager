package kr.co.tjeit.lecturemanager.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

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

    // 회원 가입시 ID 중복 체크
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

    public static void sign_up(final Context context, final String userId, final String pw, final String name, final String profileURL, final String phoneNum, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/sign_up";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", userId);
        data.put("password", pw);
        data.put("name", name);
        data.put("profile_photo", profileURL);
        data.put("phone_num", phoneNum);


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

//    로그인 기능
    public static void sign_in(final Context context, final String userId, final String pw, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/sign_in";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", userId);
        data.put("password", pw);


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

//    모든 회원 목록 받아오기
    public static void get_all_users(final Context context, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/get_all_users";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();


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

//    회원정보 수정
    public static void update_user_info(final Context context, final String userId, final String userName, final  String userPhone, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/update_user_info";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", userId);
        data.put("name", userName);
        data.put("phone_num", userPhone);


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

//    댓글 등록 확인
    public static void register_reply(final Context context, final int id, final String content, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/register_reply";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", id+"");
        data.put("content", content);


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
    public static void get_all_replies(final Context context, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/get_all_replies";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();


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
}
