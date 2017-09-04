package kr.co.tjeit.lecturemanager.util;

import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by the on 2017-08-31.
 */

public class ContextUtil {

    private static User loginUser = null;

//    1. 사용자의 숫자 ID
//    2. 사용자의 폰번

    private static final String prefName = "출석부연습";
    private static final String ID = "ID";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PROFILE = "USER_PROFILE";
    private static final String USER_PHONE_NUM = "USER_PHONE_NUM";


    public static void login(Context context, User loginUser) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putInt(ID, loginUser.getId()).commit();
        pref.edit().putString(USER_ID, loginUser.getUserId()).commit();
        pref.edit().putString(USER_NAME, loginUser.getUserName()).commit();
        pref.edit().putString(USER_PROFILE, loginUser.getProfileUrl()).commit();
        pref.edit().putString(USER_PHONE_NUM, loginUser.getPhoneNum()).commit();

    }

    public static void logout(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putInt(ID, 0).commit();
        pref.edit().putString(USER_ID, "").commit();
        pref.edit().putString(USER_NAME, "").commit();
        pref.edit().putString(USER_PROFILE, "").commit();
        pref.edit().putString(USER_PHONE_NUM, "").commit();
        loginUser = null;
    }

    public static User getLoginUser(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        if (pref.getString(USER_ID, "").equals("")) {
            loginUser = null;
        } else {
            loginUser = new User();
            loginUser.setId(pref.getInt(ID, 0));
            loginUser.setUserId(pref.getString(USER_ID, ""));
            loginUser.setUserName(pref.getString(USER_NAME, ""));
            loginUser.setProfileUrl(pref.getString(USER_PROFILE, ""));
            loginUser.setPhoneNum(pref.getString(USER_PHONE_NUM, ""));
        }
        return loginUser;
    }


}
