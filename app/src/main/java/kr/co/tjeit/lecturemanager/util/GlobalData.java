package kr.co.tjeit.lecturemanager.util;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by the on 2017-08-31.
 */

public class GlobalData  {

    public static List<User> allUsers = new ArrayList<>();

    public static void initData () {

        allUsers.clear();
        allUsers.add(new User("1", "고동윤", "tempURL"));
        allUsers.add(new User("2", "권성민", "tempURL"));
        allUsers.add(new User("3", "김현철", "tempURL"));
        allUsers.add(new User("4", "박석영", "tempURL"));
        allUsers.add(new User("5", "박수현", "tempURL"));
        allUsers.add(new User("6", "박영주", "tempURL"));
        allUsers.add(new User("7", "손익상", "tempURL"));
        allUsers.add(new User("8", "이승헌", "tempURL"));
        allUsers.add(new User("9", "이요한", "tempURL"));
        allUsers.add(new User("10", "최종환", "tempURL"));
        allUsers.add(new User("11", "한상열", "tempURL"));


    }
}
