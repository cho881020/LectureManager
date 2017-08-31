package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.co.tjeit.lecturemanager.data.User;

public class ViewStudentInfoActivity extends BaseActivity {

    private TextView studentNameTxt;
    private Button callBtn;
    User mUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_info);
        mUser = (User) getIntent().getSerializableExtra("studentName");


    }

    @Override
    public void setupEvents() {
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri myUri = Uri.parse("tel:010-5112-3237");
                Intent intent = new Intent(Intent.ACTION_DIAL, myUri);
                startActivity(intent);

            }
        });
    }

    @Override
    public void setValues() {
        studentNameTxt.setText(mUser.getName()+"");
    }

    @Override
    public void bindViews() {
        studentNameTxt = (TextView) findViewById(R.id.studentNameTxt);
        callBtn = (Button) findViewById(R.id.callBtn);
    }
}
