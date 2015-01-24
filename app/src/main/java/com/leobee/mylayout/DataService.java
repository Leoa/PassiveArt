package com.leobee.mylayout;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ServiceConfigurationError;

import thedatabase.DatabaseOperations;
import utilities.RandomNumbers;
import utilities.Timestamp;

/**
 * Created by Leondria on 1/22/2015.
 */
public class DataService  extends Service implements SensorEventListener {
    DatabaseOperations DB;
    Context ctx= this;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate=0;
    private float last_x,last_y,last_z;
    private static final int SHAKE_THRESHOLD = 1;
    private float [] history = new float[3];

    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(this, "on create service", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //this.db
        this.DB= new DatabaseOperations(ctx);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer =  senSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        senSensorManager.registerListener( this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);

        Toast.makeText(this, "on start command service", Toast.LENGTH_LONG).show();

        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy(){

        super.onDestroy();
        senSensorManager.unregisterListener(this);
        Toast.makeText(this, " service Destroyed", Toast.LENGTH_LONG).show();

    }


    public void onSensorChanged(SensorEvent event){
        // where we can detect change
        Sensor mySensor = event.sensor;

        // value added to database for art calculations
        if(mySensor.getType()==Sensor.TYPE_ACCELEROMETER){
            // get x,y and z of the phone's position
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float xChange = history[0] - x;
            float yChange = history[1] - y;
            float zChange = history[2] - z;

            history[0] = x;
            history[1] = y;
            history[2] = z;

            // get the current time of the
            long curTime=System.currentTimeMillis();


            // compare the current time to last update to limit data from sensor
            if((curTime - lastUpdate)>1){
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                //detect if the device has been shaken
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 3000;
                if(speed < SHAKE_THRESHOLD){
                    // do nothing

                } else {

                    //preff connector
                    if (xChange > 2) {

                        DB.putInfo(DB,"left", Timestamp.getCurrentTimeStamp());
                    } else if (xChange < -2) {

                        DB.putInfo(DB,"right", Timestamp.getCurrentTimeStamp());
                    } else if (yChange > 2) {

                        DB.putInfo(DB,"down", Timestamp.getCurrentTimeStamp());
                    } else if (yChange < -2) {

                        DB.putInfo(DB,"up", Timestamp.getCurrentTimeStamp());
                    } else if (zChange > 2) {

                        DB.putInfo(DB,"front", Timestamp.getCurrentTimeStamp());
                    } else if (zChange < -2) {

                        DB.putInfo(DB,"back", Timestamp.getCurrentTimeStamp());
                    } else {
                        // textBox.setText("phone is shaking");
                        //DB.putInfo(DB,"shake", Timestamp.getCurrentTimeStamp());
                        last_x = x;
                        last_y = y;
                        last_z = z;
                    }
                }


            }
        }
    }




    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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