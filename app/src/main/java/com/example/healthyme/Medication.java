package com.example.healthyme;

public class Medication {

    private String mprogram;
    private String drugName;
    private String description;
    private String date;
    public Medication() {
        //public no-arg constructor needed
    }
    public Medication(String mprogram, String drugName, String description, String date) {
        this.mprogram = mprogram;
        this.drugName = drugName;
        this.description = description;
        this.date = date;
    }
}
