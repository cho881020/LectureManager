package kr.co.tjeit.lecturemanager.data;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by the on 2017-08-31.
 */

public class User implements Serializable {

    private String userId;
    private String userName;
    private String profileUrl;
    private String phoneNum;

    public User() {

    }

    public User(String userId, String userName, String profileUrl, String phoneNum) {
        this.userId = userId;
        this.userName = userName;
        this.profileUrl = profileUrl;
        this.phoneNum = phoneNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
