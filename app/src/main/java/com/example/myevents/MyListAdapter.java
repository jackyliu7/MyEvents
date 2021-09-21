package com.example.myevents;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * this class defines a list adapter
 */
public class MyListAdapter extends ArrayAdapter<User> {

    private LayoutInflater inflater;
    private ArrayList<User> users;
    private int viewResourceId;

    /**
     *Initalizes the list adapter
     * @param context
     * @param textViewResourceId This is the ID for textview widget
     * @param users An arraylist to store events created by the user
     */
    public MyListAdapter(Context context, int textViewResourceId, ArrayList<User> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = textViewResourceId;
    }

    /**
     * Displays data to the relevant textiews which are nested inside a lisvtiew
     * @param position
     * @param convertView
     * @param parents
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = inflater.inflate(viewResourceId, null);

        User user = users.get(position);

        if (user != null) {
            TextView name = convertView.findViewById(R.id.TextView1);
            TextView date = convertView.findViewById(R.id.TextView2);
            TextView time = convertView.findViewById(R.id.TextView3);
            if (name != null) {
                name.setText(user.getName());
            }
            if (date != null) {
                date.setText(user.getDate());
            }
            if (time != null) {
                time.setText(user.getTime());
            }
        }
    return convertView;
    }
}


