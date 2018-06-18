package com.team2753.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;
import com.team2753.Constants;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;
import com.team2753.splines.FollowPath;
import com.team2753.subsystems.Slammer;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import lejos.robotics.navigation.Waypoint;

import static com.team2753.Constants.WHEEL_BASE;
import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 3/12/2018.
 */

@Autonomous(name = "Blue Far",group = "3 Glyph")
public class B_Far_Multi extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        WaypointSequence waypointSequence = new WaypointSequence(5);
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(13, 4, 0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(40, -3, 0));
        Path leftToGP = PathGenerator.makePath(waypointSequence, Constants.defaultTrajectoryConfig,
                WHEEL_BASE, "");

        waitForStart("BFar", AutoParams.AUTO);
        Robot.getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();

        SuperHitJewel(this.jewel_Color, Jewel_Color.Blue);

        switch (RelicRecoveryVuMark.LEFT){
            case LEFT:
                Robot.getDrive().encoderDrive(0.4, -24, -24, 5);

                Robot.getDrive().encoderDrive(0.3, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90

                Robot.getDrive().encoderDrive(0.5, -6, -6, 3);

                Robot.getDrive().turnCW(54, 0.3, 3);

                Robot.getDrive().encoderDrive(0.2, 1, 1, 1.5);

                ScorePreloadReleaseIntake();

                new FollowPath(this, Robot.getDrive(), leftToGP, -1, 1);

                Robot.getDrive().encoderDrive(1.0, 40, 40, 3);

                intakeThread.interrupt();

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                Robot.getDrive().encoderDrive(0.2, -4, -4, 1);

                if(t.seconds()<26){
                    Robot.getDrive().encoderDrive(0.3, 3, 3, 2);
                    Robot.getDrive().encoderDrive(0.3, -4, -4, 2);
                }

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING)) idle();

                break;
            case CENTER:
                Robot.getDrive().encoderDrive(0.6, -24, -24, 5);

                Robot.getDrive().encoderDrive(0.6, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90

                Robot.getDrive().encoderDrive(0.5, -13, -13, 3);

                Robot.getDrive().turnCW(54, 0.3, 3); // -90-52

                Robot.getDrive().encoderDrive(0.2, 1, 1, 3);

                ScorePreloadReleaseIntake();

                Robot.getDrive().encoderDrive(0.5, -13, -13, 3);

                Robot.getDrive().turnCW(20, 1.0, 3); // -150

                Robot.getDrive().encoderDrive(1.0, -35, -35, 6);

                Robot.getDrive().encoderDrive(0.4, -10, -10, 3);

                while (opModeIsActive() && Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                Robot.getDrive().encoderDrive(0.2, -2, -2, 1);

                if(t.seconds()<26){
                    Robot.getDrive().encoderDrive(0.3, 2, 2, 2);
                    Robot.getDrive().encoderDrive(0.3, -4, -4, 2);
                }

                while (opModeIsActive() && Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING)) idle();

                break;
            case RIGHT:
                // Drive off
                Robot.getDrive().encoderDrive(0.4, -30, -30, 3);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3);

                // Drive 17 in
                Robot.getDrive().encoderDrive(0.5, -19, -19, 3);

                // Turn 90
                Robot.getDrive().turnCW(90, 0.4, 3);

                ScorePreloadReleaseIntake();

                // Drive Backward
                Robot.getDrive().encoderDrive(0.6, -10, -10, 3);

                Robot.getDrive().turnCCW(28, 0.5, 2);

                Robot.getDrive().encoderDrive(0.5, -25, -25, 5);

                Robot.getDrive().turnCW(6, 0.3, 2);

                Robot.getDrive().encoderDrive(0.6, 45, 45, 3);

                while (opModeIsActive() && Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                Robot.getDrive().encoderDrive(0.3, -4, -4, 3);

                if(t.seconds()<26){
                    Robot.getDrive().encoderDrive(0.3, 3, 3, 2);
                    Robot.getDrive().encoderDrive(0.3, -4, -4, 2);
                }

                while (opModeIsActive() && Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING)) idle();

                break;
        }
    }
}