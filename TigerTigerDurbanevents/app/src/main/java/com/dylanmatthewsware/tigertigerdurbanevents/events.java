package com.dylanmatthewsware.tigertigerdurbanevents;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class events extends AppCompatActivity {

    private ListView listView;
    private Event_Array EventArrayAdapter;
    private String text[][];
    private int cnt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        listView = (ListView) findViewById(R.id.card_listView);
        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));
        text= new String[100][13];

        try {
            new DownloadRawData().execute(createUrl());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String createUrl() throws UnsupportedEncodingException {
        String link = "https://graph.facebook.com/v2.3/tigerdurban/events?access_token=164901674096912%7CPicCRyjg4PNvfAXvep5JHFixW50&time_filter=upcoming" ;//"https://graph.facebook.com/v2.3/tigerdurban/events?time_filter=upcoming?access_token=164901674096912|PicCRyjg4PNvfAXvep5JHFixW50";

        String urlLink= URLEncoder.encode(link, "utf-8"); //encodes string
        /// String urlDes = URLEncoder.encode(destination, "utf-8"); //encodes string

        return link;
        //returns the end of the url for the google maps url for response
    }

    private class DownloadRawData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) { //in background
            String link = params[0];//gets params [0]
            // Toast.makeText(navEvents.this, "link " + link, Toast.LENGTH_SHORT).show();
            try {
                URL url = new URL(link);

                InputStream inputStream = url.openConnection().getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) { //builds string            //Reads json from Facebook Page
                    buffer.append(line + "\n"); //appends the buffer
                }

                return buffer.toString();
                //returns json data

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String res) { //post excecute
            try {
                parseJSon(res);
                //sends the json data to method to read json
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
//https://developers.google.com/android/reference/com/google/android/gms/maps/model/Polyline
    //reading google maps api response
    //https://stackoverflow.com/questions/15924834/decoding-polyline-with-new-google-maps-api
//https://github.com/jd-alexander/Google-Directions-Android/blob/master/library/src/main/java/com/directions/route/GoogleParser.java
//https://gist.github.com/hallahan/7b4a3aebe4d7a0b41a5a

    private void parseJSon(String json) throws JSONException {//reads json
        if (json == null)
            return;

        EventArrayAdapter = new Event_Array(getApplicationContext(),R.layout.list_item);
        List<Event> events = new ArrayList<Event>();
        JSONObject jsonData = new JSONObject(json);
        JSONArray jsonRoutes = jsonData.getJSONArray("data");

        int cnt = 0;
        //Toast.makeText(this, "data", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < jsonRoutes.length(); i++) { //loop going up to end of json route
            String message = "";

            JSONObject jsonEvent = jsonRoutes.getJSONObject(i);
            Event event = new Event();





            message+="Event name :\t" + jsonEvent.getString("name") + "\n";
            message+= "description :\t" + jsonEvent.getString("description") + "\n";
            message+= "start time :\t" + jsonEvent.getString("start_time")+ "\n";
            message+= "end time :\t" + jsonEvent.getString("end_time")+ "\n";


            event.setDescription(jsonEvent.getString("description"));

            JSONObject place  = new JSONObject(jsonEvent.getString("place"));

            event.setNameOfPlace( place.getString("name"));

            message+= "name of host :\t" + place.getString("name") + "\n";

            JSONObject location = new JSONObject(place.getString("location") );

            //gets data

            String  name = jsonEvent.getString("name");
            String startTime = jsonEvent.getString("start_time");
            String endTime = jsonEvent.getString("end_time");
            String description = jsonEvent.getString("description");
            String hostName = place.getString("name");
            String city = location.getString("city");
            String country = location.getString("country");
            Double latitude = Double.parseDouble(location.getString("latitude"));
            Double longitude = Double.parseDouble(location.getString("longitude"));
            String streetAddress = location.getString("street");
            String zip = location.getString("zip");

            //sets data

            event.setName(name);
            event.setNameOfPlace(hostName);
            event.setStartTime(startTime);
            event.setEndTime(endTime);
            event.setDescription(description);
            event.setCity(city);
            event.setCountry(country);
            event.setLatitude(latitude);
            event.setLongitude(longitude);
            event.setStreet(streetAddress);
            event.setZip(zip);


            text[cnt][0] = name;
            text[cnt][1] = hostName;
            text[cnt][2] = startTime;
            text[cnt][3] = endTime;
            text[cnt][4] = description;
            text[cnt][5] = city;
            text[cnt][6] = country;
            text[cnt][7] = ""+latitude;
            text[cnt][8] = "" + longitude;
            text[cnt][9] = streetAddress;
            text[cnt][10] = zip;

            cnt++;

            Event e = new Event(name,description,startTime,endTime,hostName,city,country,latitude, longitude,streetAddress,zip);
            EventArrayAdapter.add(e);
            listView.setAdapter(EventArrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Intent intent = new Intent(events.this,EventScrolling.class);
                    intent.putExtra("name", text[position-1][0]);
                    intent.putExtra("host", text[position-1][1]);
                    intent.putExtra("start", text[position-1][2]);
                    intent.putExtra("end", text[position-1][3]);
                    intent.putExtra("des", text[position-1][4]);
                    intent.putExtra("city", text[position-1][5]);
                    intent.putExtra("country", text[position-1][6]);
                    intent.putExtra("latitude", text[position-1][7]);
                    intent.putExtra("longitude", text[position-1][8]);
                    intent.putExtra("street", text[position-1][9]);
                    intent.putExtra("zip", text[position-1][10]);

                    startActivity(intent);



                }



            });




        }

    }
}
