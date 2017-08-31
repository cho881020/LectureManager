package kr.co.tjeit.lecturemanager.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by user on 2017-08-31.
 */

public class Reply implements Serializable {
//    댓글 데이터 고유 속성
    private String context; // 댓글 내용
    private Calendar createdAt; // 댓글 작성 시간

//    관계 설정
    private User writer;

    public Reply() {
    }

    public Reply(String context, Calendar createdAt, User writer) {
        this.context = context;
        this.createdAt = createdAt;
        this.writer = writer;
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
