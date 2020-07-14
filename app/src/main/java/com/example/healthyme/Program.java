package com.example.healthyme;

public class Program {

    private String mprogram;
    private String illness;
    private String dateS;
    private String dateE;
    public Program() {
        //public no-arg constructor needed
    }
    public Program(String mprogram, String illness, String dateS, String dateE) {
        this.mprogram = mprogram;
        this.illness = illness;
        this.dateS = dateS;
        this.dateE = dateE;
    }
}
