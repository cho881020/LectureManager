package kr.co.tjeit.lecturemanager.data;

import java.io.Serializable;

/**
 * Created by tjoeun on 2017-08-31.
 */

public class User implements Serializable{
    private String id;
    private String name;
    private String profileImgPath;

    public User() {
    }

    public User(String id, String name, String profileImgPath) {
        this.id = id;
        this.name = name;
        this.profileImgPath = profileImgPath;
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

    public String getProfileImgPath() {
        return profileImgPath;
    }

    public void setProfileImgPath(String profileImgPath) {
        this.profileImgPath = profileImgPath;
    }
}
