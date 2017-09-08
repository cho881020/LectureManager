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
import kr.co.tjeit.lecturemanager.data.Reply;

/**
 * Created by user on 2017-08-31.
 */

public class ReplyAdapter extends ArrayAdapter<Reply> {

    Context mContext;
    List<Reply> mList;
    LayoutInflater inf;

    public ReplyAdapter(Context context, List<Reply> list) {
        super(context, R.layout.reply_list_item, list);

        mContext = context;
        mList = list;

//        LayoutInflater 만들때는 LayoutInflater.from => 재료로 컨텍스트
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.reply_list_item, null);
        }

        Reply data = mList.get(position);

        ImageView profileImg = (ImageView) row.findViewById(R.id.profileImg);
        TextView writerNameTxt = (TextView) row.findViewById(R.id.writerNameTxt);
        TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);

        Glide.with(mContext).load(data.getWriter().getProfileURL()).into(profileImg);
        writerNameTxt.setText(data.getWriter().getName());
        contentTxt.setText(data.getContent());



        return row;
    }

}
