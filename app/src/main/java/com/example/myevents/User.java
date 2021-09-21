package com.example.myevents;

/**
 * this class defines an event with data to be stored in an arraylist
 */
public class User {
    private String name;
    private String date;
    private String time;

    /**
     * assigns relevant data to variables
     * @param name name of event
     * @param date date of event
     * @param time hour and minute of event
     */
    public User(String name, String date, String time){
        this.name = name;
        this.date = date;
        this.time = time;
        String newTime = this.time.substring(0, 2) + ':' + this.time.substring(2);
        this.time = newTime;
    }

    /**
     * @return name of event
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return date of event
     */
    public String getDate() {
        return this.date;
    }

    /**
     * @return hour and minute of event
     */
    public String getTime() {
        return this.time;
    }
}
