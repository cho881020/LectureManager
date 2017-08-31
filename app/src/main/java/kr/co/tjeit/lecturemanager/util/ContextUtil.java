package kr.co.tjeit.lecturemanager.util;

import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by user on 2017-08-31.
 */

public class ContextUtil {
    private static User loginUser = null;

    private final static String prefName = "lecturePref";
    private final static String LOGIN_USER_ID = "LOGIN_USER_ID";
    private final static String LOGIN_USER_NAME = "LOGIN_USER_NAME";
    private final static String LOGIN_USER_URL = "LOGIN_USER_URL";

    public static void login(Context context, String id, String name, String url) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_ID, id).commit();
        pref.edit().putString(LOGIN_USER_NAME, name).commit();
        pref.edit().putString(LOGIN_USER_URL, url).commit();
    }

    public static User getLoginUser(Context context){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        if (pref.getString(LOGIN_USER_ID, "").equals("")) {
            loginUser = null;
        } else {
            loginUser= new User();
            loginUser.setId(pref.getString(LOGIN_USER_ID,""));
            loginUser.setName(pref.getString(LOGIN_USER_NAME,""));
            loginUser.setProfileURL(pref.getString(LOGIN_USER_URL,""));
        }
        return loginUser;
    }

    public static void logout(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_ID, "").commit();
        pref.edit().putString(LOGIN_USER_NAME, "").commit();
        pref.edit().putString(LOGIN_USER_URL, "").commit();
    }
}
