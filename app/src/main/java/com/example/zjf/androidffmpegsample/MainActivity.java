package com.example.zjf.androidffmpegsample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if ( ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
        }
    }

    public void run(View view) {
        String base = Environment.getExternalStorageDirectory().getPath();
        Log.e("PATH", base);
        String[] commands = new String[9];
        commands[0] = "ffmpeg";
        commands[1] = "-i";
        commands[2] = base + "/input.mp4";
        commands[3] = "-i";
        commands[4] = base + "/input.mp3";
        commands[5] = "-strict";
        commands[6] = "-2";
        commands[7] = "-y";
        commands[8] = base + "/merge.mp4";
        int result = FFmpeg.run(commands);
        if(result == 0){
            Toast.makeText(MainActivity.this, "命令行执行完成", Toast.LENGTH_SHORT).show();
            Log.e("RESULT = ", Integer.toString(result));
        }
    }
}
