package com.example.chapter2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter2.recycler.LinearItemDecoration;
import com.example.chapter2.recycler.MyAdapter;
import com.example.chapter2.recycler.TestData;
import com.example.chapter2.recycler.TestDataSet;

public class RecyclerViewActivity extends AppCompatActivity implements MyAdapter.IOnItemClickListener {

    private static final String TAG = "TAG";

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Log.i(TAG, "RecyclerViewActivity onCreate");
        initView();

        findViewById(R.id.imv_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建意图对象
                Intent intent = new Intent(RecyclerViewActivity.this, GetResultActivity.class);
                //设置传递键值对
                intent.putExtra("data","消息");
                //激活意图
                startActivity(intent);
            }
        });
        findViewById(R.id.imv_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建意图对象
                Intent intent = new Intent(RecyclerViewActivity.this, GetResultActivity.class);
                //设置传递键值对
                intent.putExtra("data","赞");
                //激活意图
                startActivity(intent);
            }
        });
        findViewById(R.id.imv_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建意图对象
                Intent intent = new Intent(RecyclerViewActivity.this, GetResultActivity.class);
                //设置传递键值对
                intent.putExtra("data","@我的");
                //激活意图
                startActivity(intent);
            }
        });
        findViewById(R.id.imv_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建意图对象
                Intent intent = new Intent(RecyclerViewActivity.this, GetResultActivity.class);
                //设置传递键值对
                intent.putExtra("data","评论");
                //激活意图
                startActivity(intent);
            }
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(TestDataSet.getData());
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLUE);
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        DefaultItemAnimator animator = new DefaultItemAnimator();
//        animator.setAddDuration(3000);
//        recyclerView.setItemAnimator(animator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "RecyclerViewActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "RecyclerViewActivity onResume");
    }


    @Override
    public void onItemCLick(int position, TestData data) {
        //创建意图对象
        Intent intent = new Intent(this, GetResultActivity.class);
        //设置传递键值对
        String str = position+". "+data.toString();
        intent.putExtra("data",str);
        //激活意图
        startActivity(intent);

       // Toast.makeText(RecyclerViewActivity.this, "点击了第" + position + "条", Toast.LENGTH_LONG).show();
       // mAdapter.addData(position + 1, new TestData("新增头条", ""));
    }

    @Override
    public void onItemLongCLick(int position, TestData data) {
        //创建意图对象
        Intent intent = new Intent(this, GetResultActivity.class);
        //设置传递键值对
        String str = position+". "+data.toString();
        intent.putExtra("data_2",str);
        //激活意图
        startActivity(intent);

       // Toast.makeText(RecyclerViewActivity.this, "长按了第" + position + "条", Toast.LENGTH_LONG).show();
       // mAdapter.removeData(position);
    }
}