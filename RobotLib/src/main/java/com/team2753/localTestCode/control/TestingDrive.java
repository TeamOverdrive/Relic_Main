package com.team2753.localTestCode.control;

/**
 * Created by heinr_000 on 5/19/2018.
 */

public class TestingDrive {
    public static void main(String[] args){
        double max_vel = 0.6;

        double input = 0.7;
        double sign = -1;

        double output = Math.copySign(Math.min(Math.max(input, 0), max_vel), sign);

        System.out.println(output);
    }
}
