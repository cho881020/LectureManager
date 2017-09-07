package kr.co.tjeit.lecturemanager.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by tjoeun on 2017-08-31.
 */

public class User implements Serializable{
    private int id; // DB와의 연동을 고려하는 변수 : 몇번째 사용자
    private String userid;
    private String name;
    private String profileImgPath;
    private String phoneNum;

    public static User getUserFromJsonObject(JSONObject json){
//        매번 파싱하기 매우 귀찮다

        User tempUser = new User();
//       json을 파싱해서, tempUser의 내용물로 채워주기
        try {
            tempUser.setId(json.getInt("id"));
            tempUser.setUserId(json.getString("user_id"));
            tempUser.setName(json.getString("name"));
            tempUser.setProfileImgPath("http://13.124.238.13"+json.getJSONObject("profile_photo").getString("url"));
            tempUser.setPhoneNum(json.getString("phone_num"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempUser;
    }

    public User() {
    }

    public User(int id, String user_id, String name, String profileImgPath, String phoneNum) {
        this.id = id;
        this.userid = user_id;
        this.name = name;
        this.profileImgPath = profileImgPath;
        this.phoneNum = phoneNum;
    }


    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImgPath() {
        return profileImgPath;
    }

    public void setProfileImgPath(String profileImgPath) {
        this.profileImgPath = profileImgPath;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
