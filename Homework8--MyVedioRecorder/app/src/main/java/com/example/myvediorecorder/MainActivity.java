package com.example.myvediorecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    private int PERMISSION_REQUEST_CODE = 1028;
    private android.hardware.Camera mCamera;
    private MediaRecorder mediaRecorder;
    private SurfaceView surfaceView;
    private Button btn;
    private VideoView vedioView;
    private SurfaceHolder surfaceHolder;
    private String mp4Path;
    private boolean isRcording;
    private boolean failPrepare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get_permissions();
        intiView();
        intiCamera();
    }

    private void intiView()
    {
        surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback((SurfaceHolder.Callback) this);

        isRcording = false;
        failPrepare = false;

        vedioView = findViewById(R.id.videoView);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//录制or结束录制
                vedioView.setVisibility(View.GONE);
                record(surfaceView);
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        try {
            intiCamera();
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(holder.getSurface() == null){
            return;
        }
        mCamera.stopPreview();//停止预览
        try{//重新预览
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mCamera.stopPreview();
        mCamera.release();
        mCamera=null;//释放Camera
    }

    private void intiCamera()//初始化相机
    {
        mCamera = android.hardware.Camera.open();
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.set("orientation","portrait");
        parameters.set("rotation",90);
        mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(90);
    }

    private boolean prepareVideoRecord(){
        mediaRecorder = new MediaRecorder();

        mCamera.unlock();
        mediaRecorder.setCamera(mCamera);

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));

        mp4Path = getOutputMediaPath();
        mediaRecorder.setOutputFile(mp4Path);

        mediaRecorder.setOrientationHint(90);
        mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());

        try{
            mediaRecorder.prepare();
        }catch (Exception e){
            mediaRecorder.release();
            return false;
        }
        return true;
    }

    public void record(View view){
        if(isRcording){
            btn.setText("录制");
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            mCamera.lock();

            vedioView.setVisibility(View.VISIBLE);
            vedioView.setVideoPath(mp4Path);
            vedioView.start();
            isRcording = !isRcording;
        }else {
            failPrepare = prepareVideoRecord();
            if(failPrepare) {
                btn.setText("结束录制");
                mediaRecorder.start();
                isRcording = !isRcording;
            }
        }
    }

    private String getOutputMediaPath() {
        File mediaStorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir, "IMG_" + timeStamp + ".mp4");
        if (!mediaFile.exists()) {
            mediaFile.getParentFile().mkdirs();
        }
        return mediaFile.getAbsolutePath();
    }

    private void get_permissions()//申请所有需要的权限
    {
        String[] permissions_camera = new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO};
        ActivityCompat.requestPermissions(this,permissions_camera,PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(mCamera == null){
            intiCamera();
        }
        mCamera.startPreview();
    }
    @Override
    public void onPause(){
        super.onPause();
        mCamera.stopPreview();
    }
}
