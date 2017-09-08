package kr.co.tjeit.lecturemanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.data.Message;
import kr.co.tjeit.lecturemanager.util.ContextUtil;

/**
 * Created by tjoeun on 2017-09-08.
 */

public class CheckMessageAdapter extends ArrayAdapter<Message> {
    Context mContext;
    List<Message> mList;
    LayoutInflater inf;

    public CheckMessageAdapter(Context context, List<Message> list){
        super(context, R.layout.message_list_item,list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row = inf.inflate(R.layout.message_list_item,null);
        }
        Message data = mList.get(position);
        TextView sendUserNameTxt = (TextView)row.findViewById(R.id.sendUserNameTxt);
        TextView contentTxt = (TextView)row.findViewById(R.id.contentTxt);
        CircleImageView profileImg =  (CircleImageView)row.findViewById(R.id.profileImg);
        TextView createdAtTxt = (TextView)row.findViewById(R.id.createdAtTxt);

        sendUserNameTxt.setText(data.getWriter().getName());
        contentTxt.setText(data.getContent());
        Glide.with(mContext).load(data.getWriter().getProfileImgPath()).into(profileImg);
        SimpleDateFormat sdf = new SimpleDateFormat("M월d일 h시m분", Locale.KOREAN);
        createdAtTxt.setText(sdf.format(data.getCreatedAt().getTime()));





        return row;
    }
}
