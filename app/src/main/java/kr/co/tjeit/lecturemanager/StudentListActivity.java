package kr.co.tjeit.lecturemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.login.LoginManager;
import com.kakao.usermgmt.UserManagement;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.adapter.StudentAdapter;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.GlobalData;

public class StudentListActivity extends BaseActivity {


    private ListView studentListView;
    private android.widget.Button logoutBtn;
    private Button myProfileBtn;

    List<User> mStudentList = new ArrayList<>();
    StudentAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        bindViews();
        setValues();
        setupEvents();

    }

    @Override
    public void setupEvents() {
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myIntent = new Intent(mContext, ViewStudentInfoActivity.class);
                myIntent.putExtra("사용자정보", GlobalData.students.get(position));

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

                        mStudentList.remove(position);
                        mAdapter.notifyDataSetChanged();

                    }
                });
                myBuilder.setNegativeButton("취소", null);
                myBuilder.show();

                return false;
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtil.logout(mContext);

                LoginManager.getInstance().logOut();

                UserManagement.requestLogout(null);

                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        myProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
        this.studentListView = (ListView) findViewById(R.id.studentListView);
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);


        mAdapter = new StudentAdapter(mContext, GlobalData.students);
        studentListView.setAdapter(mAdapter);


    }

    @Override
    public void bindViews() {
        this.studentListView = (ListView) findViewById(R.id.studentListView);
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);
        this.myProfileBtn = (Button) findViewById(R.id.myProfileBtn);
    }

}


