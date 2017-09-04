package kr.co.tjeit.lecturemanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.data.Reply;
import kr.co.tjeit.lecturemanager.data.User;

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

        TextView writerNameTxt = (TextView) row.findViewById(R.id.writerNameTxt);
        TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);

        writerNameTxt.setText(data.getWriter().getName());
        contentTxt.setText(data.getContent());


        return row;
    }

}
