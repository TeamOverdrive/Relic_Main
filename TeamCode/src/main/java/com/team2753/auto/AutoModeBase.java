package com.team2753.auto;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team2753.Team2753Linear;
import com.team2753.subsystems.Slammer;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;


/**
 * Created by joshua9889 on 3/23/2018.
 */

public abstract class AutoModeBase extends Team2753Linear {

    protected ElapsedTime t = new ElapsedTime();

    public void waitForStart(String OpModeName){
        super.waitForStart(OpModeName, true);

        Robot.getDrive().zeroSensors();
        
        t.reset();
    }

    protected Thread intakeThread = new Thread(new Runnable() {
        @Override
        public void run() {
            sleep(400);
            while(!Thread.interrupted() && opModeIsActive()){
                if(opModeIsActive())
                    Robot.getIntake().setPower(0.8);
                sleep(1000);
                if (opModeIsActive()) {
                    Robot.getIntake().reverse();
                }
                sleep(200);
                Thread.yield();
            }

            if(opModeIsActive())
                Robot.getIntake().setPower(-0.2);
        }
    });

    protected void ScorePreloadReleaseIntake(){
        if(opModeIsActive()) {
            while (opModeIsActive() && !Robot.getSlammer().setStopperState(Slammer.STOPPER_State.OPEN))
                Robot.getIntake().releaseIntake();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    sleep(1000);
                    if (opModeIsActive() && !isStopRequested()) {
                        Robot.getSlammer().setStopperState(Slammer.STOPPER_State.CLOSED);
                        intakeThread.start();
                    }
                }
            }).start();
        }
    }

    protected void SuperHitJewel(Team2753Linear.Jewel_Color scannedJewel, Team2753Linear.Jewel_Color alliance_color){
        if(opModeIsActive()) {
            Robot.getJewel().deploy(true);

            if (alliance_color == Jewel_Color.Red) {
                if (scannedJewel == Jewel_Color.Blue) {
                    sleep(200);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            sleep(1000);
                            Robot.getJewel().retract(true);
                            sleep(300);
                            Robot.getJewel().right();
                        }
                    }).start();
                } else {
                    sleep(300);
                    Robot.getJewel().right();
                    sleep(200);
                    Robot.getJewel().retract(false);
                }
            } else { // Blue
                if (scannedJewel == Jewel_Color.Blue) {
                    sleep(200);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            sleep(1000);
                            Robot.getJewel().retract(false);
                            sleep(300);
                            Robot.getJewel().right();
                        }
                    }).start();
                } else {
                    sleep(300);
                    Robot.getJewel().left();
                    sleep(200);
                    Robot.getJewel().retract(false);
                    sleep(300);
                    Robot.getJewel().right();
                }
            }

        }
    }

    protected void DefaultHitJewel(Team2753Linear.Jewel_Color scannedJewel, Team2753Linear.Jewel_Color alliance_color){
        Robot.getJewel().deploy(true);
        sleep(500);

        if(scannedJewel==alliance_color){
            Robot.getJewel().rightHit();
        } else {
            Robot.getJewel().leftHit();
        }

        sleep(200);
        Robot.getJewel().retract(false);
        sleep(300);
    }

    public RelicRecoveryVuMark WhatColumnToScoreIn(){
        return savedVumark;
    }
}
