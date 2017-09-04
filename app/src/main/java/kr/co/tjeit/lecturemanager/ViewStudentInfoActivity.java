package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.co.tjeit.lecturemanager.data.User;

public class ViewStudentInfoActivity extends BaseActivity {

    private TextView studentNameTxt;
    private Button callBtn;
    private User selectUser;
    private TextView idTxt;
    private TextView phoneTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_info);



        bindViews();
        setupEvents();
        setValues();


        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri myUri = Uri.parse("tel:"+phoneTxt.getText().toString());
                Intent myIntent = new Intent(Intent.ACTION_DIAL, myUri);
                startActivity(myIntent);

            }
        });
//        1. 사용자 정보를 보러 들어오면 핸드폰 번호가 나타나도록.
//        2. 전화걸기를 누르면 해당 사용자의 전화번호로 걸 수 있도록.
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        selectUser = (User) getIntent().getSerializableExtra("studentInfo");
        studentNameTxt.setText(selectUser.getName());
        idTxt.setText(selectUser.getUserId());
        phoneTxt.setText(selectUser.getPhoneNum());
    }

    @Override
    public void bindViews() {
        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.phoneTxt = (TextView) findViewById(R.id.phoneTxt);
        this.idTxt = (TextView) findViewById(R.id.idTxt);
        this.studentNameTxt = (TextView) findViewById(R.id.studentNameTxt);

    }
}
