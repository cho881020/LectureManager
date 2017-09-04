package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class ViewStudentInfoActivity extends BaseActivity {

    User student;

    private TextView studentNameTxt;
    private Button callBtn;
    private TextView idTxt;
    private TextView phoneTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_info);
        bindViews();
        setupEvents();
        setValues();

//        1. 사용자 정보를 보러 들어오면 핸드폰 번호가 나타나도록
//        2. 전화걸기를 누르면 해당 사용자의 전화번호로 걸 수 있도록

    }

    @Override
    public void setupEvents() {


        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri myUri = Uri.parse("tel:" +student.getPhoneNum());
                Intent myIntent = new Intent(Intent.ACTION_DIAL, myUri);
                startActivity(myIntent);

            }
        });


    }

    @Override
    public void setValues() {

        student = (User) getIntent().getSerializableExtra("studentName");
        studentNameTxt.setText(student.getUserName());
        idTxt.setText(student.getUserId());
        phoneTxt.setText(student.getPhoneNum());

    }

    @Override
    public void bindViews() {
        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.phoneTxt = (TextView) findViewById(R.id.phoneTxt);
        this.idTxt = (TextView) findViewById(R.id.idTxt);
        this.studentNameTxt = (TextView) findViewById(R.id.studentNameTxt);
    }
}
