package com.example.myevents;


import java.util.ArrayList;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * this class is a separate activity allowing users to view their previously stored data
 */
public class ViewContents extends AppCompatActivity {
    private DBHelper dbHelper;

    /**
     * reads data from database, creates a list adapter and displays its contents with an ArrayList
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_list_view);
        dbHelper = new DBHelper(this, null, 1);

        ArrayList<User> userList = new ArrayList<User>();
        Cursor data = dbHelper.getListContents();
        if (data.getCount() != 0) {
            while(data.moveToNext()) {
                User user = new User(data.getString(1), data.getString(2), data.getString(3));
                userList.add(user);
            }
            MyListAdapter adapter = new MyListAdapter(this, R.layout.viewevents, userList);
            ListView listView = findViewById(R.id.ListView);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(ViewContents.this, "Empty database", Toast.LENGTH_LONG).show();
        }

    }
}
