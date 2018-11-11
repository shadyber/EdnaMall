package com.ednamall.ednamall.edna;

import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ContactActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private PhonesAdapter adapter;
    private List<PhonesMenu> menuList;


    String displayName = "Edna Mall Matti Multiplex";
    String mobileNumber = "+251942131313";
    String homeNumber = "+251116616874";
    String workNumber = "+251116616880";
    String emailID = "info@ednamall.co";
    String company = "Edna Mall";
    String jobTitle = "All Information";


    public void joinTelgram(View vIew){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/EDNAMALL_OFFICIAL_SCHEDULE")));

    }

    public void requestVIber(View vIew){
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO,
                Uri.parse("sms:+251942131313"));
        smsIntent.putExtra("sms_body", "Add Me to Viber List ");
        startActivity(smsIntent);
    }

    public void joinFacebook(View vIew){
Intent intent;
String pageId="396510150414130";
        try {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + pageId));
            startActivity(intent);
        } catch (Exception e) {
            intent =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ednamatticinema"));
            startActivity(intent);
        }


      //   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.m.facebook.com/ednamatticinema")));

    }


    public void joinTwitter(View vIew){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/edna_mall?lang=en")));

    }


    public void joinInstagram(View vIew){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/ednamall_official/?hl=en")));

    }


    public void saveConact(View view){





        ArrayList<ContentProviderOperation> ops =
                new ArrayList<ContentProviderOperation>();

        ops.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build()
        );

        //------------------------------------------------------ Names
        if(displayName != null)
        {
            ops.add(ContentProviderOperation.newInsert(
                    ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(
                            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                            displayName).build()
            );
        }

        //------------------------------------------------------ Mobile Number
        if(mobileNumber != null)
        {
            ops.add(ContentProviderOperation.
                    newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, mobileNumber)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build()
            );
        }

        //------------------------------------------------------ Home Numbers
        if(homeNumber != null)
        {
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, homeNumber)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                    .build());
        }

        //------------------------------------------------------ Work Numbers
        if(workNumber != null)
        {
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, workNumber)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                    .build());
        }

        //------------------------------------------------------ Email
        if(emailID != null)
        {
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
                    .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                    .build());
        }

        //------------------------------------------------------ Organization
        if(!company.equals("") && !jobTitle.equals(""))
        {
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, company)
                    .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
                    .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, jobTitle)
                    .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
                    .build());
        }

        // Asking the Contact provider to create a new contact
        try
        {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            Toast.makeText(getApplicationContext(),"Contact Saved To Your Phone Contact List as Edna Mall Matti Multiplex ",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {

            Toast.makeText(getApplicationContext(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark)); //status bar or the time bar at the top
        }
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent home=new Intent(ContactActivity.this,MainActivity.class);
                startActivity(home);

            }
        });


        recyclerView =   findViewById(R.id.recycler_view);


        menuList = new ArrayList<>();
        adapter = new PhonesAdapter(ContactActivity.this, menuList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ContactActivity.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new ContactActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();


    }




    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {

        menuList.clear();
        int[] covers = new int[]{
                R.drawable.phone,
                R.drawable.mobile
                };

        PhonesMenu a = new PhonesMenu("Marketing Team","+251 116 616874 ","Marketing",  covers[0]);
        menuList.add(a);

        a = new PhonesMenu("Cinema Reservation","+251116 61 62 78","Cinema Reservation",  covers[0]);
        menuList.add(a);

        a = new PhonesMenu("Cinema Head","+251116616880","Cinema Head",  covers[0]);
        menuList.add(a);

        a = new PhonesMenu("Operation","+251116616870","Operation manager",  covers[0]);
        menuList.add(a);

        a = new PhonesMenu("Real Estate Admin Office","+251116616877","Real Estate",  covers[0]);
        menuList.add(a);
        a = new PhonesMenu("General Information "," +251942131313","Edna Mall",  covers[1]);
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
