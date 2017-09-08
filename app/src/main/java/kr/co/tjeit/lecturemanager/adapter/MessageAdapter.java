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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.data.Message;
import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by the on 2017-09-08.
 */

public class MessageAdapter extends ArrayAdapter<Message> {

    Context mContext;
    List<Message> mList;
    LayoutInflater inf;

    public MessageAdapter(Context context, List<Message> list) {
        super(context, R.layout.message_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null ) {
            row = inf.inflate(R.layout.message_list_item, null);
        }

        Message data = mList.get(position);

        CircleImageView profileImg = (CircleImageView) row.findViewById(R.id.profileImg);
        TextView writerNameTxt = (TextView) row.findViewById(R.id.writerNameTxt);
        TextView messageTimeTxt = (TextView) row.findViewById(R.id.messageTimeTxt);
        TextView messageContentTxt = (TextView) row.findViewById(R.id.messageContentTxt);


        Glide.with(mContext).load(data.getWriter().getProfileURL()).into(profileImg);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M월d일 h시 m분", Locale.KOREA);

        writerNameTxt.setText(data.getWriter().getName());
        messageTimeTxt.setText(simpleDateFormat.format(data.getCreatedAt().getTime()));
        messageContentTxt.setText(data.getContent());

        return row;
    }
}
