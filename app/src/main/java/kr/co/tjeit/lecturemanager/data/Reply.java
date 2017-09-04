package kr.co.tjeit.lecturemanager.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by user on 2017-08-31.
 */

public class Reply implements Serializable {

    //    댓글 데이터의 고유 속성
    private int id; // DB와의 연동 : 몇번째 댓글인지
    private String content; // 댓글의 내용을 저장.
    private Calendar createdAt; // 댓글이 달린 시간을 저장.

    private int user_id; // 어떤 사용자가 작성한 댓글인지, 사용자의 번호를 기록하는 변수.
//    EX. user_id => 25 : 이 댓글의 작성자의 이름?

//    댓글데이터의 관계설정

    private User writer;


    public static Reply getReplyFromJson(JSONObject json) {
//        매번 파싱하기 매우 귀찮다.

        Reply tempReply = new Reply();

//        json을 파싱해서, tempUser의 내용물로 채워주기.

        try {

            tempReply.setId(json.getInt("id"));
            tempReply.setContent(json.getString("content"));
            tempReply.setUser_id(json.getInt("user_id"));
            tempReply.setCreatedAt(Calendar.getInstance());
            tempReply.setWriter(User.getUserFromJsonObject(json.getJSONObject("writer")));

//            User 클래스에서 만들어둔 static 메소드를 활용해서 ,
//            다시 파싱을 구현하는 일이 없이, 간단하게 코딩을 마무리한다.
//            => getUserFromJsonObject 기능을 활용했다.

        } catch (JSONException e) {
            e.printStackTrace();
        }

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


}
