package com.example.prescribed.addMed;

import java.util.Date;

public class Medication {

    private String name;
    private Date date;
    private int times;
    private Frequency freq;
    private String notes;

    public Medication(String name, Date date, int times, Frequency freq, String notes){
        this.name = name;
        this.date = date;
        this.times = times;
        this.freq = freq;
        this.notes = notes;
    }

    public String getName(){
        return name;
    }

    public Date getDate(){
        return (Date)date.clone();
    }

    public int getTimes(){
        return times;
    }

    public Frequency getFrequency(){
        return freq;
    }

    public String getNotes(){
        return notes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setTimes(int times){
        this.times = times;
    }

    public void setFreq(Frequency freq){
        this.freq = freq;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }
}
