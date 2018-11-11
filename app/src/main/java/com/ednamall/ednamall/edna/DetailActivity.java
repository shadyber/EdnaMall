package com.ednamall.ednamall.edna;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;


public class DetailActivity  extends YouTubeBaseActivity  {

private static final int RECOVERY_REQUEST = 1;
private YouTubePlayerView youTubeView;
private String videoId="";
private  String image="";
private  String title="";
private  String shortDisc="";
private  String description="";
private String movieid="";
private String cast="";
private  String gener="";

TextView txtdetail,txttitle,txtcast,txtgener;



private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
Intent home=new Intent(DetailActivity.this,MainActivity.class);
startActivity(home);
                    return true;
                case R.id.navigation_dashboard:
                    Intent nowshow=new Intent(DetailActivity.this,NowshowingActivity.class);
                    startActivity(nowshow);
                    return true;
                case R.id.navigation_notifications:
                    Intent comming=new Intent(DetailActivity.this,CommingActivity.class);
                    startActivity(comming);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final ImageView imageposter=findViewById(R.id.imageposter);
        final ImageView imagepostertop=findViewById(R.id.imagepostertop);


try {
    videoId = getIntent().getStringExtra("video");
  image=getIntent().getStringExtra("image");
    title=getIntent().getStringExtra("title");
    description=getIntent().getStringExtra("description");
    shortDisc=getIntent().getStringExtra("short_disc");
    cast=getIntent().getStringExtra("cast");
    gener=getIntent().getStringExtra("gener");
}catch(Exception ex){
    Log.e("Error on Intent : ",ex.getMessage());

}





        if (!image.equalsIgnoreCase(""))
            Picasso.with(getApplicationContext()).load(image).placeholder(R.drawable.ednamall)// Place holder image from drawable folder
                    .error(R.drawable.ednamall).resize(110, 110).centerCrop()
                    .into(imageposter);

        Picasso.with(getApplicationContext()).load(image).placeholder(R.drawable.placeholder)// Place holder image from drawable folder
                .error(R.drawable.placeholder).resize(110, 110).centerCrop()
                .into(imagepostertop);

        imagepostertop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=image;

                Intent ii=new Intent(DetailActivity.this, FullScreenImageActivity.class);
                ii.putExtra("image", s);
                ii.putExtra("title",title);
                startActivity(ii);
            }
        });

txtdetail=findViewById(R.id.txtdetail);
txtdetail.setText(description);


txtcast=findViewById(R.id.txtcast);
txtcast.setText(cast);


        txttitle=findViewById(R.id.txttitle);
        txttitle.setText(title);

        YouTubePlayerView youTubePlayerView =
                (YouTubePlayerView) findViewById(R.id.youtube_view);

        youTubePlayerView.initialize(Config.YOUTUBE_API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo(videoId);
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
