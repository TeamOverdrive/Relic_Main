package com.team2753.auto.MG.glutenAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.Range;
import com.team2753.Constants;
import com.team2753.auto.AutoModeBase;
import com.team2753.subsystems.Slammer;

import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous(name = "Blue Back Big Boy Defence",group = "Defence")
@Disabled
public class Defence_B_Back_Multi extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("BBack Defence");

        SuperHitJewel(this.jewel_Color, Jewel_Color.Blue);

        switch (WhatColumnToScoreIn()){
            case LEFT:
                Robot.getDrive().encoderDrive(0.3, -36, -36, 4);

                Robot.getDrive().turnCW(67, 0.2, 4);

                Robot.getIntake().releaseIntake();

                Robot.getDrive().encoderDrive(0.3, 7, 7, 3);

                ScorePreloadReleaseIntake();

                Robot.getDrive().encoderDrive(0.2, (Constants.WHEEL_BASE*PI*(67-90))/180, 0, 4);
                sleep(500);
                Robot.getDrive().encoderDrive(0.4, -5, -5, 3);
                sleep(300);

                Robot.getDrive().driveTrajectory(-29, -29, 7 * 1000);
                sleep(400);

                Robot.getDrive().encoderDrive(0.3, 0, (Constants.WHEEL_BASE*PI*(10.4))/180, 3);

                intakeThread.interrupt();

                Robot.getDrive().driveTrajectory(36, 36, 70000);
                sleep(100);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();
                sleep(400);

                Robot.getDrive().encoderDrive(0.4, -5, -5, 2);

                if(30-t.seconds()>=4){
                    Robot.getDrive().encoderDrive(0.4, 4, 4, 2);

                    Robot.getDrive().setLeftRightForTime(-1, -1, 100);
                }
                break;
            case CENTER:
                Robot.getDrive().encoderDrive(0.3, -35, -35, 4);

                Robot.getDrive().turnCW(90, 0.2, 4);

                Robot.getIntake().releaseIntake();

                Robot.getDrive().encoderDrive(0.3, 7, 7, 3);

                ScorePreloadReleaseIntake();

                Robot.getDrive().encoderDrive(0.4, -5, -5, 3);
                sleep(300);

                Robot.getDrive().driveTrajectory(-29, -29, 7 * 1000);
                sleep(400);

                Robot.getDrive().encoderDrive(0.3, 34.5, 34.5, 3);

                intakeThread.interrupt();
                sleep(100);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();
                sleep(400);

                Robot.getDrive().encoderDrive(0.4, -5, -5, 2);

                if(30-t.seconds()>=4){
                    Robot.getDrive().encoderDrive(0.4, 4, 4, 2);

                    Robot.getDrive().setLeftRightForTime(-1, -1, 100);
                }
                break;
            case RIGHT:
                Robot.getDrive().encoderDrive(0.3, -36, -36, 4);

                Robot.getDrive().turnCW(113, 0.2, 5);

                Robot.getIntake().releaseIntake();

                Robot.getDrive().encoderDrive(0.3, 7, 7, 3);

                ScorePreloadReleaseIntake();

                Robot.getDrive().encoderDrive(0.2, 0, (Constants.WHEEL_BASE*PI*(90-113))/180, 4);
                sleep(500);
                Robot.getDrive().encoderDrive(0.4, -5, -5, 3);
                sleep(300);

                Robot.getDrive().driveTrajectory(-29, -29, 7 * 1000);
                sleep(400);

                Robot.getDrive().encoderDrive(0.3, (Constants.WHEEL_BASE*PI*(12.5))/180, 0, 3);
                Robot.getDrive().driveTrajectory(37, 37, 7 * 1000);

                intakeThread.interrupt();
                sleep(100);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                sleep((long)Range.clip((28000-t.milliseconds()), 0, 30000));
                Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 400);

                break;
        }

        sleep((long)Range.clip((29000-t.milliseconds()), 0, 30000));
        while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING))idle();
        sleep((long)Range.clip((29500-t.milliseconds()), 0, 30000));
    }
}
