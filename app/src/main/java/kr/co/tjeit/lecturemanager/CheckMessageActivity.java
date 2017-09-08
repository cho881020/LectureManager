package kr.co.tjeit.lecturemanager;

import android.os.Bundle;
import android.widget.ListView;

public class CheckMessageActivity extends BaseActivity {

    private android.widget.ListView messageListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_message);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

        this.messageListView = (ListView) findViewById(R.id.messageListView);
    }
}
