package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends BaseActivity {

    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


    }



    @Override
    public void setupEvents() {

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUpActivity.this, StudentListActivity.class);
                startActivity(myIntent);
                finish();
                LoginActivity.myActivity.finish();
            }
        });

    }

    @Override
    public void setValues() {

    }
    @Override
    public void bindViews() {
        signUpBtn = (Button) findViewById(R.id.signUpBtn);

    }
}


