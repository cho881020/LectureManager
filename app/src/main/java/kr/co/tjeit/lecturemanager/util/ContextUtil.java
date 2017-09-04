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

//    1. 사용자 숫자 ID
//    2. 사용자 폰번
    private static final String ID = "ID";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PROFILEIMG_PATH = "USER_PROFILEIMG_PATH";
    private static final String USER_PHONE_NUM= "USER_PHONE_NUM";

    public static void login(Context context, User loginUser){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putInt(ID,loginUser.getId()).commit();
        pref.edit().putString(USER_ID,loginUser.getUserId()).commit();
        pref.edit().putString(USER_NAME,loginUser.getName()).commit();
        pref.edit().putString(USER_PROFILEIMG_PATH,loginUser.getProfileImgPath()).commit();
        pref.edit().putString(USER_PHONE_NUM,loginUser.getPhoneNum()).commit();
    }

    public static void logout(Context context){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putInt(ID,0).apply();
        pref.edit().putString(USER_ID,"").apply();
        pref.edit().putString(USER_NAME,"").apply();
        pref.edit().putString(USER_PROFILEIMG_PATH,"").apply();
        pref.edit().putString(USER_PHONE_NUM,"").apply();

    }

    public static User getLoginUserData(Context context){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        if(pref.getString(USER_ID,"").equals("")){
            loginUser=null;
        }
        else{
            loginUser = new User();
            loginUser.setId(pref.getInt(ID,0));
            loginUser.setUserId(pref.getString(USER_ID,""));
            loginUser.setName(pref.getString(USER_NAME,""));
            loginUser.setProfileImgPath(pref.getString(USER_PROFILEIMG_PATH,""));
            loginUser.setPhoneNum(pref.getString(USER_PHONE_NUM,""));
        }
        return loginUser;

    }

}
