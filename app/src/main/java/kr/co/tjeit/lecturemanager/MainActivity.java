package kr.co.tjeit.lecturemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.CallbackManager;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    String[] students = {"고동윤", "권성민", "김현철", "박석영",
            "박수현", "박영주", "손익상", "이승헌", "이요한", "한상열"};

    ArrayList<String> myStudentsArrayList;

    private ListView studentListView;
    private ArrayAdapter<String> studentAdapter;

    CallbackManager callbackManager;
    private android.widget.TextView profileTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
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

        profileTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
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

        studentAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                myStudentsArrayList);
        studentListView.setAdapter(studentAdapter);
    }

    @Override
    public void bindViews() {
        this.profileTxt = (TextView) findViewById(R.id.profileTxt);
        studentListView = (ListView) findViewById(R.id.studentListView);
    }
}


