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

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        super(context, R.layout.reply_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if(row == null) {
            row = inf.inflate(R.layout.reply_list_item, null);
        }

        Reply data = mList.get(position);

        ImageView profileImg = (ImageView) row.findViewById(R.id.profileImg);
        TextView userNameTxt = (TextView) row.findViewById(R.id.userNameTxt);
        TextView replyTimeTxt = (TextView) row.findViewById(R.id.replyTimeTxt);
        TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);

        userNameTxt.setText(data.getWriter().getUserName());
        SimpleDateFormat myDataFormat = new SimpleDateFormat("mm:ss a");

        replyTimeTxt.setText(myDataFormat.format(Calendar.getInstance().getTime()));
        contentTxt.setText(data.getContent());




        return row;
    }
}
