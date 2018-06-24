package com.team2753.auto.MG.pure_motion_profiling;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team2753.Constants;
import com.team2753.auto.AutoModeBase;
import com.team2753.subsystems.Slammer;

import static java.lang.Math.PI;

@Autonomous(group = "MIT")
public class RedCloseMTI extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("RBack");

        Robot.getRelic().hold();
        DefaultHitJewel(this.jewel_Color, Jewel_Color.Red);

        // Clear
        switch (WhatColumnToScoreIn()){
            case RIGHT:
                Robot.getDrive().driveTrajectory(20, 20, 3000);
                Robot.getDrive().encoderDrive(0.3, 0, (Constants.WHEEL_BASE*PI*(90))/180, 3);

                ScorePreloadReleaseIntake();
                sleep(300);
                Robot.getDrive().setLeftRightForTime(0.3, 0.3, 400);

                Robot.getDrive().driveTrajectory(-4, -4, 3000);

                Robot.getDrive().driveTrajectory(-31, -31, 6000);
                sleep(400);

                Robot.getDrive().encoderDrive(0.3, Constants.WHEEL_BASE*PI*(14)/180, 0, 3);
                Robot.getDrive().driveTrajectory(34, 34, 4000);

                intakeThread.interrupt();
                sleep(100);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) {
                    Robot.getIntake().setPower(0);
                    idle();
                }
                sleep(1000);

                Robot.getDrive().driveTrajectory(-5, -5, 2500);

                if(30-t.seconds()>=4){
                    Robot.getDrive().setLeftRightForTime(0.5, 0.5, 600);
                    Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 700);
                }
                break;

            case CENTER:
                Robot.getDrive().driveTrajectory(20, 22, 4000);
                Robot.getDrive().encoderDrive(0.3, 0, (Constants.WHEEL_BASE*PI*(57))/180, 3);
                Robot.getDrive().driveTrajectory(5, 5, 3000);

                ScorePreloadReleaseIntake();

                Robot.getDrive().encoderDrive(0.3, -(Constants.WHEEL_BASE*PI*(90-57))/180, 0, 3);
                Robot.getDrive().driveTrajectory(-40, -40, 5000);

                sleep(400);

                Robot.getDrive().encoderDrive(0.3, 0, (Constants.WHEEL_BASE*PI*(-3))/180, 3);
                Robot.getDrive().driveTrajectory(39, 39, 4500);

                intakeThread.interrupt();
                sleep(100);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();
                sleep(500);
                Robot.getDrive().driveTrajectory(-5, -5, 2000);

                if(30-t.seconds()>=4){
                    Robot.getDrive().setLeftRightForTime(0.7, 0.7, 750);
                    Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 400);
                }
                break;

            case LEFT:
                Robot.getDrive().driveTrajectory(27, 27, 4000);
                Robot.getDrive().encoderDrive(0.3, 0, (Constants.WHEEL_BASE*PI*(53))/180, 3);
                Robot.getDrive().driveTrajectory(7, 7, 3000);
                ScorePreloadReleaseIntake();
                Robot.getDrive().encoderDrive(0.3, -(Constants.WHEEL_BASE*PI*(90-53))/180, 0, 3);

                Robot.getDrive().driveTrajectory(-5, -5, 2000);
                Robot.getDrive().driveTrajectory(-20, -20, 4000);
                Robot.getDrive().driveTrajectory(-5, -5, 2000);

                Robot.getDrive().encoderDrive(0.3, 0, (Constants.WHEEL_BASE*PI*(14))/180, 3);
                Robot.getDrive().driveTrajectory(35, 35, 4);

                intakeThread.interrupt();
                sleep(100);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();
                sleep(500);
                Robot.getDrive().setLeftRightForTime(0.5, 0.5, 500);

                if(30-t.seconds()>=4){
                    Robot.getDrive().setLeftRightForTime(0.4, 0.4, 400);
                    Robot.getDrive().setLeftRightForTime(-0.6, -0.6, 400);
                }
                break;
        }


        sleep((long) (28000-t.milliseconds()));
        while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING))idle();
        sleep((long) (29500-t.milliseconds()));
    }
}
