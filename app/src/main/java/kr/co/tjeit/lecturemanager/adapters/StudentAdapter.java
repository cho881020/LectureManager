package kr.co.tjeit.lecturemanager.adapters;

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

import org.w3c.dom.Text;

import java.util.List;

import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.datas.UserData;

/**
 * Created by the on 2017-08-31.
 */

public class StudentAdapter extends ArrayAdapter<UserData> {

    Context mContext;
    List<UserData> mList;
    LayoutInflater inf;

    public StudentAdapter(Context context, List<UserData> list) {
        super(context, R.layout.student_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if(row == null) {
            row = inf.inflate(R.layout.student_list_item, null);
        }

        UserData data = mList.get(position);

        ImageView profileImg = (ImageView) row.findViewById(R.id.profileImg);
        TextView studentNameTxt = (TextView) row.findViewById(R.id.studentNameTxt);

//        Glide.with(mContext).load(data.getUserProfilImg()).into(profileImg);
        studentNameTxt.setText(data.getUserName());


        return row;
    }
}
