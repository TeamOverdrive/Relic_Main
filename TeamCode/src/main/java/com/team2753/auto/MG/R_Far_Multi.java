package com.team2753.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous(name = "Red Far 3",group = "3 Glyph")
public class R_Far_Multi extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("RFar", AutoParams.AUTO);
        Robot.getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        boolean farther = false;
        hitJewel(this.jewel_Color, Jewel_Color.Red);

        switch (WhatColumnToScoreIn()){
            case LEFT:
                // Drive off
                Robot.getDrive().encoderDrive(0.4, 30, 30, 3);

                //Turn 90
                Robot.getDrive().turnCW(90, 0.3, 3);

                // Drive 17 in
                Robot.getDrive().encoderDrive(0.4, -19, -19, 3);

                // Turn 90
                Robot.getDrive().turnCCW(90, 0.4, 3);

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

                Robot.getDrive().turnCW(28, 0.5, 2);

                Robot.getDrive().encoderDrive(0.5, -25, -25, 5);
                sleep(200);

                farther = false;
                if(!(Robot.getIntake().frontDetected() && Robot.getIntake().backDetected())){
                    Robot.getIntake().reverse();
                    sleep(400);
                    Robot.getIntake().intake();
                    Robot.getDrive().encoderDrive(0.2, -7, -7, 3);
                    farther = true;
                }

                while (opModeIsActive() && t.seconds()>24 && !(Robot.getIntake().frontDetected() && Robot.getIntake().backDetected())){
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

                // Release Preloaded Glyph
                Robot.getSlammer().stopperUp();
                sleep(400);

                // Release Intake
                Robot.getIntake().releaseIntake();

                // Drive Backward
                Robot.getDrive().encoderDrive(0.6, -5, -5, 3);

                // Relic
//                Robot.getRelic().setAngles(0,0);
//                Robot.getRelic().setWristPostion(64);
//                Robot.getRelic().close();
                Robot.getSlammer().stopperDown();

                // Start intaking
                Robot.getIntake().intake();

                Robot.getDrive().turnCCW(13, 0.5, 2);

                Robot.getDrive().encoderDrive(0.5, -25, -25, 5);
                sleep(200);

                farther = false;
                if(!(Robot.getIntake().frontDetected() && Robot.getIntake().backDetected())){
                    Robot.getIntake().reverse();
                    sleep(400);
                    Robot.getIntake().intake();
                    Robot.getDrive().encoderDrive(0.2, -7, -7, 3);
                    farther = true;
                }

                while (opModeIsActive() && t.seconds()>24 && !(Robot.getIntake().frontDetected() && Robot.getIntake().backDetected())){
                    Thread.yield();
                    telemetry.clearAll();
                    telemetry.addData("Front", Robot.getIntake().frontDetected());
                    telemetry.addData("Back", Robot.getIntake().backDetected());
                    telemetry.update();
                }

                Robot.getIntake().reverse();
                boolean farther4 = false;
                if(!Robot.getIntake().frontDetected() && !Robot.getIntake().backDetected()){
                    sleep(600);
                    Robot.getIntake().intake();
                    Robot.getDrive().encoderDrive(0.2, -4, -4, 2);
                    Robot.getIntake().reverse();
                    farther4 = true;
                }

                if(farther4){
                    Robot.getDrive().encoderDrive(0.6, 37, 37, 3);
                } else {
                    Robot.getDrive().encoderDrive(0.6, 35, 35, 3);
                }


                Robot.getIntake().intake();
                sleep(300);
                Robot.getSlammer().stopperUp();
                Robot.getIntake().reverse();
                sleep(300);
                Robot.getSlammer().autoSlam();
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

                // Release Preloaded Glyph
                Robot.getSlammer().stopperUp();
                sleep(400);

                // Release Intake
                Robot.getIntake().releaseIntake();

                // Drive Backward
                Robot.getDrive().encoderDrive(0.6, -15, -15, 3);

                Robot.getSlammer().stopperDown();

                // Relic
//                Robot.getRelic().setAngles(0,0);
//                Robot.getRelic().setWristPostion(64);
//                Robot.getRelic().close();

                // Start intaking
                Robot.getIntake().setPower(0.6);

                Robot.getDrive().turnCCW(10, 0.5, 2);

                Robot.getDrive().encoderDrive(0.5, -25, -25, 5);
                sleep(200);

                farther = false;
                if(!(Robot.getIntake().frontDetected() && Robot.getIntake().backDetected())){
                    Robot.getIntake().reverse();
                    sleep(400);
                    Robot.getIntake().intake();
                    Robot.getDrive().encoderDrive(0.2, -7, -7, 3);
                    farther = true;
                }

                while (opModeIsActive() && t.seconds()>24 && !(Robot.getIntake().frontDetected() || Robot.getIntake().backDetected())){
                    Thread.yield();
                    telemetry.clearAll();
                    telemetry.addData("Front", Robot.getIntake().frontDetected());
                    telemetry.addData("Back", Robot.getIntake().backDetected());
                    telemetry.update();
                }

                sleep(1000);
                Robot.getIntake().reverse();
                Robot.getDrive().turnCCW(10, 0.4, 3);
                Robot.getDrive().encoderDrive(0.6, 40, 40, 4);
                Robot.getIntake().intake();
                sleep(300);
                Robot.getSlammer().stopperUp();
                Robot.getIntake().reverse();
                sleep(300);
                Robot.getSlammer().autoSlam();
                Robot.getDrive().encoderDrive(0.3, -6, -6, 3);
                break;
        }
    }
}
