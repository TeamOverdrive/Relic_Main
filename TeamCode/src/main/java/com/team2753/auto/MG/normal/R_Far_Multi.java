package com.team2753.auto.MG.normal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;
import com.team2753.auto.HoldMyPaths;
import com.team2753.splines.FollowPath;
import com.team2753.subsystems.Slammer;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static com.team2753.Constants.WHEEL_BASE;
import static com.team2753.Constants.defaultTrajectoryConfig;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous(name = "Red Far Test",group = "Normal")
public class R_Far_Multi extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        HoldMyPaths.calculateRedRightToGlyphPit();

        waitForStart("RFar", AutoParams.AUTO);
        Robot.getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        Robot.getRelic().hold();

        SuperHitJewel(this.jewel_Color, Jewel_Color.Red);

        switch (WhatColumnToScoreIn()){
            case LEFT:
                // Drive off Stone
                Robot.getDrive().encoderDrive(0.4, 30, 30, 3, this);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3.5);

                // Drive 19 in
                Robot.getDrive().encoderDrive(0.5, -19, -19, 3, this);

                // Turn 90
                Robot.getDrive().turnCCW(90, 0.4, 3);

                ScorePreloadReleaseIntake();

                sleep(300);
                Robot.getDrive().setLeftRightForTime(0.3, 0.3, 400);

                // Drive Backward
                Robot.getDrive().encoderDrive(0.6, -10, -10, 3);

                Robot.getDrive().turnCW(28, 0.5, 2);

                Robot.getDrive().driveTrajectory(-36, -36, 6 * 1000);
                sleep(200);

                Robot.getDrive().turnCW(6, 0.3, 2);

                Robot.getDrive().driveTrajectory(48, 48, 5 * 1000);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while(opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                sleep((long)Range.clip((28000-t.milliseconds()), 0, 30000));
                Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 400);

                break;
            case CENTER:
                // Drive off
                Robot.getDrive().encoderDrive(0.3, 31, 31, 3, this);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3.5);

                // Drive 17 in
                Robot.getDrive().encoderDrive(0.5, -17, -17, 3, this);

                // Turn 54
                Robot.getDrive().turnCCW(54, 0.4, 3);

                ScorePreloadReleaseIntake();

                // Drive Backward
                Robot.getDrive().encoderDrive(0.6, -5, -5, 3);

                Robot.getDrive().turnCCW(13, 0.5, 2);

                Robot.getDrive().driveTrajectory(-30, -30, 5*1000);
                sleep(200);

                Robot.getDrive().driveTrajectory(37, 37, 5*1000);

                while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING) && opModeIsActive()) idle();

                Robot.getIntake().reverse();

                sleep(300);

                while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                Robot.getDrive().encoderDrive(0.3, -6, -6, 3);
                Robot.getDrive().setLeftRightForTime(0.5, 0.5, 800);
                Robot.getDrive().encoderDrive(0.3, -5, -5 , 3);

                break;
            case RIGHT:
                // Drive off
                Robot.getDrive().encoderDrive(0.6, 31, 31, 3, this  );

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3.5);

                // Drive 11 in
                Robot.getDrive().encoderDrive(0.5, -11, -11, 2.5);

                // Turn 54
                Robot.getDrive().turnCCW(54, 0.4, 3);

                ScorePreloadReleaseIntake();

                new FollowPath(this, Robot.getDrive(), HoldMyPaths.farRedRightToGlyphPit, -1, 1);

                sleep(400);

                Robot.getDrive().turnCW(-10, 0.4, 3);

                Robot.getDrive().driveTrajectory(40, 40, 4*1000);
                sleep(200);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                sleep(300);
                Robot.getDrive().encoderDrive(0.3, -3, -3, 3);
                Robot.getDrive().setLeftRightForTime(0.5, 0.5, 1000);
                Robot.getDrive().setLeftRightForTime(-0.3, -0.3, 500);

                break;
        }

        sleep((long) Range.clip((28000-t.milliseconds()), 0, 30000));
        while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING))idle();
        sleep((long)Range.clip((29500-t.milliseconds()), 0, 30000));
    }
}
