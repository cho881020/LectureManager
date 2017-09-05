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
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.util.ContextUtil;

/**
 * Created by the on 2017-08-31.
 */

public class StudentInfoAdapter extends ArrayAdapter<User> {

    Context mContext;
    List<User> mList;
    LayoutInflater inf;

    public StudentInfoAdapter(Context context, List<User> list) {
        super(context, R.layout.student_info_list, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.student_info_list, null);
        }

        User data = mList.get(position);

        CircleImageView profileImg = (CircleImageView) row.findViewById(R.id.profileImg);
        TextView nameTxt = (TextView) row.findViewById(R.id.nameTxt);

        Glide.with(mContext).load(data.getProfileUrl()).into(profileImg);
        nameTxt.setText(data.getUserName());






        return row;
    }
}
