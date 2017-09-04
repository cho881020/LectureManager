package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class SignUpActivity extends BaseActivity {

    private Button signUpBtn;
    private android.widget.EditText idEdt;
    private Button checkDuppleBtn;

    boolean isIdDupl = true;
    boolean isIdDuplOk = false;

    private EditText nameEdt;
    private EditText pwEdt;
    private EditText bitrhEdt;
    private EditText phoneNumEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        checkDuppleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                중복 여부 > 서버에 요청하면 돌아오는 응답으로 체크
//                버튼이 눌리면 실제로 서버에 중복 확인 요청
                ServerUtil.check_dupl_id(mContext, idEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
//                        응답에 포함된 json을 분석
//                        json 내부에서 중복 여부를 파악
//                        파악된 중복 여부에 따라 중복확인 후처리 작업 진행
                        try {
                            isIdDupl = json.getBoolean("result");

                            if (isIdDupl) {
                                // 아이디가 중복된 상황
//                    중복 확인(제목) 이미 사용중인 아이디입니다.(메시지)
//                    확인 버튼만 있는 경고창
                                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                alert.setTitle("중복 확인");
                                alert.setMessage("이미 사용중인 아이디입니다.");
                                alert.setPositiveButton("확인", null);
                                alert.show();
                            } else {
                                // 중복 없는 상황
//                    사용해도 좋은 아이디입니다. Toast
                                Toast.makeText(mContext, "사용해도 좋은 아이디입니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

//       3. 중복확인을 통과한 이후에 아이디를 수정했다면 다시 중복확인을 받아야하도록
        idEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        타이핑이 되는 매 순간마다, 중복검사를 통과못한 값으로 변경
                isIdDupl = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                1, 중복확인을 통과해야함
                if (isIdDupl) {
                    Toast.makeText(mContext, "아이디 중복확인을 해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
//                2. 입력칸 중에 빈 칸이 없어야함
//                 => 위에서부터 하나하나 검사하다가 빈칸을 발견하면, 해당 칸이 비어있음을 경고창으로 알려주기
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("가입 실패");
                alert.setPositiveButton("확인", null);

                if (nameEdt.getText().toString().equals("")) {
                    alert.setMessage("아이디가 입력되지 않았습니다.");

                    alert.show();
                    return;
                } else if (pwEdt.getText().toString().equals("")) {
                    alert.setMessage("비밀번호가 입력되지 않았습니다.");
                    alert.show();
                    return;
                } else if (bitrhEdt.getText().toString().equals("")) {
                    alert.setMessage("생년월일이 입력되지 않았습니다.");
                    alert.show();
                    return;
                } else if (phoneNumEdt.getText().toString().equals("")) {
                    alert.setMessage("휴대폰 번호가 입력되지 않았습니다.");
                    alert.show();
                    return;
                } else {
//                4. 서버에 가입 요청
//                5. 가입요청의 응답을 보고 가입 승인이 나면 로그인 처리
//                6. 로그인 처리가 완료되면 학생 목록 화면으로 이동
//                 => 프로필 사진 경로x : tempURL이라고 프사 경로 지정
                    ServerUtil.sign_up(mContext, idEdt.getText().toString(), pwEdt.getText().toString(), nameEdt.getText().toString(),
                            "tmpURL", phoneNumEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                                @Override
                                public void onResponse(JSONObject json) {
                                    try {
                                        boolean isSignUpOk = json.getBoolean("result");

                                        if (isSignUpOk) {
                                            Toast.makeText(mContext, json.getString("message"), Toast.LENGTH_SHORT).show();
                                            Intent myIntent = new Intent(SignUpActivity.this, StudentListActivity.class);
                                            startActivity(myIntent);
                                            finish();
                                            LoginActivity.myActivity.finish();
                                        } else {
                                            Toast.makeText(mContext, json.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.phoneNumEdt = (EditText) findViewById(R.id.phoneNumEdt);
        this.bitrhEdt = (EditText) findViewById(R.id.bitrhEdt);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
        this.checkDuppleBtn = (Button) findViewById(R.id.checkDuppleBtn);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
    }
}


