package com.team2753.auto.SPLINE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team2753.Constants;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.SPLINE.paths.RedFar_Paths;
import com.team2753.splines.FollowPath;
import com.team2753.splines.field.JoshuaField;
import com.team2753.subsystems.Slammer;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static com.team2753.auto.SPLINE.paths.RedFar_Paths.bsToCenter;
import static com.team2753.auto.SPLINE.paths.RedFar_Paths.bsToRight;
import static com.team2753.auto.SPLINE.paths.RedFar_Paths.leftToGP;
import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 5/28/2018.
 */

@Autonomous
@Disabled
public class RED_FAR extends AutoModeBase {

    private Thread intakeThread = new Thread(new Runnable() {
        @Override
        public void run() {
            sleep(400);
            while(!Thread.interrupted()){
                Robot.getIntake().setLeftRightPower(1.0, 1.0);
                sleep(1500);
                Robot.getIntake().setLeftRightPower(-1, -1);
                sleep(200);
                Thread.yield();
            }
        }
    });

    @Override
    public void runOpMode() throws InterruptedException {
        RedFar_Paths.calculateRedFar(new JoshuaField());

        waitForStart("Red", true);
        Robot.getDrive().zeroSensors();

        SuperHitJewel(jewel_Color, Jewel_Color.Red);

        RelicRecoveryVuMark useThisVuMark = RelicRecoveryVuMark.CENTER;
        switch (useThisVuMark){
            case LEFT:
                Robot.getDrive().encoderDrive(0.4, 30, 30, 3);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3);

                // Drive forward
                Robot.getDrive().encoderDrive(0.4, -18.8, -18.8, 3);

                // Turn 90
                Robot.getDrive().turnCCW(90, 0.3, 4);

                while(!Robot.getSlammer().setStopperState(Slammer.STOPPER_State.OPEN) && opModeIsActive())
                    Robot.getIntake().releaseIntake();

                sleep(40);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sleep(1000);
                        Robot.getSlammer().setStopperState(Slammer.STOPPER_State.CLOSED);
                    }
                }).start();

                intakeThread.start();

                // From Column to pit
                new FollowPath(this, Robot.getDrive(), leftToGP, -1, 1);

                sleep(500);

                // Drive a little forward
                Robot.getDrive().encoderDrive(0.3, 0, (Constants.WHEEL_BASE*PI*25)/180, 3);
                Robot.getDrive().encoderDrive(0.8, 35, 35, 4);
                intakeThread.interrupt();
                while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING) && opModeIsActive())Thread.yield();
                sleep(2000);

                Robot.getDrive().encoderDrive(0.2, -4, -4, 2);

                while (opModeIsActive() && !Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING)) idle();
                break;
            case CENTER:
                new FollowPath(this, Robot.getDrive(), bsToCenter, 1, 1);

                while(!Robot.getSlammer().setStopperState(Slammer.STOPPER_State.OPEN))
                    Robot.getIntake().releaseIntake();
                break;
            case RIGHT:
                new FollowPath(this, Robot.getDrive(), bsToRight, 1, 1);

                while(!Robot.getSlammer().setStopperState(Slammer.STOPPER_State.OPEN))
                    Robot.getIntake().releaseIntake();
//
//                sleep(300);
//                rightToGP.goLeft();
//
//                Robot.getSlammer().setStopperState(Slammer.STOPPER_State.CLOSED);
//
//                Thread intakeThread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        sleep(400);
//                        while(!Thread.interrupted()){
//                            Robot.getIntake().setLeftRightPower(1.0, 1.0);
//                            sleep(1500);
//                            Robot.getIntake().setLeftRightPower(0.5, 1.0);
//                            sleep(200);
//                            Thread.yield();
//                        }
//
//                        while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.SCORING) && opModeIsActive())Thread.yield();
//                    }
//                });
//                intakeThread.start();
//
//                new FollowPath(this, Robot.getDrive(), rightToGP, -1, 1);
//
//                Robot.getDrive().encoderDrive(0.3, -5, -5, 4);
//                Robot.getDrive().encoderDrive(0.2, 5, 5, 4);
//
//                new FollowPath(this, Robot.getDrive(), gpToRight, 1, 1);
//                intakeThread.interrupt();
//
//                Robot.getDrive().encoderDrive(0.3, -3, -3, 3);
//                Robot.getDrive().turnCW(Robot.getDrive().getGyroAngleDegrees()+35, 0.3, 2);
//                Robot.getDrive().encoderDrive(0.3, -4, -4, 2);
//
//                while (!Robot.getSlammer().setSlammerState(Slammer.SLAMMER_State.INTAKING) && opModeIsActive())Thread.yield();
//                sleep(1000);
                break;
            case UNKNOWN:

                break;
        }

    }
}
