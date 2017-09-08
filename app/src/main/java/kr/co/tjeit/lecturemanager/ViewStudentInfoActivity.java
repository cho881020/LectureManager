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
    private TextView userIdTxt;

    User mUser = null;
    private TextView phoneTxt;
    private Button sendMessageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_info);
        mUser = (User) getIntent().getSerializableExtra("사용자정보");
        bindViews();
        setupEvents();
        setValues();

//        1. 사용자 정보를 보러 들어오면 핸드폰 번호가 나타나도록.
//        2. 전화걸기를 누르면 해당 사용자의 전화번호로 걸 수 있도록.
    }

    @Override
    public void setupEvents() {

        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SendMessageActivity.class);
                intent.putExtra("student", mUser);
                startActivity(intent);
            }
        });

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

        userIdTxt.setText(mUser.getUserId());
        studentNameTxt.setText(mUser.getName());
        phoneTxt.setText(mUser.getPhoneNum());

    }

    @Override
    public void bindViews() {
        this.sendMessageBtn = (Button) findViewById(R.id.sendMessageBtn);
        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.phoneTxt = (TextView) findViewById(R.id.phoneTxt);
        this.userIdTxt = (TextView) findViewById(R.id.userIdTxt);
        this.studentNameTxt = (TextView) findViewById(R.id.studentNameTxt);

    }
}
