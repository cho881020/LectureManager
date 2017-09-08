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

//            1. 내용물 분석 삽입
            message.content = json.getString("content");

//            2. 작성된 시간 분석

            String timeStr = json.getString("created_at");
            message.createdAt = Calendar.getInstance();
//            timeStr => 시간으로 분석해서 createdAt에 대입
//            분석 : parsing, 문자 -> 시간 SimpleDateFormat
//            시간 -> 문자로 출력 : SimpleDateFormat]
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.KOREA);
//            sdf.format() Calendar -> String 메쏘드.
//            sdf.parse() String -> Calendar 분석 메쏘드.
            message.createdAt.setTime(sdf.parse(timeStr));

//            3. 작성자 정보

            message.writer = User.getUserFromJsonObject(json.getJSONObject("writer"));



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
