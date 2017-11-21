package com.dylanmatthewsware.tigertigerdurbanevents;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by home on 2017/11/21.
 */

public class Event_Array extends ArrayAdapter<Event> {
    private Context context;
    private List<Event> eventList = new ArrayList<Event>();
    public Event_Array(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    static class CardViewHolder {
        TextView eventName;
        TextView description;
        TextView hostName;
        TextView dat;
        TextView address;
    }

    @Override
    public void add(Event object) {
        eventList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.eventList.size();
    }

    @Override
    public Event getItem(int index) {
        return this.eventList.get(index);
    } //gets event item

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //gets

        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.eventName = (TextView) row.findViewById(R.id.tvEventName);
            viewHolder.description = (TextView) row.findViewById(R.id.tvDes);           //Holds all the event information in the viewholder
            viewHolder.hostName = (TextView)  row.findViewById(R.id.tvHostName);
            viewHolder.dat = (TextView)  row.findViewById(R.id.tvDate);
            viewHolder.address = (TextView)  row.findViewById(R.id.tvAddress);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }

        Event events = getItem(position);
        viewHolder.eventName.setText(events.getName());
        viewHolder.description.setText(events.getDescription());
        viewHolder.address.setText(events.getStreet() + ", " + events.getCity() + ", " + events.getZip() + ", " +events.getCountry());
        viewHolder.hostName.setText(events.getNameOfPlace());
        viewHolder.dat.setText("start time :\t" + events.getStartTime() + "\n" + "end time :\t" + events.getEndTime());

        return row;
    }


}