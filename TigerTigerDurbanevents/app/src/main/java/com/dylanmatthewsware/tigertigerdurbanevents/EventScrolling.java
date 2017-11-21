package com.dylanmatthewsware.tigertigerdurbanevents;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EventScrolling extends AppCompatActivity {

    private String name;
    private String host;
    private String start;
    private String end;
    private String des;
    private String city;
    private String country;
    private String lat;
    private String lon;
    private String street;
    private String zip;
    private TextView tveventinfo;
    private ImageView imageView;
    private Calendar beginTime = Calendar.getInstance();
    private Calendar endTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tveventinfo =  (TextView) findViewById(R.id.tveventinfo);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cal = new Intent(Intent.ACTION_EDIT);
                cal.setType("vnd.android.cursor.item/event");
                cal.putExtra(CalendarContract.Events.TITLE, name);
                cal.putExtra(CalendarContract.Events.DESCRIPTION,des);
                cal.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                cal.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                cal.putExtra(CalendarContract.Events.EVENT_LOCATION,street + ", " + city + ", " + country);

                startActivity(cal);
            }
        });


        host= getIntent().getStringExtra("host");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        des = getIntent().getStringExtra("des");
        city = getIntent().getStringExtra("city");
        country = getIntent().getStringExtra("country");
        lat = getIntent().getStringExtra("latitude");
        lon = getIntent().getStringExtra("longitude");
        street = getIntent().getStringExtra("street");
        zip = getIntent().getStringExtra("zip");
        name  = getIntent().getStringExtra("name");

        setTitle(name);

        String text = "The event is hosted by " + host + ".\nThe event is on " + start + ", the event ends at " + end +
                ".\n" + des + "\n\n" + "The event will be held at " + street + ", " + city + ", " + country;

        tveventinfo.setText(text);

        start =  start.replace("T" , "-");
        end = end.replace("T", "-");


        start = start.replace(":", "-");
        end = end.replace(":", "-");

        String arrStart [] = start.split("-");
        int syear = Integer.parseInt(arrStart[0]);
        int smonth = Integer.parseInt(arrStart[1]);
        int sday  = Integer.parseInt(arrStart[2]);
        int shour = Integer.parseInt(arrStart[3]);
        //shour = shour -2;
        int smin =  Integer.parseInt(arrStart[4]);

        String endArr [] = end.split("-");
        int year = Integer.parseInt(endArr[0]);
        int month = Integer.parseInt(endArr[1]);
        int day  = Integer.parseInt(endArr[2]);
        int hour = Integer.parseInt(endArr[3]);
        // hour = hour -2;
        int min =  Integer.parseInt(endArr[4]);




        beginTime.set(syear,smonth, sday, shour, smin);
        endTime.set(year,month, day, hour, min);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}
