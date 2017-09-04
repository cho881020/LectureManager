package kr.co.tjeit.lecturemanager.utils;

import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.lecturemanager.datas.UserData;

/**
 * Created by the on 2017-08-31.
 */

public class ContextUtil {

    private static UserData loginUser = null;

    private static final String prefName = "lecturePref";

    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PROFILE_URL = "USER_PROFILE_URL";
    private static final String USER_PHONE_NUM = "USER_PHONE_NUM";

    public static void login(Context context, UserData loginUser) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_ID, loginUser.getUserId()).commit();
        pref.edit().putString(USER_NAME, loginUser.getUserName()).commit();
        pref.edit().putString(USER_PROFILE_URL, loginUser.getUserProfilImg()).commit();
        pref.edit().putString(USER_PHONE_NUM, loginUser.getPhoneNum()).commit();
    }

    public static UserData getLoginUser(Context context) {

        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if(pref.getString(USER_NAME, "").equals("")) {
            loginUser = null;
        }
        else {
            loginUser = new UserData();
            loginUser.setUserId(pref.getString(USER_ID, ""));
            loginUser.setUserName(pref.getString(USER_NAME, ""));
            loginUser.setUserProfilImg(pref.getString(USER_PROFILE_URL, ""));
            loginUser.setPhoneNum(pref.getString(USER_PHONE_NUM, ""));
        }
        return loginUser;
    }

    public static void logoutProcess(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_ID, "").commit();
        pref.edit().putString(USER_NAME, "").commit();
        pref.edit().putString(USER_PROFILE_URL, "").commit();
        pref.edit().putString(USER_PHONE_NUM, "").commit();

        loginUser = null;
    }
}
