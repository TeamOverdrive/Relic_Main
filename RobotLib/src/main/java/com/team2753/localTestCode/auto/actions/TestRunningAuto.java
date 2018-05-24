package com.team2753.localTestCode.auto.actions;

/**
 * Created by joshua9889 on 4/24/2018.
 */

public class TestRunningAuto extends Auto {
    public static void main(String[] args){
        Auto auto = new Auto();

        auto.runAction(new DriveActionTest(1000));
    }
}
