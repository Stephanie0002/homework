package com.bytedance.videoplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * 使用系统VideoView播放 resource 视频
 */
public class VideoActivity extends AppCompatActivity {
    private Button buttonPlay;
    private Button buttonPause;
    private VideoView videoView;
    private TextView tv_time;
    private SeekBar seekBar;

    private int current_time;
    private int total_time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video);
        setTitle("VideoView");

        buttonPause = findViewById(R.id.buttonPause);
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });

        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });

        seekBar = findViewById(R.id.seekbar);
        tv_time = findViewById(R.id.tv_time);

        videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218093206z8V1JuPlpe.mp4"));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                current_time = videoView.getCurrentPosition();//单位毫秒
                total_time = videoView.getDuration();//单位毫秒
                seekBar.setMax(total_time);
                int a = current_time/1000;
                int b = total_time/1000;
                tv_time.setText(a+"s/\n"+b+"s");
                videoView.start();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {//进度条变化
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current_time = seekBar.getProgress();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                videoView.seekTo(current_time);
                int a = current_time/1000;
                int b = total_time/1000;
                tv_time.setText(a+"s/\n"+b+"s");
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()) {
                    current_time = videoView.getCurrentPosition();//单位毫秒
                    int a = current_time / 1000;
                    int b = total_time / 1000;
                    seekBar.setProgress(current_time);
                    tv_time.setText(a + "s/\n" + b + "s");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }

}
