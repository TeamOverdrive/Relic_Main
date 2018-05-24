package com.team2753.localTestCode.control;

/**
 * Created by joshua9889 on 5/22/2018.
 */

public class RunnableTest {
    public static void main(String[] args){
        RunnableTest runnableTest = new RunnableTest();

        double start = System.currentTimeMillis();
        double elasped = 0;
        while (elasped<1000){
            double current = System.currentTimeMillis();
            elasped = current - start;
            runnableTest.calculate(elasped);
            System.out.println("Input: " + elasped + "| Output: " + runnableTest.currentSpeed);
        }
    }
    double currentSpeed = 0.0;

    double currentPosition = 0.0;
    double lastPosition = 0.0;

    long currentTime = 0;
    long lastTime = 0;

    boolean first = true;

    public void calculate(double position){
        if(!first){
            currentPosition = position;
            currentTime = System.nanoTime();

            double dPosition = currentPosition - lastPosition; //Ticks
            double dt = currentTime - lastTime; // Nanoseconds
            System.out.println("dP:" + dPosition +" | DT: " + dt);
            currentSpeed = dPosition / dt;
        }

        lastPosition = currentPosition;
        lastTime = currentTime;
        first = false;
    }
}
