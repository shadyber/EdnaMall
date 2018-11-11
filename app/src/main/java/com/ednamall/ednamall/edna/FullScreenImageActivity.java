package com.ednamall.ednamall.edna;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;


public class FullScreenImageActivity extends AppCompatActivity {

String imageURL="http://ednamall.co/images/banner.jpg";
    public void setAsWallpaper(View view){

        Picasso.with(this).load(imageURL).into(new Target(){

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(FullScreenImageActivity.this);
                try {
                    wallpaperManager.setBitmap(bitmap);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Log.d("TAG", "onBitmapLoaded: ");
                Toast.makeText(FullScreenImageActivity.this, "Wallpaper Changed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                Log.d("TAG", "FAILED");
                imageURL="http://ednamall.co/images/banner.jpg";
                Toast.makeText(FullScreenImageActivity.this, "Loading image failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                Log.d("TAG", "Prepare Load");
                Toast.makeText(FullScreenImageActivity.this, "Downloading image", Toast.LENGTH_SHORT).show();
            }
        });

    }


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
Bundle t=iin.getExtras();

if(t!=null){
    this.setTitle(t.getString("title")+" @ Edna Mall");

}
        if(b!=null)
        {
            String image =(String) b.get("image");
            imageURL=image;
            Picasso.with(getApplicationContext()).load(image).placeholder(R.drawable.placeholder)// Place holder image from drawable folder
                    .error(R.drawable.placeholder)
                    .into(imgDisplay);

        }



    }



}

