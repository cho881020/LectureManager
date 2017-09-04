package kr.co.tjeit.lecturemanager.data;

import java.io.Serializable;

/**
 * Created by user on 2017-08-31.
 */

public class User implements Serializable{
    String id;
    String name;
    String profileURL;
    String phoneNum;

    public User() {
    }

    public User(String id, String name, String profileURL, String phoneNum) {
        this.id = id;
        this.name = name;
        this.profileURL = profileURL;
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
