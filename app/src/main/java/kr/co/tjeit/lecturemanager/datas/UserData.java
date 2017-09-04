package kr.co.tjeit.lecturemanager.datas;

import java.io.Serializable;

/**
 * Created by the on 2017-08-31.
 */

public class UserData implements Serializable {

    private String userId;
    private String userName;
    private String userProfilImg;
    private String phoneNum;

    public UserData() {
    }

    public UserData(String userId, String userName, String userProfilImg, String phoneNum) {
        this.userId = userId;
        this.userName = userName;
        this.userProfilImg = userProfilImg;
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
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

    public String getUserProfilImg() {
        return userProfilImg;
    }

    public void setUserProfilImg(String userProfilImg) {
        this.userProfilImg = userProfilImg;
    }
}
