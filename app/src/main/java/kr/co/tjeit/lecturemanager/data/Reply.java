package kr.co.tjeit.lecturemanager.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by the on 2017-08-31.
 */

public class Reply implements Serializable {

//    댓글 데이터의 고유 속성
    private String content; // 댓글의 내용을 저장하는 변수
    private Calendar createAt; // 댓글이 달린 시간 저장

//    댓글 데이터의 관계설정
    private User writer;

    public Reply() {
    }

    public Reply(String content, Calendar createAt, User writer) {
        this.content = content;
        this.createAt = createAt;
        this.writer = writer;
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

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
}
