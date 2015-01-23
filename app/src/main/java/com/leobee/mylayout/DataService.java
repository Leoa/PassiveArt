package com.leobee.mylayout;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ServiceConfigurationError;

/**
 * Created by Leondria on 1/22/2015.
 */
public class DataService  extends Service {

    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(this, "on create service", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(this, "on start command service", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){

        super.onDestroy();
        Toast.makeText(this, " service Destroyed", Toast.LENGTH_LONG).show();

    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


/*
*
* public class myService extends Service {
 public TheDB db;
 Context context;

 @Override public void onCreate()
{ context = getApplicationContext();
 this.db = TheDB(context);
}
* */