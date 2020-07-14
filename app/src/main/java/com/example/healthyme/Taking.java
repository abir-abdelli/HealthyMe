package com.example.healthyme;

public class Taking {

    private String medication;
    private String notes;
    private String date;
    public Taking() {
        //public no-arg constructor needed
    }
    public Taking(String medication, String notes, String date) {
        this.medication = medication;
        this.notes = notes;
        this.date = date;
    }
}
