package kr.co.tjeit.lecturemanager.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by user on 2017-09-08.
 */

public class Message implements Serializable {
//    자체적으로 저장할 변수

    private String content; // 내용을 저장하는 변수.
    private Calendar createdAt; // 쪽지가 작성된 시간.

//    다른 객체와의 관계
    private User writer; // 누가 보냈는지?

    public static Message getMessageFromJson(JSONObject json) {
//        쪽지를 서버에서 받으면 파싱을 전담하는 메쏘드
        Message message = new Message();
//        파싱작업을 진행 (문제)
        try {
            message.setContent(json.getString("content"));
            message.setWriter(User.getUserFromJsonObject(json.getJSONObject("writer")));

            // 1. 내용물 분석 삽입


            // 2. 시간 받아서 바꿔주기
            String timeStr = json.getString("created_at");
            message.createdAt = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-'T'hh:mm:ss.SSS'Z'", Locale.KOREAN);

            message.createdAt.setTime(sdf.parse(timeStr));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return message;

    }

    public Message() {
    }

    public Message(String content, Calendar createdAt, User writer) {
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
