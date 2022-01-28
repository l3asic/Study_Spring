package com.example.project1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class FileDownLoadService extends Service {
    private static String TAG = "fildeDown";
    public boolean isFileDown = false;

    public FileDownLoadService() {
    }
    //Ibinder 를 이용해서 화면이 바뀌거나 하는처리를 넣음 . 에러 발생률이높아서 많이사용안함


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: service");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //이벤트처리 부분 ↓
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String command = intent.getStringExtra("command");
        if (!isFileDown) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 1; i <= 100; i++) {
                        Log.d(TAG, "onStartCommand: " + i + command);
                        MainActivity.progressBar.setProgress(i);
                        if (i==100){
                            isFileDown = false;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}