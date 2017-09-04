package kr.co.tjeit.lecturemanager.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by user on 2017-08-31.
 */

public class Reply implements Serializable {
//    댓글 데이터 고유 속성
    private int id; // 몇번째 댓글인지
    private String context; // 댓글 내용
    private Calendar createdAt; // 댓글 작성 시간

    private int user_id;    // 어떤 사용자가 작성한 댓글인지, 사용자의 번호를 기록

//    관계 설정
    private User writer;

    public static Reply getReplyFromJson(JSONObject json) {
        Reply tmpReply = new Reply();

        try {
            tmpReply.setId(json.getInt("id"));
            tmpReply.setContext(json.getString("content"));
            tmpReply.setUser_id(json.getInt("user_id"));
            tmpReply.setWriter(User.getUserFromJsonObject(json.getJSONObject("writer")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tmpReply;
    }

    public Reply() {
    }

    public Reply(int id, String context, Calendar createdAt, int user_id, User writer) {
        this.id = id;
        this.context = context;
        this.createdAt = createdAt;
        this.user_id = user_id;
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
}
