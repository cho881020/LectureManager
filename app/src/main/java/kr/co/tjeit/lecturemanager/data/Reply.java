package kr.co.tjeit.lecturemanager.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by the on 2017-08-31.
 */

public class Reply implements Serializable {

//    댓글 데이터의 고유 속성
    private int id; // 데이터베이스와의 연동을 고려
    private String content; // 댓글의 내용을 저장
    private Calendar createdAt; // 댓글이 달린 시간을 저장

    private String user_id; // 어떤 사용자가 작성한 댓글인지, 사용자의 번호를 기록

    private User writer;

    public static Reply getReplyFromJson(JSONObject json){
        Reply tempReply = new Reply();

//        데이터 파싱해서 세팅
//        댓글 작성자도 같이 파싱
        try {
            tempReply.setId(json.getInt("id"));
            tempReply.setContent(json.getString("content"));
            tempReply.setUser_id(json.getString("user_id"));
            tempReply.setCreatedAt(Calendar.getInstance());

//            User클래스에서 만들어둔 static 메쏘드를 활용
//            다시 파싱 구현하는일 없이, 간단하게 코딩 마무리
//            getUserFromJsonObject 기능활용
            tempReply.setWriter(User.getUserFromJsonObject(json.getJSONObject("writer")));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return tempReply;
    }

    public Reply() {
    }

    public Reply(int id, String content, Calendar createdAt, String user_id, User writer) {
        this.id = id;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
}
