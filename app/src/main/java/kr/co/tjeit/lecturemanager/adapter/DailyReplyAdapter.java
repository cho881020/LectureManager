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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.data.Reply;
import kr.co.tjeit.lecturemanager.data.User;
import kr.co.tjeit.lecturemanager.utill.ContextUtill;

/**
 * Created by the on 2017-08-31.
 */

public class DailyReplyAdapter extends ArrayAdapter<Reply> {

    Context mContext;
    List<Reply> mList;
    LayoutInflater inf;

    public DailyReplyAdapter(Context context, List<Reply> list) {
        super(context, R.layout.reply_list_item, list);

        mContext = context;
        mList = list;
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

        TextView nameTxt = (TextView) row.findViewById(R.id.nameTxt);
        TextView timeTxt = (TextView) row.findViewById(R.id.timeTxt);
        TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);

        nameTxt.setText(data.getWriter().getName());
        contentTxt.setText(data.getContent());

        return row;
    }

}



