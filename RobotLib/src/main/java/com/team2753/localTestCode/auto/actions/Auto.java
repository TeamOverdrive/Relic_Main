package com.team2753.localTestCode.auto.actions;

import com.team2753.localTestCode.util.ElapsedTime;

/**
 * Created by joshua9889 on 4/24/2018.
 */

public class Auto {

    public void runAction(Action action){
        System.out.println("Starting Action: " + action.toString());
        action.start();

        ElapsedTime t = new ElapsedTime();

        while (!action.isFinished() && t.milliseconds()<action.timeoutM){
            action.loop();
            Thread.yield();
        }

        action.stop();
        System.out.println("Finished Running Action after: " + t.milliseconds());
    }
}
