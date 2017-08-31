package kr.co.tjeit.lecturemanager.util;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by the on 2017-08-31.
 */

public class GlobalData {

    public static List<User> students = new ArrayList<>();

    public static void initGlobalData() {
        students.clear();
        students.add(new User("1", "고동윤", "tempURL"));
        students.add(new User("2", "권성민", "tempURL"));
        students.add(new User("3", "김현철", "tempURL"));
        students.add(new User("4", "박석영", "tempURL"));
        students.add(new User("5", "박수현", "tempURL"));
        students.add(new User("6", "박영주", "tempURL"));
        students.add(new User("7", "손익상", "tempURL"));
        students.add(new User("8", "이승헌", "tempURL"));
        students.add(new User("9", "이요한", "tempURL"));
        students.add(new User("10", "한상열", "tempURL"));
        students.add(new User("11", "최종환", "tempURL"));

    }

}
