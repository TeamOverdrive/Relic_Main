package com.team2753.auto.SPLINE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team2753.Team2753Linear;
import com.team2753.splines.FollowPath;
import com.team2753.splines.FollowerDrive;
import com.team2753.splines.PathStorage;
import com.team2753.splines.field.JoshuaField;
import com.team2753.subsystems.Slammer;

import static com.team2753.splines.PathStorage.pathFromFarRedRightToGlyphPit;
import static com.team2753.splines.PathStorage.pathFromGlyphPitToCenterRedFar;
import static com.team2753.splines.PathStorage.pathToRightColumnRedFar;

/**
 * Created by joshua9889 on 5/28/2018.
 */

@Autonomous
public class RED_FAR extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        if (PathStorage.pathToRightColumnRedFar ==null)
            PathStorage.calculateRedFar(new JoshuaField());

        waitForStart("Red", true);

        final FollowerDrive follower = new FollowerDrive(Robot.getDrive(), Robot.getFollowerWheel());

        Robot.getIntake().releaseIntake();
        Robot.getJewel().deploy(true);
        sleep(200);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(1000);
                Robot.getJewel().retract(true);
            }
        }).start();

        new FollowPath(this, Robot.getDrive(), pathToRightColumnRedFar, 1, 1);
        follower.setZero();
        Thread followerUpdateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    follower.update();
                }
            }
        });

        followerUpdateThread.setPriority(Thread.NORM_PRIORITY);
        followerUpdateThread.start();

        while(!Robot.getSlammer().setStopperState(Slammer.STOPPER_State.OPEN))
            Robot.getIntake().releaseIntake();

        sleep(100);
        pathFromFarRedRightToGlyphPit.goLeft();

        Robot.getSlammer().setStopperState(Slammer.STOPPER_State.CLOSED);
        Thread intake = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.interrupted()){
                    Robot.getIntake().setLeftRightPower(1.0, 1.0);
                    sleep(1500);
                    Robot.getIntake().setLeftRightPower(0.5, 1.0);
                    sleep(200);
                    Thread.yield();
                }

                while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING) && opModeIsActive())Thread.yield();
            }
        });
        intake.start();

        new FollowPath(this, Robot.getDrive(), pathFromFarRedRightToGlyphPit, -1, 1);

        new FollowPath(this, Robot.getDrive(), pathFromGlyphPitToCenterRedFar, 1, 1);
        intake.interrupt();

        Robot.getDrive().encoderDrive(0.1, -3, -3, 3);

        while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING) && opModeIsActive())Thread.yield();

        followerUpdateThread.interrupt();
    }
}
