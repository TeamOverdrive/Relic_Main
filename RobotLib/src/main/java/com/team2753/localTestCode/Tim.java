package com.team2753.localTestCode;

/**
 * Created by joshua9889 on 5/21/2018.
 */

public class Tim {
    public static void main(String[] args) throws InterruptedException {
        double first = System.currentTimeMillis();

        Thread.sleep(1000);

        double dt = System.currentTimeMillis()-first;

        System.out.println(dt);
    }
}
