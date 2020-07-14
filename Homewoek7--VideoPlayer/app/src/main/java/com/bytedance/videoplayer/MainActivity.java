package com.bytedance.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBtn();

        //ImageView imageView = findViewById(R.id.imageView);
        //String url = "https://s3.pstatp.com/toutiao/static/img/logo.271e845.png";
        //Glide.with(this).load(url).into(imageView);
    }

    private void initBtn()
    {
        Button btn_image = findViewById(R.id.btn_image);//跳转图片加载页面
        Button btn_video = findViewById(R.id.btn_video);//跳转视频播放页面

        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent);
            }
        });

        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VideoActivity.class);
                startActivity(intent);
            }
        });
    }
}
