package kr.co.tjeit.lecturemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import kr.co.tjeit.lecturemanager.adapter.StudentAdapter;
import kr.co.tjeit.lecturemanager.util.GlobalData;

// 저만의 브런치 입니다. (손익상)



public class StudentListActivity extends BaseActivity {


    private ListView studentListView;
    private StudentAdapter mAdapter;
    private android.widget.Button profileViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        GlobalData.initUserData();
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        profileViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MyProfileActivity.class);
                startActivity(intent);
            }
        });



        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(StudentListActivity.this, position+"번 줄", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(StudentListActivity.this, ViewStudentInfoActivity.class);
                myIntent.putExtra("studentInfo", GlobalData.allUser.get(position));

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

                        GlobalData.allUser.remove(position);
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
        mAdapter = new StudentAdapter(mContext, GlobalData.allUser);
        studentListView.setAdapter(mAdapter);
    }

    @Override
    public void bindViews() {
        studentListView = (ListView) findViewById(R.id.studentListView);
        this.profileViewBtn = (Button) findViewById(R.id.profileViewBtn);

    }
}


