package kr.co.tjeit.lecturemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.login.LoginManager;
import com.kakao.usermgmt.UserManagement;

import java.util.ArrayList;

import kr.co.tjeit.lecturemanager.util.ContextUtil;

public class MainActivity extends BaseActivity {



    String[] students = {"고동윤", "권성민", "김현철", "박석영",
            "박수현", "박영주", "손익상", "이승헌", "이요한", "한상열"};

    ArrayList<String> myStudentsArrayList;

    private ListView studentListView;
    private ArrayAdapter<String> studentAdapter;
    private android.widget.Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setValues();
        setupEvents();

        myStudentsArrayList = new ArrayList<String>();
        myStudentsArrayList.add("고동윤");
        myStudentsArrayList.add("권성민");
        myStudentsArrayList.add("김현철");
        myStudentsArrayList.add("박석영");
        myStudentsArrayList.add("박수현");
        myStudentsArrayList.add("박영주");
        myStudentsArrayList.add("손익상");
        myStudentsArrayList.add("이승헌");
        myStudentsArrayList.add("이요한");
        myStudentsArrayList.add("한상열");





    }

    @Override
    public void setupEvents() {
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, position+"번 줄", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(MainActivity.this, ViewStudentInfoActivity.class);
                myIntent.putExtra("studentName", myStudentsArrayList.get(position));

                startActivity(myIntent);

            }
        });

        studentListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

//                Toast.makeText(MainActivity.this, position + "번 학생 삭제 요청", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setTitle("삭제 확인");
                myBuilder.setMessage("정말 삭제하시겠습니까?");
                myBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        myStudentsArrayList.remove(position);
                        studentAdapter.notifyDataSetChanged();

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
    }

    @Override
    public void setValues() {
        studentAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                myStudentsArrayList);

        this.studentListView = (ListView) findViewById(R.id.studentListView);
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);

    }

    @Override
    public void bindViews() {
        studentListView = (ListView) findViewById(R.id.studentListView);
    }

}


