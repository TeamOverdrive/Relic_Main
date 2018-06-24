package com.team2753.auto.MG.pure_motion_profiling;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.Range;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.HoldMyPaths;
import com.team2753.splines.FollowPath;
import com.team2753.subsystems.Slammer;

import static com.team2753.Constants.WHEEL_BASE;
import static java.lang.Math.PI;

@Autonomous(group = "MTI")
public class BlueFarMTI extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        HoldMyPaths.calculateBlueFarLeftToGlyphPit();
        waitForStart("BFar");

        Robot.getRelic().hold();
        DefaultHitJewel(this.jewel_Color, Jewel_Color.Blue);

        Thread calculatePathThread;

        switch (WhatColumnToScoreIn()){
            case LEFT:
                Robot.getDrive().driveTrajectory(-24, -24, 5000);

                Robot.getDrive().encoderDrive(0.3, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90

                Robot.getDrive().driveTrajectory(-6, -6, 3000);

                Robot.getIntake().releaseIntake();

                Robot.getDrive().turnCCW(-54, 0.3, 3);
                Robot.getDrive().driveTrajectory(1, 1, 2000);

                this.ScorePreloadReleaseIntake();

//                Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 400);
//                Robot.getDrive().setLeftRightForTime(0.5, 0.5, 300);
//                Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 300);

                //new FollowPath(this, Robot.getDrive(), HoldMyPaths.farBlueLeftToGlyphPit, 6000, -1, 1);

//                sleep(500);
//
//                Robot.getDrive().driveTrajectory(40, 40, 3);
//
//                sleep(200);
//
//                intakeThread.interrupt();
//
//                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
//                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();
//
//                sleep(400);
//
//                Robot.getDrive().setLeftRightForTime(-0.6, -0.6, 500);
//
//                if(t.seconds()<26){
//                    Robot.getDrive().setLeftRightForTime(0.5, 0.5, 500);
//                    Robot.getDrive().setLeftRightForTime(-0.4, -0.4, 500);
//                }
                break;
            case CENTER:
//                calculatePathThread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        HoldMyPaths.calculateBlueFarCenterToGlyphPit();
//                    }
//                });
//                calculatePathThread.start();
//                calculatePathThread.setPriority(Thread.MAX_PRIORITY);

                Robot.getDrive().driveTrajectory(-24, -24, 5000);

                Robot.getDrive().encoderDrive(0.4, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90

                Robot.getIntake().releaseIntake();

                Robot.getDrive().driveTrajectory( -12.6, -12.6, 3500);

                Robot.getDrive().turnCCW(-54, 0.4, 3);
                Robot.getDrive().driveTrajectory(1.2, 1.2, 2000);

                ScorePreloadReleaseIntake();

//                Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 400);
//                Robot.getDrive().setLeftRightForTime(0.5, 0.5, 300);
//                Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 300);

//                while (opModeIsActive() && HoldMyPaths.farBlueCenterToGlyphPit == null) idle();
//                new FollowPath(this, Robot.getDrive(), HoldMyPaths.farBlueCenterToGlyphPit, 6000, -1, 1);
//                Robot.getDrive().turnCCW(-3, 0.4, 3);
//
//                Robot.getDrive().driveTrajectory(40, 40, 5000);
//
//                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
//                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();
//
//                Robot.getDrive().driveTrajectory(-4, -4, 3000);
//
//                if(t.seconds()<26){
//                    Robot.getDrive().setLeftRightForTime(0.5, 0.5, 600);
//                    Robot.getDrive().setLeftRightForTime(-0.3, -0.3, 500);
//                }
                break;
            case RIGHT:
//                calculatePathThread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        HoldMyPaths.calculateBlueFarRightToGlyphPit();
//                    }
//                });
//                calculatePathThread.start();
//                calculatePathThread.setPriority(Thread.MAX_PRIORITY);
                Robot.getDrive().driveTrajectory( -24, -24, 5000);

                Robot.getDrive().encoderDrive(0.3, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90

                Robot.getDrive().driveTrajectory(-23, -23, 3000);

                Robot.getIntake().releaseIntake();

                Robot.getDrive().turnCCW(-54,0.4, 3);
                Robot.getDrive().driveTrajectory(1.2, 1.2, 1500);


                ScorePreloadReleaseIntake();

//                Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 400);
//                Robot.getDrive().setLeftRightForTime(0.5, 0.5, 300);
//                Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 300);

//                while (opModeIsActive() && HoldMyPaths.farBlueRightToGlyphPit == null) idle();
//
//                 Drive Backward
//                new FollowPath(this, Robot.getDrive(), HoldMyPaths.farBlueRightToGlyphPit, 6000, 1, 1);
//                Robot.getDrive().driveTrajectory(-6, -6, 4*1000);
//                sleep(500);
//
//                Robot.getDrive().driveTrajectory(40, 40, 3750);
//
//                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
//                while (opModeIsActive() && Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();
//
//                Robot.getDrive().driveTrajectory(-4, -4, 3000);
//
//                if(t.seconds()<26){
//                    Robot.getDrive().setLeftRightForTime(0.5, 0.5, 600);
//                    Robot.getDrive().setLeftRightForTime(-0.3, -0.3, 600);
//                }
                break;
        }

        sleep((long) Range.clip((28000-t.milliseconds()), 0, 30000));
        while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING))idle();
        sleep((long)Range.clip((29500-t.milliseconds()), 0, 30000));
    }

}
