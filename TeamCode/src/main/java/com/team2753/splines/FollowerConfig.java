package com.team2753.splines;

/**
 * Created by joshua9889 on 5/22/2018.
 */

public class FollowerConfig {
    private double encoderP;
    private double encoderI;
    private double encoderD;
    private double encoderV;
    private double encoderA;
    private double gyroP;

    public FollowerConfig(double encoderP, double encoderI, double encoderD, double encoderV, double encoderA,
                          double gyroP){

        this.encoderP = encoderP;
        this.encoderI = encoderI;
        this.encoderD = encoderD;
        this.encoderV = encoderV;
        this.encoderA = encoderA;
        this.gyroP = gyroP;
    }

    public double[] get(){
        double[] reeeeee = new double[]{
                encoderP, encoderI, encoderD, encoderV, encoderA,
                gyroP
        };
        return reeeeee;
    }
}
