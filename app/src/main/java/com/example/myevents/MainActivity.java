package com.example.myevents;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The MainActivity class defines the mainline logic of this application
 */
public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private TimePicker timePicker;
    private Button btnAdd, btnView;
    private CalendarView calendarView;
    private EditText editText;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * This function runs when the application first starts and initializes widgets
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this, null, 1);
        timePicker = findViewById(R.id.datePicker1);
        editText = findViewById(R.id.editTextTextPersonName);
        calendarView = findViewById(R.id.calendarView2);
        btnAdd = findViewById(R.id.button);
        btnView = findViewById(R.id.button2);

        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(today.getTime());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView,
                                            int year, int month, int day) {
                int true_month = month + 1;
                String monthString = String.valueOf(true_month);
                if (true_month < 10) {
                    monthString = "0" + monthString;
                }
                date = String.valueOf(year) + '-' + monthString + '-' + String.valueOf(day);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewContents.class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddData();
            }
        });
    }

    public void AddData() {
        /**
         * This function saves data like name, date, and time data using SQLite
         */
        Toast.makeText(MainActivity.this, "" + date,
                Toast.LENGTH_LONG).show();
        int old_hour = timePicker.getHour();
        String hour = String.valueOf(old_hour);
        if (old_hour < 10) {
            hour = "0" + hour;
        }
        int old_minute = timePicker.getMinute();
        String minute = String.valueOf(old_minute);
        if (old_minute < 10) {
            minute = "0" + minute;
        }
        String time = hour + ':' + minute;
        String name = editText.getText().toString();
        boolean insert = dbHelper.addData(name, date, time);
        if (insert) {
            Toast.makeText(MainActivity.this, "Successful data entry",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Unsuccessful data entry",
                    Toast.LENGTH_LONG).show();
        }
    }

}