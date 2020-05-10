package com.example.lockdown;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class LockDownModel {
    private String myTask;

    @ServerTimestamp
    private Date date;

    public LockDownModel(String myTask, Date date) {
        this.myTask = myTask;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LockDownModel() {
    }

    public String getMyTask() {
        return myTask;
    }

    public void setMyTask(String myTask) {
        this.myTask = myTask;
    }
}
