package kr.co.tjeit.lecturemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.adapter.StudentInfoAdapter;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.GlobalData;
import kr.co.tjeit.lecturemanager.util.ServerUtil;


// 앙 기모띠~~~~~~~~

public class StudentListActivity extends BaseActivity {

    String[] students = {"고동윤", "권성민", "김현철", "박석영",
            "박수현", "박영주", "손익상", "이승헌", "이요한", "한상열"};

    //    ArrayList<String> myStudentsArrayList;
    StudentInfoAdapter madapter;

    private ListView studentListView;
    //    private ArrayAdapter<String> studentAdapter;
    private android.widget.Button profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        bindViews();
        setupEvents();
        setValues();
        GlobalData.initData();


    }


    @Override
    public void setupEvents() {
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(StudentListActivity.this, position+"번 줄", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(StudentListActivity.this, ViewStudentInfoActivity.class);
                myIntent.putExtra("studentName", GlobalData.allUsers.get(position));

                startActivity(myIntent);

            }
        });

        studentListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

//                Toast.makeText(StudentListActivity.this, position + "번 학생 삭제 요청", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(StudentListActivity.this);
                myBuilder.setTitle("삭제 확인");
                myBuilder.setMessage("정말 삭제하시겠습니까?");
                myBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        GlobalData.allUsers.remove(position);
                        madapter.notifyDataSetChanged();

                    }
                });
                myBuilder.setNegativeButton("취소", null);
                myBuilder.show();

                return false;
            }
        });

    }

    @Override
    public void setValues() {

        ServerUtil.get_all_users(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                try {
                    JSONArray users = json.getJSONArray("users");

                    for (int i = 0; i < users.length(); i++) {
                        JSONObject user = users.getJSONObject(i);

                        User tempUser = new User();
                        tempUser.setUserId(user.getString("user_id"));
                        tempUser.setUserName(user.getString("name"));
                        tempUser.setProfileUrl(user.getString("profile_photo"));
                        tempUser.setPhoneNum(user.getString("phone_num"));

                        GlobalData.allUsers.add(tempUser);
                    }

                    madapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

//        myStudentsArrayList = new ArrayList<String>();
//        myStudentsArrayList.add("고동윤");
//        myStudentsArrayList.add("권성민");
//        myStudentsArrayList.add("김현철");
//        myStudentsArrayList.add("박석영");
//        myStudentsArrayList.add("박수현");
//        myStudentsArrayList.add("박영주");
//        myStudentsArrayList.add("손익상");
//        myStudentsArrayList.add("이승헌");
//        myStudentsArrayList.add("이요한");
//        myStudentsArrayList.add("한상열");
//
//        studentAdapter = new ArrayAdapter<String>(StudentListActivity.this, android.R.layout.simple_list_item_1, myStudentsArrayList);
//        studentListView.setAdapter(studentAdapter);


        madapter = new StudentInfoAdapter(mContext, GlobalData.allUsers);
        studentListView.setAdapter(madapter);
    }

    @Override
    public void bindViews() {
        this.studentListView = (ListView) findViewById(R.id.studentListView);
        this.profileBtn = (Button) findViewById(R.id.profileBtn);

    }
}


