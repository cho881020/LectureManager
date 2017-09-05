package kr.co.tjeit.lecturemanager.adapter;

import android.content.Context;
import android.net.Uri;
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
import kr.co.tjeit.lecturemanager.data.User;

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
        TextView contextTxt = (TextView) row.findViewById(R.id.contextTxt);

        nameTxt.setText(data.getWriter().getName());
        contextTxt.setText(data.getContext());

        ImageView profile_image = (ImageView) row.findViewById(R.id.profile_image);
        if (!data.getWriter().getProfileURL().equals("tempURL")) {
            Glide.with(mContext).load(Uri.parse(data.getWriter().getProfileURL())).into(profile_image);
        } else {
            profile_image.setImageResource(R.mipmap.ic_launcher);
        }

        return row;
    }

}
