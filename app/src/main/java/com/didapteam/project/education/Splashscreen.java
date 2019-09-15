package com.didapteam.project.education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Splashscreen extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView persentase, title;
    private int time = 2000, value=0;
    private RelativeLayout rlMain;
    private AsyncTask<Void, Void, Boolean> asyncTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setProgress(0);
        persentase = findViewById(R.id.prg_percent);
        title = findViewById(R.id.title);
        rlMain = findViewById(R.id.rl_main);

        startProcess();
    }

    private void startProcess() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncTask = new AsyncTask<Void, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        try {
                            Socket socket = new Socket();
                            socket.connect(new InetSocketAddress("8.8.8.8", 53), 1000);
                            socket.close();
                            return true;
                        } catch (IOException e) {
                            return false;
                        }
                    }

                    @Override
                    protected void onPostExecute(Boolean result) {
                        super.onPostExecute(result);
                        if (result) {
                            final Handler handler = new Handler(){
                                @Override
                                public void handleMessage(Message msg) {
                                    super.handleMessage(msg);
                                    persentase.setText(Integer.toString(value)+"%");
                                    if(value == progressBar.getMax()){
                                        startActivity(new Intent(Splashscreen.this, LoginActivity.class));
                                        finish();
                                    }
                                    value++;
                                }
                            };

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        for (int i=0;i<=progressBar.getMax();i++){
                                            progressBar.setProgress(i);
                                            handler.sendMessage(handler.obtainMessage());
                                            Thread.sleep(50);
                                        }
                                    }catch (InterruptedException ex){
                                        ex.printStackTrace();
                                    }
                                }
                            });
                            thread.start();
                        } else {
                            Snackbar.make(rlMain, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startProcess();
                                }
                            }).show();
                        }
                    }
                }.execute();
            }
        },time);
    }
}
