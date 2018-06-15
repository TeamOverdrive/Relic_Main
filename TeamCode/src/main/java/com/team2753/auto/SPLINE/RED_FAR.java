package com.team2753.auto.SPLINE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team2753.auto.AutoModeBase;
import com.team2753.splines.FollowPath;
import com.team2753.splines.FollowerDrive;
import com.team2753.splines.field.OverdriveField;
import com.team2753.subsystems.Slammer;

import static com.team2753.auto.SPLINE.PathStorage.pathFromFarRedRightToGlyphPit;
import static com.team2753.auto.SPLINE.PathStorage.pathFromGlyphPitToCenterRedFar;
import static com.team2753.auto.SPLINE.PathStorage.pathToRightColumnRedFar;

/**
 * Created by joshua9889 on 5/28/2018.
 */

@Autonomous
public class RED_FAR extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        if (PathStorage.pathToRightColumnRedFar ==null)
            PathStorage.calculateRedFar(new OverdriveField());

        waitForStart("Red", true);

        final FollowerDrive follower = new FollowerDrive(Robot.getDrive(), Robot.getFollowerWheel());

        this.SuperHitJewel(this.jewel_Color, Jewel_Color.Red);

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

        Robot.getDrive().encoderDrive(0.3, -5, -5, 4);
        Robot.getDrive().encoderDrive(0.2, 5, 5, 4);

        new FollowPath(this, Robot.getDrive(), pathFromGlyphPitToCenterRedFar, 1, 1);
        intake.interrupt();

        Robot.getDrive().encoderDrive(0.3, -3, -3, 3);
        Robot.getDrive().turnCW(Robot.getDrive().getGyroAngleDegrees()+35, 0.3, 2);
        Robot.getDrive().encoderDrive(0.3, -4, -4, 2);

        while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING) && opModeIsActive())Thread.yield();
        sleep(1000);


        followerUpdateThread.interrupt();
    }
}
