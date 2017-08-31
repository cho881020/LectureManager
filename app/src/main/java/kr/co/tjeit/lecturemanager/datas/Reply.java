package kr.co.tjeit.lecturemanager.datas;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by the on 2017-08-31.
 */

public class Reply implements Serializable {

    private String content; // 댓글의 내용
    private Calendar createAt; // 댓글이 달린 시간 저장.

    // 댓글 관계 설정
    private UserData writer;

    public Reply() {
    }

    public Reply(String content, Calendar createAt, UserData writer) {
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

    public UserData getWriter() {
        return writer;
    }

    public void setWriter(UserData writer) {
        this.writer = writer;
    }
}
