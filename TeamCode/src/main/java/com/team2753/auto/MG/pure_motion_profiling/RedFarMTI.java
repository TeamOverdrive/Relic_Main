package com.team2753.auto.MG.pure_motion_profiling;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;
import com.team2753.auto.HoldMyPaths;
import com.team2753.splines.FollowPath;
import com.team2753.subsystems.Slammer;

@Autonomous(group = "MTI")
public class RedFarMTI extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        HoldMyPaths.calculateRedRightToGlyphPit();

        waitForStart("RFar", AutoParams.AUTO);
        Robot.getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        Robot.getRelic().hold();

        DefaultHitJewel(this.jewel_Color, Jewel_Color.Red);

        switch (WhatColumnToScoreIn()){
            case LEFT:
                // Drive off Stone
                Robot.getDrive().driveTrajectory(30, 30, 3*1000);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3.5);

                // Drive 19 in
                Robot.getDrive().driveTrajectory(19, 19, 3000);

                // Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3.5);

                ScorePreloadReleaseIntake();

                sleep(300);
                Robot.getDrive().setLeftRightForTime(0.3, 0.3, 400);

                // Drive Backward
                Robot.getDrive().driveTrajectory(10, 10, 3000);

                Robot.getDrive().turnCW(28, 0.5, 2);

                Robot.getDrive().driveTrajectory(-36, -36, 6 * 1000);
                sleep(200);

                Robot.getDrive().turnCW(6, 0.3, 2);

                Robot.getDrive().driveTrajectory(48, 48, 5 * 1000);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while(opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                sleep((long) Range.clip((28000-t.milliseconds()), 0, 30000));
                Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 400);

                break;
            case CENTER:
                // Drive off
                Robot.getDrive().driveTrajectory(31, 31, 3);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3.5);

                // Drive 17 in
                Robot.getDrive().driveTrajectory(17, 17, 3*1000);

                // Turn 54
                Robot.getDrive().turnTrajectory(54, 5*1000);

                ScorePreloadReleaseIntake();

                // Drive Backward
                Robot.getDrive().driveTrajectory(-5, -5, 3*1000);

                Robot.getDrive().turnCCW(13, 0.5, 2);

                Robot.getDrive().driveTrajectory(-30, -30, 5*1000);
                sleep(200);

                Robot.getDrive().driveTrajectory(37, 37, 5*1000);

                while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING) && opModeIsActive()) idle();

                Robot.getIntake().reverse();

                sleep(300);

                while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                Robot.getDrive().setLeftRightForTime(-0.4, -0.4, 2);
                Robot.getDrive().setLeftRightForTime(0.5, 0.5, 800);
                Robot.getDrive().setLeftRightForTime(-0.4, -0.4, 500);

                break;
            case RIGHT:
                // Drive off
                Robot.getDrive().driveTrajectory(31, 31, 3*1000);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3.5);

                // Drive 11 in
                Robot.getDrive().driveTrajectory(11, 11, 2500);

                // Turn 54
                Robot.getDrive().turnCCW(54, 0.4, 3);

                ScorePreloadReleaseIntake();

                new FollowPath(this, Robot.getDrive(), HoldMyPaths.farRedRightToGlyphPit, 6000, -1, 1);

                sleep(400);

                Robot.getDrive().turnCW(-10, 0.4, 3);

                Robot.getDrive().driveTrajectory(40, 40, 4*1000);
                sleep(200);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                sleep(300);
                Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 1000);
                Robot.getDrive().setLeftRightForTime(0.5, 0.5, 1000);
                Robot.getDrive().setLeftRightForTime(-0.3, -0.3, 500);

                break;
        }

        sleep((long) Range.clip((28000-t.milliseconds()), 0, 30000));
        while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING))idle();
        sleep((long)Range.clip((29500-t.milliseconds()), 0, 30000));
    }
}
