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
    User user;
    private TextView idTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_info);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
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
        user = (User) getIntent().getSerializableExtra("studentName");
        studentNameTxt.setText(user.getName());
        idTxt.setText(user.getId());
    }

    @Override
    public void bindViews() {
        this.idTxt = (TextView) findViewById(R.id.idTxt);
        studentNameTxt = (TextView) findViewById(R.id.studentNameTxt);
        callBtn = (Button) findViewById(R.id.callBtn);
    }
}
