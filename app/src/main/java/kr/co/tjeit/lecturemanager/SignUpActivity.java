package kr.co.tjeit.lecturemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.lecturemanager.util.ContextUtil;
import kr.co.tjeit.lecturemanager.util.ServerUtil;

public class SignUpActivity extends BaseActivity {

    private Button signUpBtn;
    private android.widget.EditText idEdt;
    private Button checkDuplBtn;
    private EditText nameEdt;
    private EditText pwEdt;
    private EditText pwCheckEdt;
    private EditText phoneNumEdt;
    private boolean idOk = false;
    private boolean nameOk = false;
    private boolean pwOk = false;
    private boolean hpOk = false;
    boolean isIdDupl = false;

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

//                버튼이 눌리면, 실제로 서버에 중복 확인 요청
                ServerUtil.check_dupl_id(mContext, idEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
//                        응답에 포함된 json을 분석, json 내부에서 중복 여부를 파악
//                        파악된 중복 여부에 따라, 중복확인 후처리 작업 진행.
//                        JSON을 파싱해서, isidDupl을 상황에 맞는 값으로 세팅.

                        try {
                            isIdDupl = json.getBoolean("result");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (isIdDupl) {
//                    아이디가 중복된 상황
//                    중복확인 (제목) 이미 사용중인 아이디입니다. (메시지)
//                    확인버튼만 있는 경고창 띄워주기
                            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                            alert.setTitle("중복 확인");
                            alert.setMessage("이미 사용중인 아이디입니다.");
                            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alert.show();
                        } else {
//                    아이디가 중복되지 않는 상황
                            Toast.makeText(mContext, "사용 가능합니다.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
//                중복 여부? => 서버에 물어보면 돌아오는 응답으로 체크.
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
//            현재는 누르면 무조건 학생 목록으로 넘어감
//            1. 중복확인을 통과해야함
//            2. 입력칸 중에 빈칸이 있으면 안됨.
//            => 검사하다가 빈칸 발견시 해당 칸이 비어있다고 경고창 띄우기
//            3. 중복확인을 통과한 이후 아이디를 수정했다면 다시 확인
//            4. 서버에 실제로 가입 요청
//            5. 가입요청의 응답을 보고, 승인이 나면 로그인 처리
//            6. 로그인처리가 완료되면 학생 목록 화면으로 이동

            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("경고");
                alert.setPositiveButton("확인", null);

                if (isIdDupl) {
                    Toast.makeText(mContext, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (idEdt.getText().toString().equals("")) {
                    alert.setMessage("아이디를 입력해 주세요");
                    alert.show();
                    return;
                } else {
                    idOk = true;
                }

                if (idEdt.getText().toString().equals("")) {
                    alert.setMessage("이름을 입력해 주세요");
                    alert.show();
                    return;
                } else {
                    nameOk = true;
                }


                idEdt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        isIdDupl = true;
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                String pw = pwEdt.getText().toString();
                String pwCheck = pwCheckEdt.getText().toString();

                if (pw.length() == 0) {
                    alert.setMessage("비밀번호를 입력해 주세요");
                    alert.show();
                    return;
                } else if (pw.length() < 6) {
                    alert.setMessage("비밀번호가 너무 짧습니다.");
                    alert.show();
                    return;
                } else if (!pw.equals(pwCheck)) {
                    alert.setMessage("입력하신 비밀번호가 다릅니다.");
                    alert.show();
                    return;
                } else {
                    pwOk = true;
                }

                if (phoneNumEdt.getText().toString().equals("")) {
                    alert.setMessage("휴대폰 번호를 입력해 주세요.");
                    alert.show();
                    return;
                } else {
                    hpOk = true;
                }

                ServerUtil.sign_up(mContext, idEdt.getText().toString(), pwEdt.getText().toString(), nameEdt.getText().toString(), "tempURL", phoneNumEdt.getText().toString(),
                        new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                try {
                                    if (json.getBoolean("result")) {
                                        Toast.makeText(mContext, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
//                                ContextUtil.login();
                                        if (idOk && nameOk && pwOk && hpOk) {
                                            Intent myIntent = new Intent(mContext, StudentListActivity.class);
                                            startActivity(myIntent);
                                            finish();
                                        }
                                    } else {
                                        Toast.makeText(mContext, "회원가입에 실패했습니다. 아이디 변경 후 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

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
        this.pwCheckEdt = (EditText) findViewById(R.id.pwCheckEdt);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
        this.checkDuplBtn = (Button) findViewById(R.id.checkDuplBtn);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
    }
}


