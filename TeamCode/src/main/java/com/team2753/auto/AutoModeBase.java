package com.team2753.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.team2753.Team2753Linear;
import com.team2753.subsystems.Slammer;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static com.team2753.Constants.COUNTS_PER_INCH;
import static com.team2753.auto.AutoParams.autoSpeed;
import static com.team2753.auto.AutoParams.autoTurnSpeed;


/**
 * Created by joshua9889 on 3/23/2018.
 */

public abstract class AutoModeBase extends Team2753Linear {

    protected Thread intakeThread = new Thread(new Runnable() {
        @Override
        public void run() {
            sleep(400);
            while(!Thread.interrupted()){
                Robot.getIntake().setLeftRightPower(1.0, 1.0);
                sleep(1500);
                Robot.getIntake().setLeftRightPower(-1, -1);
                sleep(200);
                Thread.yield();
            }
        }
    });

    protected void ScorePreloadReleaseIntake(){
        while (opModeIsActive() && !Robot.getSlammer().setStopperState(Slammer.STOPPER_State.OPEN))
            Robot.getIntake().releaseIntake();

        sleep(40);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(1000);
                Robot.getSlammer().setStopperState(Slammer.STOPPER_State.CLOSED);
                intakeThread.start();
            }
        }).start();
    }

    protected void SuperHitJewel(Team2753Linear.Jewel_Color scannedJewel, Team2753Linear.Jewel_Color alliance_color){
        Robot.getJewel().deploy(true);

        if(alliance_color == Jewel_Color.Red){
            if(scannedJewel==Jewel_Color.Blue) {
                sleep(200);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sleep(1000);
                        Robot.getJewel().retract(true);
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
                        Robot.getJewel().retract(true);
                    }
                }).start();
            } else {
                sleep(300);
                Robot.getJewel().left();
                sleep(200);
                Robot.getJewel().retract(false);
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
