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

import java.util.List;

import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.datas.Reply;
import kr.co.tjeit.lecturemanager.datas.UserData;

/**
 * Created by the on 2017-08-31.
 */

public class ReplyAdapter extends ArrayAdapter<Reply> {
    Context mContext;
    List<Reply> mList;
    LayoutInflater inf;

    public ReplyAdapter(Context context, List<Reply> list) {
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

//        Reply data = mList.get(position);


        return row;
    }

    @Override
    public int getCount() {
        return 20;
    }
}
