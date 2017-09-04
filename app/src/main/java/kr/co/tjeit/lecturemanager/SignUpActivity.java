package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.utils.ServerUtil;

public class SignUpActivity extends BaseActivity {

    private Button signUpBtn;
    private android.widget.EditText idEdt;
    private Button checkDupleBtn;
    private boolean isIdupl = true;
    private EditText nameEdt;
    private android.widget.TextView pwEdt;
    private EditText phoneEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bindViews();
        setValues();
        setUpEvents();
    }

    @Override
    public void setUpEvents() {

        checkDupleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ServerUtil.check_dupl_id(mContext, idEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        // 응답에 포함된 json을 분석
                        // json 내부에서 중복여부 파악
                        // 파악 여부에 따라 중복확인 후처리 작업 진행
                        // JSON을 파싱해서 isdupl처리를 하면 된다.
                        try {
                            isIdupl = json.getBoolean("result");

                            if (isIdupl) {
                                // 아이디가 중복된 상황
                                // 아이디가 중복되면 중복확인(제목), 이미 사용중인 아이디입니다.(메시지)
                                // 확인 버튼만 있는 경고창
                                AlertDialog.Builder duplAlert = new AlertDialog.Builder(mContext);
                                duplAlert.setTitle("중복확인")
                                        .setMessage("이미 사용중인 아이디입니다.")
                                        .setPositiveButton("확인", null).show();
                            } else {
                                // 아이디가 중복되지 않는 상황

                                // 아이디가 중복되지 않으면 사용해도 좋은 아이디입니다. Toast
                                Toast.makeText(mContext, "사용해도 좋은 아이디입니다.", Toast.LENGTH_SHORT).show();
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
                // 현재 코드는 무조건 학생 목록으로 넘어감.

                // 1. 중복확인을 통과 해야함
                // 2. 입력칸 중에 빈 칸이 없어야.
                // ==> 해당 칸이 비어있으면 경고창으로 알려주기.
                // 3. 중복확인을 통과한 후 , 아이디를 수정했다면 다시 중복확인을 받아야하도록
                // 4. 서버에 실제로 가입 요청
                // 5. 가입요청의 응답을 보고 가입승인이 났으면 로그인 처리


                AlertDialog.Builder checkEmpty = new AlertDialog.Builder(mContext);
                checkEmpty.setTitle("회원가입 실패");

                if(idEdt.getText().toString().equals("")){
                    checkEmpty.setMessage("아이디를 입력해주세요.").show();
                    return;
                }
                else if (nameEdt.getText().toString().equals("")) {
                    checkEmpty.setMessage("이름을 입력해주세요.").show();
                    return;
                }
                else if (pwEdt.getText().toString().equals("")) {
                    checkEmpty.setMessage("비밀번호를 입력해주세요.").show();
                    return;
                }
                else if (phoneEdt.getText().toString().equals("")) {
                    checkEmpty.setMessage("전화번호를 입력해주세요.").show();
                    return;
                }
                if (isIdupl) {
                    Toast.makeText(mContext, "중복확인을 해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(mContext, "회원가입 완료", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(mContext, StudentListActivity.class);
                startActivity(myIntent);
                finishAffinity();
            }
        });

        idEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isIdupl = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.phoneEdt = (EditText) findViewById(R.id.phoneEdt);
        this.pwEdt = (TextView) findViewById(R.id.pwEdt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
        this.checkDupleBtn = (Button) findViewById(R.id.checkDupleBtn);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
    }
}


