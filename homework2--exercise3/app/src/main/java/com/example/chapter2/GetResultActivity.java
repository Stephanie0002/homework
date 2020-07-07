package com.example.chapter2;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class GetResultActivity extends Activity {
    public static final String KEY = "result_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_result);
        initView();
    }

    private void initView() {
        TextView tv = findViewById(R.id.tv_g_0);
        // 获取意图对象
        Intent intent = getIntent();
        //获取传递的值
        String str = intent.getStringExtra("data");
        String str2 = intent.getStringExtra("data_2");
        //设置值
        if(str!=null)
            tv.setText("你点击了："+str);

        if(str2!=null)
            tv.setText("你长按了："+str2);
    }
}
