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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_info);
        mUser = (User) getIntent().getSerializableExtra("사용자정보");
        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri myUri = Uri.parse("tel:010-5112-3237");
                Intent myIntent = new Intent(Intent.ACTION_DIAL, myUri);
                startActivity(myIntent);

            }
        });


    }

    @Override
    public void setValues() {
        studentIdTxt.setText(mUser.getUserId());
        studentNameTxt.setText(mUser.getName());

    }

    @Override
    public void bindViews() {
        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.studentIdTxt = (TextView) findViewById(R.id.studentIdTxt);
        this.studentNameTxt = (TextView) findViewById(R.id.studentNameTxt);
    }
}
