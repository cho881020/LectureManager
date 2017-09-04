package kr.co.tjeit.lecturemanager.utill;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.data;


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

    // 회원 가입
//    회원 가입시 아이디 중복 체크
    public static void check_dupl_id(final Context context, final String user_id,final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/check_dupl_id";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", user_id);

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

//    자체 로그인 기능
    public static void sign_up(final Context context, final String user_id, final String password, final String name,
                               final String profile_photo, final String phone_num, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/sign_up";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", user_id);
        data.put("password", password);
        data.put("name", name);
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

    public static void sign_in(final Context context, final String user_id, final String password, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/sign_in";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", user_id);
        data.put("password", password);

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

//    회원 정보 수정
    public static void update_user_info(final Context context, final String userId, final String userName, final String userPhone, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/update_user_info";
        Map<String, String> data = new HashMap<String, String>();

        data.put("user_id", userId);
        data.put("name", userName);
        data.put("phone_num", userPhone);

        //		String registrationId = ContextUtil.getRegistrationId(context);

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

    public static void register_reply(final Context context, final String content, final int user_id, final JsonResponseHandler handler) {
        String url = BASE_URL+"mobile/register_reply";
        Map<String, String> data = new HashMap<String, String>();

        data.put("user_id", user_id+"");
        data.put("content", content);

        //		String registrationId = ContextUtil.getRegistrationId(context);

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
        Map<String, String> data = new HashMap<String, String>();

        //		String registrationId = ContextUtil.getRegistrationId(context);

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
