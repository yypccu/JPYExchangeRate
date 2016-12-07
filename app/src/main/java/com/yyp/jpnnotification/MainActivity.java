package com.yyp.jpnnotification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;

/**
 * Created by I5-4430 on 2016/11/28.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create a intent that start JPNPullService from current activity(e.q. this)
        Intent mServiceIntent = new Intent(this, JPNPullService.class);
        startService(mServiceIntent);
        finish();
    }
}
