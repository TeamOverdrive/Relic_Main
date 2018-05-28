package com.team2753.splines.team254_2014;

/**
 * Created by joshua9889 on 5/22/2018.
 *
 * Used to store the PDVA terms
 */

public class FollowerConfig {
    private double encoderP;
    private double encoderD;
    private double encoderV;
    private double encoderA;
    private double gyroP;

    public FollowerConfig(double encoderP, double encoderD, double encoderV, double encoderA,
                          double gyroP){

        this.set(encoderP, encoderD, encoderV, encoderA, gyroP);
    }

    public void set(double encoderP, double encoderD, double encoderV, double encoderA,
                      double gyroP){

        this.encoderP = encoderP;
        this.encoderD = encoderD;
        this.encoderV = encoderV;
        this.encoderA = encoderA;
        this.gyroP = gyroP;
    }

    public double[] get(){
        double[] reeeeee = new double[]{
                encoderP, encoderD, encoderV, encoderA,
                gyroP
        };
        return reeeeee;
    }
}
