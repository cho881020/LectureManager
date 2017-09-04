package kr.co.tjeit.lecturemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.GlobalData;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class SignUpActivity extends BaseActivity {

    private Button signUpBtn;
    private android.widget.EditText idEdt;
    private Button checkDuplBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        bindViews();
        setupEvents();
        setValues();


    }


    @Override
    public void setupEvents() {

        checkDuplBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                중복여부? => 서버에다가 물어보면 돌아오는 응답으로 체크.

//                버튼이 눌리면 실제로 서버에 중복 확인 요청
                ServerUtil.check_dupl_id(mContext, idEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
//                        응답에 포함된 json을 분석
//                        json 내부에서 중복 여부를 파악.
//                        파악된 중복 여부에 따라 중복확인 후처리 작업 진행.


//                        json 파싱해서 isIdDupl을 상황에 맞는 값으로 세팅.
                        boolean isIdDupl = false;
                        try {
                            isIdDupl = json.getBoolean("result");
                            if (isIdDupl) {
//                    아이디가 중복된 상황.
//                    아이디가 중복되었다면,
//                    중복 확인 (제목), 이미 사용중인 아이디 입니다. (메세지)
//                    확인 버튼만 있는 경고창 띄워주기.
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setTitle("중복 확인");
                                builder.setMessage("이미 사용중인 아이디 입니다.");
                                builder.setPositiveButton("확인", null);
                                builder.show();

                            } else {
//                    아이디가 중복되지 않는 상황.
                                Toast.makeText(mContext, "사용해도 좋은 아이디 입니다.", Toast.LENGTH_SHORT).show();
//                    아이디가 중복되지 않으면, 사용해도 좋은 아이디 입니다. Toast
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                });


            }
        });

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
        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.checkDuplBtn = (Button) findViewById(R.id.checkDuplBtn);
        this.idEdt = (EditText) findViewById(R.id.idEdt);

    }
}


