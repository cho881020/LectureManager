package kr.co.tjeit.lecturemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.adapter.StudentAdapter;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.utill.GlobalData;
import kr.co.tjeit.lecturemanager.utill.ServerUtil;

public class StudentListActivity extends BaseActivity {

    private ListView studentListView;
    private Button myProfileBtn;
    StudentAdapter mAdapter;
    private Button studentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        bindViews();
        setupEvents();
        setValues();

    }


    @Override
    public void setupEvents() {


        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StudentListActivity.class);
                startActivity(intent);
            }
        });

//        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(mContext, ViewStudentInfoActivity.class);
//                startActivity(intent);
//            }
//        });

        myProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyProfileActivity.class);
                startActivity(intent);
            }
        });

        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(StudentListActivity.this, position+"번 줄", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(mContext, MyProfileActivity.class);
                myIntent.putExtra("사용자정보", GlobalData.allUsers.get(position));
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
                        mAdapter.notifyDataSetChanged();

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

        mAdapter = new StudentAdapter(mContext, GlobalData.allUsers);
        studentListView.setAdapter(mAdapter);

        ServerUtil.get_all_users(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                try {
                    JSONArray users = json.getJSONArray("users");

                    for (int i = 0; i < users.length(); i++){
                        JSONObject user = users.getJSONObject(i);

                        User tempUser = User.getUserFromJsonObject(user);

                        GlobalData.allUsers.add(tempUser);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void bindViews() {
        this.studentListView = (ListView) findViewById(R.id.studentListView);
        this.myProfileBtn = (Button) findViewById(R.id.myProfileBtn);
        this.studentBtn = (Button) findViewById(R.id.studentBtn);

    }
}


