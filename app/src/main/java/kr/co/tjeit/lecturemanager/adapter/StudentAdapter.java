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

import com.bumptech.glide.Glide;

import java.util.List;

import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by user on 2017-08-31.
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
        if (row == null ) {
            row = inf.inflate(R.layout.student_list_item, null);
        }

        User data = mList.get(position);

//        JAVA의 객체지향 3요소중 다형성.
//        CircleImageView는 ImageView의 자식.
//        부모는 자식을 담아둘 수 있음.
        ImageView profileImg = (ImageView) row.findViewById(R.id.profileImg);
        TextView nameTxt = (TextView) row.findViewById(R.id.nameTxt);

        Glide.with(mContext).load(data.getProfileURL()).into(profileImg);

        nameTxt.setText(data.getName());



        return row;
    }
}
