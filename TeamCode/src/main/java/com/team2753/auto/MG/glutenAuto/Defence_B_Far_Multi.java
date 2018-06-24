package com.team2753.auto.MG.glutenAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.Range;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.HoldMyPaths;
import com.team2753.splines.FollowPath;
import com.team2753.subsystems.Slammer;

import static com.team2753.Constants.WHEEL_BASE;
import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 3/12/2018.
 */

@Autonomous(name = "BlueCloseMTI Far Big Boy Defence",group = "Defence")
@Disabled
public class Defence_B_Far_Multi extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("BFar Defence");
        
        SuperHitJewel(this.jewel_Color, Jewel_Color.Blue);

        Thread calculatePathThread;

        switch (WhatColumnToScoreIn()){
            case LEFT:
                calculatePathThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HoldMyPaths.calculateBlueFarLeftToGlyphPit();
                    }
                });
                calculatePathThread.start();
                calculatePathThread.setPriority(Thread.MAX_PRIORITY);

                Robot.getDrive().encoderDrive(0.4, -24, -24, 5);

                Robot.getDrive().encoderDrive(0.3, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90

                Robot.getDrive().encoderDrive(0.5, -6, -6, 3);

                Robot.getIntake().releaseIntake();

                Robot.getDrive().turnCW(54, 0.3, 3);
                Robot.getDrive().encoderDrive(0.2, 1, 1, 2);

                this.ScorePreloadReleaseIntake();

                new FollowPath(this, Robot.getDrive(), HoldMyPaths.farBlueLeftToGlyphPit, -1, 1);

                sleep(500);

                Robot.getDrive().encoderDrive(0.6, 40, 40, 3);

                sleep(200);

                intakeThread.interrupt();

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                sleep(400);

                Robot.getDrive().encoderDrive(0.2, -4, -4, 1);

                if(t.seconds()<26){
                    Robot.getDrive().encoderDrive(0.3, 3, 3, 2);
                    Robot.getDrive().encoderDrive(0.3, -4, -4, 2);
                }
                break;
            case CENTER:
                calculatePathThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HoldMyPaths.calculateBlueFarCenterToGlyphPit();
                    }
                });
                calculatePathThread.start();
                calculatePathThread.setPriority(Thread.MAX_PRIORITY);

                Robot.getDrive().encoderDrive(0.4, -24, -24, 5);

                Robot.getDrive().encoderDrive(0.3, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90

                Robot.getIntake().releaseIntake();

                Robot.getDrive().encoderDrive(0.5, -13, -13, 3);

                Robot.getDrive().turnCW(54, 0.3, 3);
                Robot.getDrive().encoderDrive(0.2, 1.2, 1.2, 2);

                ScorePreloadReleaseIntake();

                while (opModeIsActive() && HoldMyPaths.farBlueCenterToGlyphPit == null) idle();
                new FollowPath(this, Robot.getDrive(), HoldMyPaths.farBlueCenterToGlyphPit, -1, 1);

                Robot.getDrive().encoderDrive(0.6, 40, 40, 3);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                Robot.getDrive().encoderDrive(0.2, -2, -2, 1);

                if(t.seconds()<26){
                    Robot.getDrive().encoderDrive(0.3, 2, 2, 2);
                    Robot.getDrive().encoderDrive(0.3, -4, -4, 2);
                }
                break;
            case RIGHT: // TODO: CHECK THIS COLUMN
                calculatePathThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HoldMyPaths.calculateBlueFarRightToGlyphPit();
                    }
                });
                calculatePathThread.start();
                calculatePathThread.setPriority(Thread.MAX_PRIORITY);
                Robot.getDrive().encoderDrive(0.4, -24, -24, 5);

                Robot.getDrive().encoderDrive(0.3, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90

                Robot.getDrive().encoderDrive(0.5, -23, -23, 3);

                Robot.getIntake().releaseIntake();

                Robot.getDrive().turnCW(54, 0.3, 3);
                Robot.getDrive().encoderDrive(0.2, 1.2, 1.2, 2);


                ScorePreloadReleaseIntake();

                while (opModeIsActive() && HoldMyPaths.farBlueRightToGlyphPit == null) idle();

                // Drive Backward
                new FollowPath(this, Robot.getDrive(), HoldMyPaths.farBlueRightToGlyphPit, -1, 1);

                Robot.getDrive().encoderDrive(0.6, 40, 40, 3);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                Robot.getDrive().encoderDrive(0.3, -4, -4, 3);

                if(t.seconds()<26){
                    Robot.getDrive().encoderDrive(0.3, 3, 3, 2);
                    Robot.getDrive().encoderDrive(0.3, -4, -4, 2);
                }
                break;
        }

        sleep((long) Range.clip((28000-t.milliseconds()), 0, 30000));
        while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING))idle();
        sleep((long)Range.clip((29500-t.milliseconds()), 0, 30000));
    }
}