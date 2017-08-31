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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_info);


        bindViews();
        setupEvents();
        setValues();


//        callBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Uri myUri = Uri.parse("tel:010-5112-3237");
//                Intent myIntent = new Intent(Intent.ACTION_CALL, myUri);
//                startActivity(myIntent);
//
//            }
//        });

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        selectUser = (User) getIntent().getSerializableExtra("studentInfo");
        studentNameTxt.setText(selectUser.getName());
        idTxt.setText(selectUser.getId());
    }

    @Override
    public void bindViews() {
        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.idTxt = (TextView) findViewById(R.id.idTxt);
        this.studentNameTxt = (TextView) findViewById(R.id.studentNameTxt);

    }
}
