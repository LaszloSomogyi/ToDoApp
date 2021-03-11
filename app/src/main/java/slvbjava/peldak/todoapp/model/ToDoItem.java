package slvbjava.peldak.todoapp.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.Date;

public class ToDoItem implements Serializable {

    private int id;
    private String name;
    private String details;
    private int year;
    private int month;
    private int day;
    private int priority;

    public ToDoItem() { id = -1;}


    public ToDoItem(String name, String details, int year, int month, int day, int priority) {
        id = -1;
        this.name = name;
        this.details = details;
        this.year = year;
        this.month = month;
        this.day = day;
        this.priority = priority;
    }

    public ToDoItem(int id, String name, String details, int year, int month, int day, int priority) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.year = year;
        this.month = month;
        this.day = day;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() { return details; }

    public void setDetails(String details) { this.details = details; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public int getMonth() { return month; }

    public void setMonth(int month) { this.month = month; }

    public int getDay() { return day; }

    public void setDay(int day) { this.day = day; }

    public int getPriority() { return priority; }

    public void setPriority(int priority) { this.priority = priority; }

}
