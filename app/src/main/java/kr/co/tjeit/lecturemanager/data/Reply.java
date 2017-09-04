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
    private int id; // DB와의 연동 // 몇번째 댓글?
    private String content; // 댓글의 내용을 저장하는 변수
    private Calendar createAt; // 댓글이 달린 시간 저장

    private int user_id; // 어떤 사용자가 작성한 댓글인지 사용자의 번호를 기록.
//    Ex. user_id => 25라면, 이 댓글의 작성자의 이름?

//    댓글 데이터의 관계설정
    private User writer;

    public static Reply getReplyFromJson(JSONObject json) {
        Reply tempReply = new Reply();

        try {
            tempReply.setId(json.getInt("id"));
            tempReply.setContent(json.getString("content"));
            tempReply.setUser_id(json.getInt("user_id"));
            tempReply.setCreateAt(Calendar.getInstance());

//            user클래스에서 만들어둔 static 메소드를 활용해서 다시 파싱을 구현하는 일 없이 간단히 마무리 가능.
            tempReply.setWriter(User.getUserFromJsonObject(json.getJSONObject("writer")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        데이터 파싱해서 셋팅
//        댓글 작성자도 같이 파싱
//        댓글 목록 화면에서 모든 댓글을 불러다가 출력하자.
        return  tempReply;
    }

    public Reply() {
    }

    public Reply(int id, String content, Calendar createAt, int user_id, User writer) {
        this.id = id;
        this.content = content;
        this.createAt = createAt;
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

    public Calendar getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Calendar createAt) {
        this.createAt = createAt;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
}
