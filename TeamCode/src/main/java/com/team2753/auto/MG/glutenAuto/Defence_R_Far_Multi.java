package com.team2753.auto.MG.glutenAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;
import com.team2753.auto.HoldMyPaths;
import com.team2753.splines.FollowPath;
import com.team2753.subsystems.Slammer;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous(name = "Red Far Big Boy Defence",group = "Defence")
@Disabled
public class Defence_R_Far_Multi extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        HoldMyPaths.calculateRedRightToGlyphPit();

        waitForStart("RFar Defence", AutoParams.AUTO);
        Robot.getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();

        SuperHitJewel(this.jewel_Color, Jewel_Color.Red);

        switch (RelicRecoveryVuMark.LEFT){
            case LEFT:
                // Drive off Stone
                Robot.getDrive().encoderDrive(0.4, 30, 30, 3);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3);

                // Drive 19 in
                Robot.getDrive().encoderDrive(0.5, -19, -19, 3);

                // Turn 90
                Robot.getDrive().turnCCW(90, 0.4, 3);

                ScorePreloadReleaseIntake();

                sleep(300);
                Robot.getDrive().setLeftRightForTime(0.3, 0.3, 400);

                // Drive Backward
                Robot.getDrive().encoderDrive(0.6, -10, -10, 3);

                Robot.getDrive().turnCW(28, 0.5, 2);

                double distanceLeftChange = 20;
                Robot.getDrive().driveTrajectory(-36-distanceLeftChange,
                        -36-distanceLeftChange, 6 * 1000);
                sleep(200);

                Robot.getDrive().turnCW(6, 0.3, 2);

                Robot.getDrive().driveTrajectory(48+distanceLeftChange,
                        48+distanceLeftChange, 5 * 1000);

                while (opModeIsActive() &&
                        !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();

                while(opModeIsActive() &&
                        !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                sleep((long)Range.clip((28000-t.milliseconds()), 0, 30000));
                Robot.getDrive().setLeftRightForTime(-0.5, -0.5, 400);

                break;
            case CENTER:
                // Drive off
                Robot.getDrive().encoderDrive(0.6, 31, 31, 3);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 2);

                // Drive 17 in
                Robot.getDrive().encoderDrive(0.5, -17, -17, 2.5);

                // Turn 54
                Robot.getDrive().turnCCW(54, 0.4, 3);

                ScorePreloadReleaseIntake();

                // Drive Backward
                Robot.getDrive().encoderDrive(0.6, -5, -5, 3);

                Robot.getDrive().turnCCW(13, 0.5, 2);

                Robot.getDrive().encoderDrive(0.5, -25, -25, 5);
                sleep(200);

                Robot.getDrive().encoderDrive(0.6, 35, 35, 3);

                while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING) && opModeIsActive()) idle();

                Robot.getIntake().reverse();

                sleep(300);

                while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                Robot.getDrive().encoderDrive(0.3, -6, -6, 3);
                Robot.getDrive().encoderDrive(0.4, 6, 6, 3);
                Robot.getDrive().encoderDrive(0.3, -5, -5 , 3);

                break;
            case RIGHT:
                // Drive off
                Robot.getDrive().encoderDrive(0.6, 31, 31, 3);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 2);

                // Drive 11 in
                Robot.getDrive().encoderDrive(0.5, -11, -11, 2.5);

                // Turn 54
                Robot.getDrive().turnCCW(54, 0.4, 3);

                ScorePreloadReleaseIntake();

                new FollowPath(this, Robot.getDrive(), HoldMyPaths.farRedRightToGlyphPit, -1, 1);

                Robot.getDrive().encoderDrive(1, 35, 35, 4);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.HOLDING))idle();
                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                Robot.getDrive().encoderDrive(0.3, -6, -6, 3);

                break;
        }

        sleep((long) Range.clip((28000-t.milliseconds()), 0, 30000));
        while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING))idle();
        sleep((long)Range.clip((29500-t.milliseconds()), 0, 30000));
    }
}
