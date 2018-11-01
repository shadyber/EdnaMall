package com.ednamall.ednamall.ednamall2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import co.ednamall.ednamall.ednamall2.R;

public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);



        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");

        ImageView imgDisplay;
        Button btnClose;


        imgDisplay = (ImageView) findViewById(R.id.imgDisplay);
        btnClose = (Button) findViewById(R.id.btnClose);


        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FullScreenImageActivity.this.finish();
            }
        });


        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String image =(String) b.get("image");
            Picasso.with(getApplicationContext()).load(image).placeholder(R.drawable.placeholder)// Place holder image from drawable folder
                    .error(R.drawable.placeholder).resize(110, 110).centerCrop()
                    .into(imgDisplay);

        }



    }



}

