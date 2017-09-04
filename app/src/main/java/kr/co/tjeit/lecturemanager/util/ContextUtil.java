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
    private final static String LOGIN_USER_PHONENUM = "LOGIN_USER_PHONENUM";

    public static void login(Context context, User user) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_ID, user.getId()).commit();
        pref.edit().putString(LOGIN_USER_NAME, user.getName()).commit();
        pref.edit().putString(LOGIN_USER_URL, user.getProfileURL()).commit();
        pref.edit().putString(LOGIN_USER_PHONENUM, user.getPhoneNum()).commit();
    }

    public static User getLoginUser(Context context){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        if (pref.getString(LOGIN_USER_ID, "").equals("")) {
            loginUser = null;
        } else {
            loginUser= new User();
            loginUser.setId(pref.getString(LOGIN_USER_ID,""));
            loginUser.setName(pref.getString(LOGIN_USER_NAME,""));
            loginUser.setPhoneNum(pref.getString(LOGIN_USER_PHONENUM, ""));
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
