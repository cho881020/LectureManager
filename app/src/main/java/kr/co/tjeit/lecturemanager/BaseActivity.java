package kr.co.tjeit.lecturemanager;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by the on 2017-08-31.
 */

public abstract class BaseActivity extends AppCompatActivity {

    Context mContext = this;

    public abstract void bindViews();
    public abstract void setupEvents();
    public abstract void setValues();

}
