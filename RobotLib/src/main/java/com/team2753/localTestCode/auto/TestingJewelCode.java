package com.team2753.localTestCode.auto;

import com.team2753.localTestCode.util.ElapsedTime;

/**
 * Created by joshua9889 on 4/23/2018.
 */

public class TestingJewelCode {
    public static void main(String[] args){
        ElapsedTime time = new ElapsedTime();

        boolean Red = true;
        boolean Blue = false;

        boolean ScannedJewel = Blue;
        boolean AllianceColor = Red;

        while (!(time.milliseconds()>1000)){
            if (time.milliseconds()<500)
                deploy(true);
            else if(time.milliseconds()<700){
                if(ScannedJewel==AllianceColor){
                    right();
                } else {
                    left();
                }
            } else {
                retract(false);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void right(){
        System.out.println("Right");
    }

    public static void left(){
        System.out.println("Left");
    }

    public static void deploy(boolean center){
        System.out.println("Deploy");
    }

    public static void retract(boolean center){
        System.out.println("retract");
    }
}
