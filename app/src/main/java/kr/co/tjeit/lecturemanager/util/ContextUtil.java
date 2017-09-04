package kr.co.tjeit.lecturemanager.util;

import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by the on 2017-08-31.
 */

public class ContextUtil {

    private static User loginUser = null;

    private static final String prefName = "LecturePref";

    private static final String ID = "ID";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_GENDER = "USER_GENDER";
    private static final String USER_PHONE = "USER_PHONE";
    private static final String USER_PROFILE_URL = "USER_PROFILE_URL";


    public static void logout(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putInt(ID, 0).commit();
        pref.edit().putString(USER_ID, "").commit();
        pref.edit().putString(USER_NAME, "").commit();
        pref.edit().putString(USER_PROFILE_URL, "").commit();
        pref.edit().putString(USER_PHONE, "").commit();

    }

    public static void login(Context context, User loginUser) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putInt(ID, loginUser.getId()).commit();
        pref.edit().putString(USER_ID, loginUser.getUserId()).commit();
        pref.edit().putString(USER_NAME, loginUser.getName()).commit();
        pref.edit().putString(USER_PROFILE_URL, loginUser.getProfileURL()).commit();
        pref.edit().putString(USER_PHONE, loginUser.getPhoneNum()).commit();

    }

    public static User getLoginUser(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (pref.getString(USER_ID, "").equals("")) {
//            로그인이 안된 상태
            loginUser = null;
        }
        else {
            loginUser = new User();
            loginUser.setId(pref.getInt(ID, 0));
            loginUser.setUserId(pref.getString(USER_ID, ""));
            loginUser.setName(pref.getString(USER_NAME, ""));
            loginUser.setProfileURL(pref.getString(USER_PROFILE_URL, ""));
            loginUser.setPhoneNum(pref.getString(USER_PHONE, ""));
        }


        return loginUser;
    }


}