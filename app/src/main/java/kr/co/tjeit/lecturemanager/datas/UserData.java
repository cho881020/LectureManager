package kr.co.tjeit.lecturemanager.datas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by the on 2017-08-31.
 */

public class UserData implements Serializable {

    private int id; // DB와 연동을 고려하는 변수, 몇번째 사용자인지 확인
    private String userId;
    private String userName;
    private String userProfilImg;
    private String phoneNum;

    public static UserData getUserDataFromJsonObject(JSONObject json) {
        // 매번 파싱하기 매우 귀찮다.
        UserData tempUser = new UserData();
        try {
            tempUser.setId(json.getInt("id"));
            tempUser.setUserId(json.getString("user_id"));
            tempUser.setUserName(json.getString("name"));
            tempUser.setUserProfilImg(json.getString("profile_photo"));
            tempUser.setPhoneNum(json.getString("phone_num"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempUser;
    }

    public UserData() {
    }

    public UserData(int id, String userId, String userName, String userProfilImg, String phoneNum) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userProfilImg = userProfilImg;
        this.phoneNum = phoneNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
