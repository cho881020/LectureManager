package kr.co.tjeit.lecturemanager.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by the on 2017-08-31.
 */

public class Reply implements Serializable {

    //    댓글 데이터의 고유 속성
    private int id; // DB와의 연동을 고려하는 변수 : 몇번째 댓글.
    private String content; // 댓글의 내용을 저장.
    private Calendar createdAt; // 댓글이 달린 시간을 저장.

    private int user_id; // 어떤 사용자가 작성한 댓글인지 사용자의 번호를 기록
//    ex. user_id => 25 : 이 댓글의 작성자의 이름? 천고바해킹하지마세요요

   //    댓글 데이터의 관계설정
    private User writer;

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
