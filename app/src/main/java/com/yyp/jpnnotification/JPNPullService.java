package com.yyp.jpnnotification;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by I5-4430 on 2016/11/28.
 */

public class JPNPullService extends IntentService {

    public JPNPullService() {
        this(JPNPullService.class.getName());
    }
    public JPNPullService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        showToast("Starting IntentService");
        try {
            showNotification();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        showToast("Finishing IntentService");
    }

    protected void showNotification () {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.fukuzawa)
                .setContentTitle("TWBank JPY Rate")
                .setContentText(Bank.getDate() + " 賣出 " + Bank.getJpyPrice());
        int mId = 219; //
        mNotificationManager.notify(mId, mBuilder.build());
    }

    protected void showToast(final String msg){
        //gets the main thread
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                // run this code in the main thread
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
