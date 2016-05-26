package yinlei.com.mychat.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import yinlei.com.mychat.ChatApplication;


/**
 * Created by wuyin on 2016/5/26.
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ChatApplication)getApplication()).addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((ChatApplication)getApplication()).removeActivity(this);
    }
}
