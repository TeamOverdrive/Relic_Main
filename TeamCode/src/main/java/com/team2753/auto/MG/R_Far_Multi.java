package com.team2753.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;
import com.team2753.splines.FollowPath;
import com.team2753.subsystems.Slammer;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static com.team2753.Constants.WHEEL_BASE;
import static com.team2753.Constants.defaultTrajectoryConfig;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous(name = "Red Far",group = "3 Glyph")
//@Disabled
public class R_Far_Multi extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        WaypointSequence RightColumnToGlyphPit = new WaypointSequence(5);
        RightColumnToGlyphPit.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
        RightColumnToGlyphPit.addWaypoint(new WaypointSequence.Waypoint(13, -2, 0));
        RightColumnToGlyphPit.addWaypoint(new WaypointSequence.Waypoint(39, 4, Math.toRadians(4)));
        Path path = PathGenerator.makePath(RightColumnToGlyphPit, defaultTrajectoryConfig, WHEEL_BASE, "");

        waitForStart("RFar", AutoParams.AUTO);
        Robot.getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        boolean farther = false;

        SuperHitJewel(this.jewel_Color, Jewel_Color.Red);

        switch (RelicRecoveryVuMark.RIGHT){
            case LEFT:
                // Drive off Stone
                Robot.getDrive().encoderDrive(0.5, 30, 30, 3);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3);

                // Drive 19 in
                Robot.getDrive().encoderDrive(0.5, -19, -19, 3);

                // Turn 90
                Robot.getDrive().turnCCW(90, 0.4, 3);

                ScorePreloadReleaseIntake();

                // Drive Backward
                Robot.getDrive().encoderDrive(0.6, -10, -10, 3);

                Robot.getDrive().turnCW(28, 0.5, 2);

                Robot.getDrive().encoderDrive(0.5, -25, -25, 5);
                sleep(200);

                Robot.getDrive().turnCW(6, 0.3, 2);

                Robot.getDrive().encoderDrive(0.6, 45, 45, 3);

                while(opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();
                Robot.getDrive().encoderDrive(0.3, -4, -4, 3);
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

                while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING) && opModeIsActive()) idle();
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

                new FollowPath(this, Robot.getDrive(), path, -1, 1);

                Robot.getDrive().encoderDrive(1, 35, 35, 4);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING)) idle();

                Robot.getDrive().encoderDrive(0.3, -6, -6, 3);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING)) idle();

                break;
        }

        intakeThread.interrupt();
    }
}
