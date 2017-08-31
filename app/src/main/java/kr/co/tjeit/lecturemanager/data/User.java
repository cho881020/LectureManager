package kr.co.tjeit.lecturemanager.data;

import android.util.Log;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by the on 2017-08-31.
 */

public class User implements Serializable{

    private String userId;
    private String name;
    private String profileURL;

    public User() {
    }

    public User(String id, String name, String profileImagePath) {
        this.userId = id;
        this.name = name;
        this.profileURL = profileImagePath;
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
}
