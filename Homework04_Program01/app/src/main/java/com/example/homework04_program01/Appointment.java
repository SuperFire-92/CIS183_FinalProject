package com.example.homework04_program01;

public class Appointment
{
    private User handyman;
    private User caller;
    private String job;
    private String date;
    private int callId;

    Appointment()
    {

    }

    Appointment(User h, User c, String j, String d, int id)
    {
        handyman = h;
        caller = c;
        job = j;
        date = d;
        callId = id;
    }

    public User getHandyman() {
        return handyman;
    }

    public void setHandyman(User handyman) {
        this.handyman = handyman;
    }

    public User getCaller() {
        return caller;
    }

    public void setCaller(User caller) {
        this.caller = caller;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }
}
