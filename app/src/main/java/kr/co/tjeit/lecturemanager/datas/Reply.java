package kr.co.tjeit.lecturemanager.datas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Calendar;

import kr.co.tjeit.lecturemanager.utils.GloblaData;

/**
 * Created by the on 2017-08-31.
 */

public class Reply implements Serializable {

    private int id; // DB와의 연동 고려, 몇번째 댓글인지 확인
    private String content; // 댓글의 내용
    private Calendar createAt; // 댓글이 달린 시간 저장.

    private int user_id; // 어떤 사용자가 작성한 댓글인지, 사용자의 번호를 기록

    // 댓글 관계 설정
    private UserData writer;

    public static Reply getReplyFromJsonObject(JSONObject json) {
        Reply tempReply = new Reply();
        try {
            tempReply.setId(json.getInt("id"));
            tempReply.setContent(json.getString("content"));
            tempReply.setCreateAt(Calendar.getInstance());
            tempReply.setUser_id(json.getInt("user_id"));

            tempReply.setWriter(UserData.getUserDataFromJsonObject(json.getJSONObject("writer")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempReply;
    }

    public Reply() {
    }

    public Reply(int id, String content, Calendar createAt, int user_id, UserData writer) {
        this.id = id;
        this.content = content;
        this.createAt = createAt;
        this.user_id = user_id;
        this.writer = writer;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Calendar createAt) {
        this.createAt = createAt;
    }

    public UserData getWriter() {
        return writer;
    }

    public void setWriter(UserData writer) {
        this.writer = writer;
    }
}
