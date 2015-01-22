package com.leobee.mylayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Leondria on 1/22/2015.
 */
public class PassiveArtActivity extends Activity implements View.OnClickListener {

    Button btn;
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

        //gradeint bg
        //image
    }
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.continueBtn:
                Intent i = new Intent(this,ShowArtActivity.class);
                startActivity(i);

                break;

            case R.id.quitBtn:
                Intent j = new Intent(this,MainActivity.class);
                startActivity(j);
// delete rows
                break;

            default:
                break;
        }

    }
}
