package com.team2753.auto.MG.normal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team2753.Constants;
import com.team2753.auto.AutoModeBase;
import com.team2753.subsystems.Slammer;

import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous(name = "Red Back", group = "Normal")
public class R_Back_Multi extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("RBack");

        Robot.getRelic().hold();
        SuperHitJewel(this.jewel_Color, Jewel_Color.Red);

        // Clear
        switch (WhatColumnToScoreIn()){
            case RIGHT:
                Robot.getDrive().encoderDrive(0.4, 20, 20, 3);
                Robot.getDrive().encoderDrive(0.3, 0, (Constants.WHEEL_BASE*PI*(90))/180, 3);

                ScorePreloadReleaseIntake();
                sleep(300);
                Robot.getDrive().setLeftRightForTime(0.3, 0.3, 400);

                Robot.getDrive().encoderDrive(0.7, -4, -4, 3);

                Robot.getDrive().encoderDrive(0.2, -31, -31, 6);
                sleep(400);
                
                Robot.getDrive().encoderDrive(0.4, (Constants.WHEEL_BASE*PI*(14))/180, 0, 3);
                Robot.getDrive().encoderDrive(0.4, 34, 34, 4);

                intakeThread.interrupt();
                sleep(100);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) {
                    Robot.getIntake().setPower(0);
                    idle();
                }
                sleep(1000);

                Robot.getDrive().encoderDrive(0.5, -5, -5, 2);

                if(30-t.seconds()>=4){
                    Robot.getDrive().setLeftRightForTime(0.5, 0.5, 600);
                    Robot.getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;

            case CENTER:
                Robot.getDrive().encoderDrive(0.6, 20, 22, 4);
                Robot.getDrive().encoderDrive(0.6, 0, (Constants.WHEEL_BASE*PI*(57))/180, 3);
                Robot.getDrive().encoderDrive(0.6, 5, 5, 3);

                ScorePreloadReleaseIntake();

                Robot.getDrive().encoderDrive(0.6, -(Constants.WHEEL_BASE*PI*(90-57))/180, 0, 3);
                Robot.getDrive().encoderDrive(0.6, -5, -5, 2);

                Robot.getDrive().encoderDrive(0.3, -35, -35, 3);

                sleep(400);

                Robot.getDrive().encoderDrive(0.4, 0, (Constants.WHEEL_BASE*PI*(-3))/180, 3);
                Robot.getDrive().encoderDrive(0.4, 39, 39, 4);

                intakeThread.interrupt();
                sleep(100);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();
                sleep(500);
                Robot.getDrive().encoderDrive(0.5, -5, -5, 2);

                if(30-t.seconds()>=4){
                    Robot.getDrive().encoderDrive(1, 4, 4, 2);
                    Robot.getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;

            case LEFT:
                Robot.getDrive().encoderDrive(0.4, 27, 27, 4);
                Robot.getDrive().encoderDrive(0.4, 0, (Constants.WHEEL_BASE*PI*(53))/180, 3);
                Robot.getDrive().encoderDrive(0.4, 7, 7, 3);
                ScorePreloadReleaseIntake();
                Robot.getDrive().encoderDrive(0.6, -(Constants.WHEEL_BASE*PI*(90-53))/180, 0, 3);

                Robot.getDrive().encoderDrive(0.6, -5, -5, 2);
                Robot.getDrive().encoderDrive(0.3, -20, -20, 3);
                Robot.getDrive().encoderDrive(0.2, -5, -5, 2);

                Robot.getDrive().encoderDrive(0.4, 0, (Constants.WHEEL_BASE*PI*(14))/180, 3);
                Robot.getDrive().encoderDrive(0.4, 35, 35, 4);

                intakeThread.interrupt();
                sleep(100);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();
                sleep(500);
                Robot.getDrive().encoderDrive(0.5, -5, -5, 2);

                if(30-t.seconds()>=4){
                    Robot.getDrive().encoderDrive(1, 4, 4, 2);
                    Robot.getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;
        }


        sleep((long) (28000-t.milliseconds()));
        while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING))idle();
        sleep((long) (29500-t.milliseconds()));
    }
}