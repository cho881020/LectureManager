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

import kr.co.tjeit.lecturemanager.adapters.StudentAdapter;
import kr.co.tjeit.lecturemanager.utils.GloblaData;
import kr.co.tjeit.lecturemanager.datas.UserData;
import kr.co.tjeit.lecturemanager.utils.ContextUtil;
import kr.co.tjeit.lecturemanager.utils.ServerUtil;

public class StudentListActivity extends BaseActivity {

//    String[] students = {"고동윤", "권성민", "김현철", "박석영",
//            "박수현", "박영주", "손익상", "이승헌", "이요한", "한상열"};
    private UserData tempUser = null;

    private ListView studentListView;
    private StudentAdapter mStudentAdapter;
    private android.widget.Button profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        bindViews();
        setValues();
        setUpEvents();

    }

    @Override
    public void setUpEvents() {
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(StudentListActivity.this, position+"번 줄", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(mContext, ViewStudentInfoActivity.class);
                myIntent.putExtra("student", GloblaData.allUsers.get(position));

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

                        GloblaData.allUsers.remove(position);
                        mStudentAdapter.notifyDataSetChanged();

                    }
                });
                myBuilder.setNegativeButton("취소", null);
                myBuilder.show();

                return false;
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
        tempUser = ContextUtil.getLoginUser(mContext);
        mStudentAdapter = new StudentAdapter(mContext, GloblaData.allUsers);

        // 서버에서 수강생 정보 받아와서 뿌려주기 (GlobalData 활용)
        ServerUtil.get_all_users(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    JSONArray users = json.getJSONArray("users");

                    for (int i = 0; i < users.length(); i++) {
                        JSONObject user = users.getJSONObject(i);
                        UserData tempUser = UserData.getUserDataFromJsonObject(user);
                        GloblaData.allUsers.add(tempUser);
                    }

                    mStudentAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        studentListView.setAdapter(mStudentAdapter);
        mStudentAdapter.notifyDataSetChanged();
    }

    @Override
    public void bindViews() {
        this.studentListView = (ListView) findViewById(R.id.studentListView);
        this.profileBtn = (Button) findViewById(R.id.profileBtn);
    }
}


