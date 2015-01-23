package com.leobee.mylayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

/**
 * Created by Leondria on 1/22/2015.
 */
public class PassiveArtActivity extends Activity implements View.OnClickListener {

    Button btn;
    View layout;
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

        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xFF616261,0xFF165824});
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
                Intent j = new Intent(this,MainActivity.class);
                startActivity(j);
// delete rows
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
