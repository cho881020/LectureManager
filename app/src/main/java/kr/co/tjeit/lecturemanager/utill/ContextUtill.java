package kr.co.tjeit.lecturemanager.utill;

import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by the on 2017-08-31.
 */

public class ContextUtill {

    private static User loginUser = null;

    private static final String prefName = "LectureManager";

    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PROFILE_URL = "USER_PROFILE_URL";

    public static void login(Context context, String id, String name, String url) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_ID, loginUser.getUserId()).apply();
        pref.edit().putString(USER_NAME, loginUser.getName()).apply();
        pref.edit().putString(USER_PROFILE_URL, loginUser.getProfileURL()).apply();
    }

    public static User getLoginUser(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);

        if (pref.getString(USER_ID, "").equals("")) {
            loginUser = null;
        } else {
            loginUser = new User();
            loginUser.setUserId(pref.getString(USER_ID, ""));
            loginUser.setName(pref.getString(USER_NAME, ""));
            loginUser.setProfileURL(pref.getString(USER_PROFILE_URL, ""));
        }
        return loginUser;
    }

    public static void login(Context context, User loginUser) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_ID, loginUser.getUserId()).commit();
        pref.edit().putString(USER_NAME, loginUser.getName()).commit();
        pref.edit().putString(USER_PROFILE_URL, loginUser.getProfileURL()).commit();

    }


    public static void logout(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_ID, "").apply();
        pref.edit().putString(USER_NAME, "").apply();
        pref.edit().putString(USER_PROFILE_URL, "").apply();
    }
}
