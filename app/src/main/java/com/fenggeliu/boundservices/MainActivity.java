package com.fenggeliu.boundservices;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.IBinder;
import android.content.Intent;
import android.content.ComponentName;
import android.content.ServiceConnection;
import com.fenggeliu.boundservices.MyService.MyLocalBinder;

public class MainActivity extends AppCompatActivity {

    MyService liusService;
    boolean isBound = false;

    public void showTime(View view){
        String currentTime = liusService.getCurrentTime();
        TextView liusText = (TextView) findViewById(R.id.liusText);
        liusText.setText(currentTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new  Intent(this, MyService.class);
        bindService(i, liusConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection liusConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyLocalBinder binder = (MyLocalBinder) iBinder;
            liusService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}
