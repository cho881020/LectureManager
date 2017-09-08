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

import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.data.Message;
import kr.co.tjeit.lecturemanager.data.Reply;

/**
 * Created by the on 2017-09-08.
 */

public class MessageAdapter extends ArrayAdapter {

        Context mContext;
        List<Message> mList;
        LayoutInflater inf;

        public MessageAdapter(Context context, List<Message> list) {
            super(context, R.layout.message_list_item, list);

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
                row = inf.inflate(R.layout.message_list_item, null);
            }

            Message data = mList.get(position);

            ImageView profileImg = (ImageView) row.findViewById(R.id.profileImg);
            TextView writerNameTxt = (TextView) row.findViewById(R.id.writerNameTxt);
            TextView timeTxt = (TextView) row.findViewById(R.id.timeTxt);
            TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);

            Glide.with(mContext).load(data.getWriter().getProfileURL()).into(profileImg);

            contentTxt.setText(data.getContent());

            writerNameTxt.setText(data.getWriter().getName());
            SimpleDateFormat sdf = new SimpleDateFormat("M월d일 h시m분", Locale.KOREA);
            timeTxt.setText(sdf.format(data.getCreatedAt().getTime()));


            return row;
        }

    }



