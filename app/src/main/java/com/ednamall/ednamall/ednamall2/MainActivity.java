package com.ednamall.ednamall.ednamall2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.ednamall.ednamall.ednamall2.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {




    public static void sendAppItself(Activity paramActivity) throws IOException {
        PackageManager pm = paramActivity.getPackageManager();
        ApplicationInfo appInfo;
        try {
            appInfo = pm.getApplicationInfo(paramActivity.getPackageName(),
                    PackageManager.GET_META_DATA);
            Intent sendBt = new Intent(Intent.ACTION_SEND);
            sendBt.setType("*/*");
            sendBt.putExtra(Intent.EXTRA_STREAM,
                    Uri.parse("file://" + appInfo.publicSourceDir));

            paramActivity.startActivity(Intent.createChooser(sendBt,
                    "Share Edna Mall App  using"));
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Package Not Found: ",e1.getMessage());
        }
    }


    public Animation animBounce;
private RecyclerView recyclerView;
    private MainMenuAdapter adapter;
    private List<MainMenu> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        menuList = new ArrayList<>();
        adapter = new MainMenuAdapter(this, menuList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
ImageView imgbanner=findViewById(R.id.imgbanner);

        prepareAlbums();
        String img_url = "http://ednamall.co/images/banner.jpg";


        Picasso.with(MainActivity.this).load(img_url).fit().centerInside()
                .placeholder(R.drawable.ednamall)
                .error(R.drawable.ednamall)
                .into(imgbanner);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Share our app and invite your Friends", Snackbar.LENGTH_LONG)
                        .setAction("Thank You", null).show();
            }
        });


       animBounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else  if (id == R.id.action_share) {
            try {
                sendAppItself(MainActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }else if (id == R.id.action_about) {
           Intent about=new Intent(MainActivity.this,AboutActivity.class);
           startActivity(about);
           return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_nowshow) {
            // Handle the camera action
            Intent nowshowing=new Intent(MainActivity.this,NowshowingActivity.class);
            startActivity(nowshowing);
            return true;
        }else if (id == R.id.nav_comming) {
            Intent comming=new Intent(MainActivity.this,CommingActivity.class);
            startActivity(comming);
            return true;
        } else if (id == R.id.nav_Games) {
            Intent bongos=new Intent(MainActivity.this,BongosActivity.class);
            startActivity(bongos);
            return true;
        } else if (id == R.id.nav_7d) {
            Intent sd=new Intent(MainActivity.this,SevenDActivity.class);
            startActivity(sd);
            return true;
        } else if (id == R.id.nav_share) {
           String shareBody = "Downlolad Edna Mall app From Google Play  : https://play.google.com/store/apps/details?id=com.ednamall.ednamall.ednamall2&hl=en";
          Intent  sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Get Edna Mall App From Google Play ");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share This Massage Using "));

        } else if (id == R.id.nav_send) {
            String shareBody = "Downlolad Edna Mall app From Google Play  : https://play.google.com/store/apps/details?id=com.ednamall.ednamall.ednamall2&hl=en";
            Intent  sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Get Edna Mall App From Google Play ");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share This Massage Using "));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.comming,
                R.drawable.nowshowing,
                R.drawable.sevend,
                R.drawable.bongos,
                R.drawable.ednamall};

        MainMenu a = new MainMenu("Coming Soon",  covers[0]);
        menuList.add(a);

         a = new MainMenu("Now Showing",  covers[1]);
        menuList.add(a);

  a = new MainMenu("7D Movies",  covers[2]);
        menuList.add(a);

         a = new MainMenu("Games",  covers[3]);
        menuList.add(a);

        a = new MainMenu("Contact US",  covers[4]);
        menuList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
