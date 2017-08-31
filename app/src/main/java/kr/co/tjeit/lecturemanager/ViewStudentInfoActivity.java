package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewStudentInfoActivity extends BaseActivity {

    private TextView studentNameTxt;
    private Button callBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_info);
        studentNameTxt = (TextView) findViewById(R.id.studentNameTxt);
        callBtn = (Button) findViewById(R.id.callBtn);

        String stdName = getIntent().getStringExtra("studentName");
        studentNameTxt.setText(stdName);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri myUri = Uri.parse("tel:010-5112-3237");
                Intent intent = new Intent(Intent.ACTION_CALL, myUri);
                startActivity(intent);

            }
        });

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

    }
}
