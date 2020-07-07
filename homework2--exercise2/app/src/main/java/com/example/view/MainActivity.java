package com.example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                LinearLayout linearLayout = findViewById(R.id.lo_1);
                int number = count(linearLayout);
                TextView tv = findViewById(R.id.tv);
                tv.setText("该页面View数："+number);
                break;
            default:
                break;
        }
    }
    //采用深度遍历，递归求View个数，假设所有组件均为view
    public int count(View viewRoot) {
        int count = 0;
        if (viewRoot == null) { return 0; }//到空树叶返回
        //若该组件是属于ViewGroup的，计数
        if (viewRoot instanceof ViewGroup) {
            count++;//计当前组件
            //循环遍历每一个子树
            for (int i = 0; i < ((ViewGroup) viewRoot).getChildCount(); i++) {
                View v = ((ViewGroup) viewRoot).getChildAt(i);
                if (v instanceof ViewGroup) {
                    count += count(v);//递归，深度遍历
                } else {
                    count++;//若子树已经不是ViewGroup，那么一定是View，计数+1个
                }
            }
        }
        return count;
    }
}
