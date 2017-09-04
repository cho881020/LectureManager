package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.utill.ContextUtill;

public class ViewStudentInfoActivity extends BaseActivity {

    private TextView studentNameTxt;
    private Button callBtn;
    private TextView studentIdTxt;

    User mUser = null;
    private TextView phoneNumTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_info);
        mUser = (User) getIntent().getSerializableExtra("사용자정보");
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
                Uri myUri = Uri.parse("tel:" + mUser.getPhoneNum());
                Intent myIntent = new Intent(Intent.ACTION_DIAL, myUri);
                startActivity(myIntent);

            }
        });


    }

    @Override
    public void setValues() {
        studentIdTxt.setText(mUser.getUserId());
        studentNameTxt.setText(mUser.getName());
        phoneNumTxt.setText(mUser.getPhoneNum());

    }

    @Override
    public void bindViews() {
        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.phoneNumTxt = (TextView) findViewById(R.id.phoneNumTxt);
        this.studentIdTxt = (TextView) findViewById(R.id.studentIdTxt);
        this.studentNameTxt = (TextView) findViewById(R.id.studentNameTxt);
    }
}
