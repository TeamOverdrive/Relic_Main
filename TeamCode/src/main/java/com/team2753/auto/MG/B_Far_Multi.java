package com.team2753.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team2753.Constants;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;

import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 3/12/2018.
 */

@Autonomous(name = "Blue Far 3",group = "3 Glyph")
@Disabled
public class B_Far_Multi extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("BFar", AutoParams.AUTO);
        Robot.getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();

        DefaultHitJewel(this.jewel_Color, Jewel_Color.Blue);

        switch (WhatColumnToScoreIn()){
            case LEFT:
                Robot.getDrive().encoderDrive(0.6, -24, -24, 5);
                Robot.getDrive().encoderDrive(0.6, -(Constants.WHEEL_BASE*PI*90)/180, 0, 3); // -90
                Robot.getDrive().encoderDrive(0.5, -6, -6, 3);

                Robot.getDrive().turnCW(54, 0.3, 3); // -90-52
                Robot.getDrive().encoderDrive(0.2, 1, 1, 3);
                Robot.getSlammer().stopperUp();
                sleep(400);
                Robot.getDrive().encoderDrive(0.2, -3, -3, 3);

                Robot.getIntake().releaseIntake();
                Robot.getDrive().encoderDrive(0.5, -10, -10, 2);
//                Robot.getRelic().setAngles(0,0);
//                Robot.getRelic().setWristPostion(50);
                sleep(400);

                Robot.getSlammer().stopperDown();
                Robot.getDrive().turnCW(8, 1.0, 3); // -150

                Robot.getIntake().reverse();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sleep(1000);
                        Robot.getIntake().intake();
                    }
                }).start();
                Robot.getDrive().encoderDrive(1.0, -35, -35, 6);

                boolean left = false;
                if(!(Robot.getIntake().backDetected() && Robot.getIntake().frontDetected())){
                    left = true;
                    Robot.getIntake().reverse();
                    sleep(500);
                    Robot.getIntake().intake();
                    Robot.getDrive().encoderDrive(0.4, -10, -10, 3);
                } else {
                    Robot.getIntake().reverse();
                }



                while (opModeIsActive() && t.seconds()>24 &&
                        !(Robot.getIntake().frontDetected() || Robot.getIntake().backDetected())){
                    Thread.yield();
                    telemetry.clearAll();
                    telemetry.addData("Front", Robot.getIntake().frontDetected());
                    telemetry.addData("Back", Robot.getIntake().backDetected());
                    telemetry.update();
                }
                sleep(500);
                Robot.getIntake().reverse();

                if(left){
                    Robot.getDrive().encoderDrive(1.0, 0, (Constants.WHEEL_BASE*PI*10)/180, 3);
                    Robot.getIntake().intake();

                    Robot.getDrive().encoderDrive(0.8, 53, 53, 3);
                } else {
                    Robot.getDrive().encoderDrive(1.0, 0, (Constants.WHEEL_BASE*PI*8)/180, 3);
                    Robot.getIntake().intake();

                    Robot.getDrive().encoderDrive(0.8, 49, 49, 3);
                }

                Robot.getSlammer().autoSlam();
                Robot.getDrive().encoderDrive(0.2, -4, -4, 1);
                if(t.seconds()<26){
                    Robot.getDrive().encoderDrive(0.3, 3, 3, 2);
                    Robot.getDrive().encoderDrive(0.3, -4, -4, 2);
                }
//                Robot.getSlammer().setPower(-1);
//                sleep(100);
//                Robot.getSlammer().stop();
                break;
            case CENTER:
                Robot.getDrive().encoderDrive(0.6, -24, -24, 5);
                Robot.getDrive().encoderDrive(0.6, -(Constants.WHEEL_BASE*PI*90)/180, 0, 3); // -90
                Robot.getDrive().encoderDrive(0.5, -13, -13, 3);

                Robot.getDrive().turnCW(54, 0.3, 3); // -90-52
                Robot.getDrive().encoderDrive(0.2, 1, 1, 3);
                Robot.getSlammer().stopperUp();
                sleep(400);
                Robot.getDrive().encoderDrive(0.2, -3, -3, 3);

                Robot.getIntake().releaseIntake();
                Robot.getDrive().encoderDrive(0.5, -10, -10, 2);
//                Robot.getRelic().setAngles(0,0);
//                Robot.getRelic().setWristPostion(50);
                sleep(400);

                Robot.getSlammer().stopperDown();
                Robot.getDrive().turnCW(20, 1.0, 3); // -150

                Robot.getIntake().reverse();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sleep(1000);
                        Robot.getIntake().intake();
                    }
                }).start();
                Robot.getDrive().encoderDrive(1.0, -35, -35, 6);

                Robot.getIntake().reverse();
                sleep(500);
                Robot.getIntake().intake();
                Robot.getDrive().encoderDrive(0.4, -10, -10, 3);

                while (opModeIsActive() && t.seconds()>24 &&
                        !(Robot.getIntake().frontDetected() || Robot.getIntake().backDetected())){
                    Thread.yield();
                    telemetry.clearAll();
                    telemetry.addData("Front", Robot.getIntake().frontDetected());
                    telemetry.addData("Back", Robot.getIntake().backDetected());
                    telemetry.update();
                }
                sleep(500);
                Robot.getIntake().reverse();

                Robot.getDrive().encoderDrive(0.8, 52, 52, 3);
                Robot.getSlammer().autoSlam();
                Robot.getDrive().encoderDrive(0.2, -2, -2, 1);

                if(t.seconds()<26){
                    Robot.getDrive().encoderDrive(0.3, 2, 2, 2);
                    Robot.getDrive().encoderDrive(0.3, -4, -4, 2);
                }

//                Robot.getSlammer().setPower(-1);
//                sleep(100);
//                Robot.getSlammer().stop();
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

                // Release Preloaded Glyph
                Robot.getSlammer().stopperUp();
                sleep(800);

                // Release Intake
                Robot.getIntake().releaseIntake();

                // Drive Backward
                Robot.getDrive().encoderDrive(0.6, -10, -10, 3);

                // Relic
//                Robot.getRelic().setAngles(0,0);
//                Robot.getRelic().setWristPostion(64);
//                Robot.getRelic().close();
                Robot.getSlammer().stopperDown();

                // Start intaking
                Robot.getIntake().intake();

                Robot.getDrive().turnCCW(28, 0.5, 2);

                Robot.getDrive().encoderDrive(0.5, -25, -25, 5);
//                Robot.getRelic().lock();
                sleep(200);

                boolean farther = false;
                if(!(Robot.getIntake().frontDetected() && Robot.getIntake().backDetected())){
                    Robot.getIntake().reverse();
                    sleep(400);
                    Robot.getIntake().intake();
                    Robot.getDrive().encoderDrive(0.2, -7, -7, 3);
                    farther = true;
                }

                while (opModeIsActive() && t.seconds()>24 &&
                        !(Robot.getIntake().frontDetected() && Robot.getIntake().backDetected())){
                    Thread.yield();
                    telemetry.clearAll();
                    telemetry.addData("Front", Robot.getIntake().frontDetected());
                    telemetry.addData("Back", Robot.getIntake().backDetected());
                    telemetry.update();
                }

                Robot.getIntake().reverse();
                boolean farther3 = false;
                if(!Robot.getIntake().frontDetected() && !Robot.getIntake().backDetected()){
                    sleep(600);
                    Robot.getIntake().intake();
                    Robot.getDrive().encoderDrive(0.2, -4, -4, 2);
                    Robot.getIntake().reverse();
                    farther3 = true;
                }

                Robot.getDrive().turnCW(6, 0.3, 2);

                if(farther3){
                    Robot.getDrive().encoderDrive(0.6, 46.5, 46.5, 3);
                } else {
                    Robot.getDrive().encoderDrive(0.6, 45, 45, 3);
                }

                Robot.getSlammer().autoSlam();
                Robot.getDrive().encoderDrive(0.3, -4, -4, 3);
                if(t.seconds()<26){
                    Robot.getDrive().encoderDrive(0.3, 3, 3, 2);
                    Robot.getDrive().encoderDrive(0.3, -4, -4, 2);
                }
                break;
        }
    }
}