package com.ednamall.ednamall.ednamall2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.ednamall.ednamall.ednamall2.R;

public class AboutActivity extends AppCompatActivity {

  public void showRoot(View view){
     Intent intent =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=8751833429873021230"));
      startActivity(intent);


  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
