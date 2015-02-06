package com.leobee.mylayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Leondria on 1/22/2015.
 * Description: Activity main page while DataService is running
 */
public class ShowArtActivity extends Activity implements View.OnClickListener {

    Button btn;
    @Override
    public void onCreate(Bundle savedInstancedState){
        super.onCreate(savedInstancedState);
        setContentView(R.layout.activity_show_art);
        btn =(Button)findViewById(R.id.button2);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    // Displays the art piece
        Intent i = new Intent(this,PassiveArtActivity.class);
        startActivity(i);


    }




}
