package kr.co.tjeit.lecturemanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.utill.ContextUtill;

/**
 * Created by the on 2017-08-31.
 */

public class StudentAdapter extends ArrayAdapter<User> {

    Context mContext;
    List<User> mList;
    LayoutInflater inf;

    public StudentAdapter(Context context, List<User> list) {
        super(context, R.layout.student_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.student_list_item, null);
        }

        User data = mList.get(position);

//        JAVA의 객체지향 3요소중 다형성
//        CircleImageView는 ImageView의 자식
//        부모는 자식을 담아둘 수 있음
        ImageView profileImg = (ImageView) row.findViewById(R.id.profile_image);
        TextView nameTxt = (TextView) row.findViewById(R.id.nameTxt);

//        TODO - 회원가입 기능 완료 후에 이미지 표시 작업 필요요

        nameTxt.setText(data.getName());


        return row;
    }

}
