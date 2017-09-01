package kr.co.tjeit.lecturemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    User me = null;

    private de.hdodenhof.circleimageview.CircleImageView profileImg;
    private android.widget.TextView nameTxt;
    private android.widget.Button linkBtn;
    private TextView genderTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {


//        페이스북의 Graph API 이용해서 더 많은 정보를 불러오기

    }

    @Override
    public void setValues() {

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(final JSONObject object, GraphResponse response) {
                        Log.d("사용자정보", object.toString());


                        linkBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                try {
                                    String link = object.getString("link");
                                    intent.setData(Uri.parse(link));
                                    startActivity(intent);

                                    String gender = object.getString("gender");
                                    if (gender.equals("male")) {
                                        genderTxt.setText("남성");
                                    } else {
                                        genderTxt.setText("여성");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });


                    }
                }
        );

        Bundle params = new Bundle();
        params.putString("fields", "id,name,gender,link,email");
        request.setParameters(params);
        request.executeAsync();


        me = ContextUtil.getLoginUser(mContext);
        nameTxt.setText(me.getUserName());
        Glide.with(mContext).load(me.getProfileUrl()).into(profileImg);


    }

    @Override
    public void bindViews() {
        this.linkBtn = (Button) findViewById(R.id.linkBtn);
        this.genderTxt = (TextView) findViewById(R.id.genderTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}
