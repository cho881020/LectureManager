package kr.co.tjeit.lecturemanager.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by the on 2017-08-31.
 */

public class Reply implements Serializable {

    //    댓글 데이터의 고유 속성
    private String content; // 댓글의 내용을 저장.
    private Calendar createdAt; // 댓글이 달린 시간을 저장.

    //    댓글 데이터의 관계설정
    private User writer;

    public Reply() {
    }

    public Reply(String content, Calendar createdAt, User writer) {
        this.content = content;
        this.createdAt = createdAt;
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
}
