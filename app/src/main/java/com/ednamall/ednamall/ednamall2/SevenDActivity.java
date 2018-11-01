package com.ednamall.ednamall.ednamall2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.ednamall.ednamall.ednamall2.R;

public class SevenDActivity extends AppCompatActivity {

    TextView txttitle;


    String url = "http://ednamall.co/api/get7d.php";


    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    public Animation animBounce;


    private static final Pattern REGEX_PATTERN =
            Pattern.compile("(?<=src=\")[^\"]*(?<!\")");


    public static String getVidioid(String input) {
        REGEX_PATTERN.matcher(input).matches();
        Matcher matcher = REGEX_PATTERN.matcher(input);
        while (matcher.find()) {
            return matcher.group();
        }

        return "";
    }


    private void getofflineData() throws JSONException {
        FileManager fileManager = new FileManager();
        String stringfile = fileManager.readFromFile("sevend.dat", getApplicationContext());
        JSONArray response = new JSONArray(stringfile);

        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject jsonObject = response.getJSONObject(i);

                Album movie = new Album();
                movie.setName(jsonObject.getString("title"));

                movie.setThumbnail(jsonObject.getString("image"));

                movie.setShortDescription(jsonObject.getString("description"));
                movie.setLongDescription(jsonObject.getString("description"));


                String videoId = "";
                try {
                    String s = getVidioid(jsonObject.getString("youtubelink"));
                    videoId = s.split("/embed/")[1];


                } catch (Exception ex) {

                    Log.e("Error on Video ", ex.getMessage());


                }

                movie.setVideo(videoId);
                albumList.add(movie);
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                Log.e("Json Exception : ", e.getMessage());

            }
            adapter.notifyDataSetChanged();


        }


    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("Response : ", String.valueOf(response));
                FileManager fileManager = new FileManager();
                fileManager.writeToFile("sevend.dat", String.valueOf(response), getApplicationContext());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Album movie = new Album();
                        movie.setName(jsonObject.getString("title"));

                        movie.setThumbnail(jsonObject.getString("image"));

                        movie.setShortDescription(jsonObject.getString("description"));
                        movie.setLongDescription(jsonObject.getString("description"));


                        String videoId = "";
                        try {
                            String s = getVidioid(jsonObject.getString("youtubelink"));
                            videoId = s.split("/embed/")[1];


                        } catch (Exception ex) {

                            Log.e("Error on Video ", ex.getMessage());


                        }

                        movie.setVideo(videoId);
                        albumList.add(movie);
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        Log.e("Json Exception : ", e.getMessage());

                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    getofflineData();
                } catch (JSONException e) {
                    //e.printStackTrace();
                }

                Log.e("Volley Error : ", error.toString());
                progressDialog.dismiss();


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.getCache();
        requestQueue.add(jsonArrayRequest);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_d);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        setSupportActionBar(toolbar);

        this.setTitle("Edna Mall 7D Simulation");
        txttitle = findViewById(R.id.txtwelcome);
        txttitle.setText("Edna Mall 7D Simulation");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new SevenDActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        ImageView imgbanner = findViewById(R.id.imgbanner);
        getData();

        String img_url = "http://ednamall.co/images/banner.jpg";


        Picasso.with(SevenDActivity.this).load(img_url).fit().centerInside()
                .placeholder(R.drawable.ednamall)
                .error(R.drawable.ednamall)
                .into(imgbanner);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareBody = "Downlolad Edna Mall app From Google Play  : https://play.google.com/store/apps/details?id=com.ednamall.ednamall.ednamall2&hl=en";
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Get Edna Mall App From Google Play ");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share This Massage Using "));

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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