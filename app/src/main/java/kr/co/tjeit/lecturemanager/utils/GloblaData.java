package kr.co.tjeit.lecturemanager.utils;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.datas.Reply;
import kr.co.tjeit.lecturemanager.datas.UserData;
import kr.co.tjeit.lecturemanager.utils.ContextUtil;

/**
 * Created by the on 2017-08-31.
 */

public class GloblaData {
    public static List<UserData> allUsers = new ArrayList<>();

    public static void initGlobaldata() {

        allUsers.clear();
        allUsers.add(new UserData("1", "고동윤", "tempURL", "임시폰번"));
        allUsers.add(new UserData("2", "권성민", "tempURL", "임시폰번"));
        allUsers.add(new UserData("3", "김현철", "tempURL", "임시폰번"));
        allUsers.add(new UserData("4", "박석영", "tempURL", "임시폰번"));
        allUsers.add(new UserData("5", "박수현", "tempURL", "임시폰번"));
        allUsers.add(new UserData("6", "박영주", "tempURL", "임시폰번"));
        allUsers.add(new UserData("7", "손익상", "tempURL", "임시폰번"));
        allUsers.add(new UserData("8", "이승헌", "tempURL", "임시폰번"));
        allUsers.add(new UserData("9", "이요한", "tempURL", "임시폰번"));
        allUsers.add(new UserData("10", "최종한", "tempURL", "임시폰번"));
        allUsers.add(new UserData("11", "한상렬","tempURL", "임시폰번"));
    }
}
