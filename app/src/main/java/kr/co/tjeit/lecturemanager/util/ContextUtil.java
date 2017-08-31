package kr.co.tjeit.lecturemanager.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.login.LoginManager;

import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by tjoeun on 2017-08-31.
 */

public class ContextUtil {

    private static User loginUser = null;
    private static final String prefName = "LectureManagerPref";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PROFILEIMG_PATH = "USER_PROFILEIMG_PATH";

    public static void login(Context context, User loginuser){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_ID,loginuser.getId()).apply();
        pref.edit().putString(USER_NAME,loginuser.getName()).apply();
        pref.edit().putString(USER_PROFILEIMG_PATH,loginuser.getProfileImgPath()).apply();
    }

    public static void logout(Context context){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_ID,"").apply();
        pref.edit().putString(USER_NAME,"").apply();
        pref.edit().putString(USER_PROFILEIMG_PATH,"").apply();
    }

    public static User getLoginUserData(Context context){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        if(pref.getString(USER_ID,"").equals("")){
            loginUser=null;
        }
        else{
            loginUser = new User();
            loginUser.setId(pref.getString(USER_ID,""));
            loginUser.setName(pref.getString(USER_NAME,""));
            loginUser.setProfileImgPath(pref.getString(USER_PROFILEIMG_PATH,""));
        }
        return loginUser;

    }

}
