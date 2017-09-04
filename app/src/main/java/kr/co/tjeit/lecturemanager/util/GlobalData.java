package kr.co.tjeit.lecturemanager.util;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.data.Reply;
import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by user on 2017-08-31.
 */

public class GlobalData {
    public static List<User> allUserList = new ArrayList<>();
    public static List<Reply> allReplyList = new ArrayList<>();

    public static void initData() {
        allUserList.clear();
        allUserList.add(new User("1", "고동윤", "tmpURL", "임시"));
        allUserList.add(new User("2", "권성민", "tmpURL", "임시"));
        allUserList.add(new User("3", "김현철", "tmpURL", "임시"));
        allUserList.add(new User("4", "박석영", "tmpURL", "임시"));
        allUserList.add(new User("5", "박수현", "tmpURL", "임시"));
        allUserList.add(new User("6", "박영주", "tmpURL", "임시"));
        allUserList.add(new User("7", "손익상", "tmpURL", "임시"));
        allUserList.add(new User("8", "이승헌", "tmpURL", "임시"));
        allUserList.add(new User("9", "이요한", "tmpURL", "임시"));
        allUserList.add(new User("10", "최종환", "tmpURL", "임시"));
        allUserList.add(new User("11", "한상열", "tmpURL", "임시"));
    }
}
