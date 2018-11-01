package com.ednamall.ednamall.ednamall2;

import android.content.ContentProviderOperation;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import co.ednamall.ednamall.ednamall2.R;

public class ContactActivity extends AppCompatActivity {



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
    }

}
