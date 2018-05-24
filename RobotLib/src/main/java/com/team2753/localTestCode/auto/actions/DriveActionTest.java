package com.team2753.localTestCode.auto.actions;

/**
 * Created by joshua9889 on 4/24/2018.
 */

public class DriveActionTest extends Action {
    public DriveActionTest(long timeoutM){
        setTimeout(timeoutM);
    }

    @Override
    public void start() {
        System.out.println("Hello");
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void stop() {

    }

    @Override
    public String toString(){
        return "TestDriveAction";
    }
}
