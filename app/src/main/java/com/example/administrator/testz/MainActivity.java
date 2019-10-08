package com.example.administrator.testz;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    FirstThread myFirstThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void xx(View view) {
        final CustomDialog dialog = new CustomDialog(this);
        dialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 5000);

    }


    public void circleLine(View view) {
        dialog = new ProgressDialog(this);

        dialog.setTitle("Downloading dialog ....");
        dialog.setMessage("Download is progress ...");
        dialog.setProgressStyle(dialog.STYLE_HORIZONTAL);
        dialog.setProgress(0);
        dialog.setMax(10);
        dialog.show();

        myFirstThread = new FirstThread();
        myFirstThread.start();

    }


    public void circleProcess(View view) {
        dialog = new ProgressDialog(this);

        dialog.setTitle("Downloading dialog ....");
        dialog.setMessage("Download is progress ...");
        dialog.setProgressStyle(dialog.STYLE_SPINNER);
        dialog.setProgress(0);
        dialog.setMax(10);
        dialog.show();

        myFirstThread = new FirstThread();
        myFirstThread.start();

    }



    class FirstThread extends Thread{
        @Override
        public void run() {
            while (dialog.getProgress()<10){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.incrementProgressBy(1);
                    }
                });

                try {
                    Thread.sleep(1000);
                }catch (Exception ex){

                }
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            });

        }
    }

}
