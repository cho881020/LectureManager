package kr.co.tjeit.lecturemanager.data;

/**
 * Created by user on 2017-08-31.
 */

public class User {
    String id;
    String name;
    String profileURL;

    public User() {
    }

    public User(String id, String name, String profileURL) {
        this.id = id;
        this.name = name;
        this.profileURL = profileURL;
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
