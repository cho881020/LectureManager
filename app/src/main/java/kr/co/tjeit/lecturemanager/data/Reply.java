package kr.co.tjeit.lecturemanager.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by tjoeun on 2017-08-31.
 */

public class Reply implements Serializable{

//    댓글 데이터의 고유 속성
    private int id; // DB와의 연동 : 몇번째 댓글
    private String content; // 댓글의 내용을 저장.
    private Calendar createdAt; // 댓글이 달린 시간을 저장.

    private int user_id; // 어떤 사용자가 작성한 댓글인지, 사용자의 번호를 기록.


//    댓글데이터의 관계설정

    private User writer;

    public static Reply getReplyFromJson(JSONObject json){
        Reply tempReply = new Reply();

//        데이터 파싱해서 세팅.
//        댓글 작성자도 같이 파싱.
        try {
            tempReply.setId(json.getInt("id"));
            tempReply.setUser_id(json.getInt("user_id"));
            tempReply.setContent(json.getString("content"));
            tempReply.setCreatedAt(Calendar.getInstance());
//            User클래스에서 만들어둔 static 메쏘드를 활용해서
//            다시 파싱을 구현하는 일 없이, 간단하게 코딩을 마무리.
//            =>getUserFromJsonObject기능 활용
            tempReply.setWriter(User.getUserFromJsonObject(json.getJSONObject("writer")));
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        댓글 목록 화면에서 모든 댓글을 불러다가 출력.

        return tempReply;
    }


    public Reply() {
    }


    public Reply(int id, String content, Calendar createdAt, int user_id, User writer) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.user_id = user_id;
        this.writer = writer;
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

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
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
}
