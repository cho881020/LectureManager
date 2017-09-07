package kr.co.tjeit.lecturemanager.data;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by the on 2017-08-31.
 */

public class User implements Serializable {

    private int id; // 데이터베이스와의 연동을 고려. : 몇번째 사용자
    private String userId;
    private String userName;
    private String profileUrl;
    private String phoneNum;

    public static User getUserFromJsonObject(JSONObject json) {
//      매번 파싱하기 매우 귀찮다.
        User tempUser = new User();

//        json을 파싱해서, tempUser의 내용물로 채워주기
        try {
            tempUser.setId(json.getInt("id"));
            tempUser.setUserId(json.getString("user_id"));
            tempUser.setUserName(json.getString("name"));
            tempUser.setProfileUrl("http://13.124.238.13" + json.getJSONObject("profile_photo").getString("url"));
            tempUser.setPhoneNum(json.getString("phone_num"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempUser;
    }

    public User() {

    }

    public User(int id, String userId, String userName, String profileUrl, String phoneNum) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.profileUrl = profileUrl;
        this.phoneNum = phoneNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
