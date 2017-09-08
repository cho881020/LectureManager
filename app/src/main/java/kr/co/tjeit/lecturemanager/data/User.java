package kr.co.tjeit.lecturemanager.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by user on 2017-08-31.
 */

public class User implements Serializable {

    private int id; // DB와의 연동을 고려하는 변수. : 몇번째 사용자인지.
    private String userId;
    private String name;
    private String profileURL;
    private String phoneNum;

    public static User getUserFromJsonObject(JSONObject json) {
//        매번 파싱하기 매우 귀찮다.
        User tempUser = new User();
//        json을 파싱해서, tempUser의 내용물로 채워주기.
        try {
            tempUser.setId(json.getInt("id"));
            tempUser.setUserId(json.getString("user_id"));
            tempUser.setName(json.getString("name"));
            tempUser.setProfileURL("http://13.124.238.13" + json.getJSONObject("profile_photo").getString("url"));
            tempUser.setPhoneNum(json.getString("phone_num"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return tempUser;

    }

    public User() {
    }

    public User(int id, String userId, String name, String profileURL, String phoneNum) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.profileURL = profileURL;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
