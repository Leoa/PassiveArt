package com.leobee.mylayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

import thedatabase.DatabaseOperations;

/**
 * Created by Leondria on 1/22/2015.
 */
public class PassiveArtActivity extends Activity implements View.OnClickListener {

    Button btn;
    View layout;
    Context ctx = this;
    DatabaseOperations DB = new DatabaseOperations(ctx);
    double obtainedTotal=0;
    double colorTotal=0;
    float redPercentage;
    float greenPercentage;
    float bluePercentage;



    @Override

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_passive_art);
        // go back to show screen
        Button one = (Button) findViewById(R.id.continueBtn);
        one.setOnClickListener(this);
         // Quit
        Button two = (Button) findViewById(R.id.quitBtn);
        two.setOnClickListener(this);

        int posLeft = DB.getSumLeft();
        int posRight = DB.getSumRight();
        int posUp = DB.getSumUp();
        int posDown = DB.getSumDown();
        int posFront = DB.getSumFront();
        int posBack = DB.getSumBack();

        int red = posLeft + posRight;
        int green = posUp + posDown;
        int blue = posFront + posBack;

        obtainedTotal = red+green+blue;
        redPercentage= (float)(red*100/obtainedTotal);
        int totalRed =(int)(225 * (redPercentage /100.0f));
        greenPercentage = (float)(green*100/obtainedTotal);
        int totalGreen =(int)(225 * (greenPercentage /100.0f));
        bluePercentage = (float)(blue*100/obtainedTotal);
        int totalBlue =(int)(225 * (bluePercentage /100.0f));


        System.out.println("obtainedTotal="+obtainedTotal);
        System.out.println("Total red="+red);
        System.out.println( "red precent "+redPercentage);
        System.out.println( "red color precent "+totalRed);
int total = 225;

        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {Color.rgb(totalRed,total/2, total/2),Color.rgb(total/2, totalGreen, total/2), Color.rgb(total/2, total/2, totalBlue)});
        gd.setCornerRadius(0f);
        layout = findViewById(R.id.activity_passive_art);

        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            layout.setBackgroundDrawable(gd);
        } else {
            layout.setBackground(gd);
        }

    }
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.continueBtn:
                Intent i = new Intent(this,ShowArtActivity.class);
                String x = "continue";
                i.putExtra("continue", x);
                startActivity(i);

                break;

            case R.id.quitBtn:
                stopMethod(v);
                // delete rows
                DB.deleteRows();

                Intent j = new Intent(this,MainActivity.class);

                startActivity(j);


                break;

            default:
                break;
        }

    }

    private void stopMethod(View v) {

        Intent i = new Intent(this,DataService.class);
        stopService(i);

    }



}
