package org.firstinspires.ftc.teamcode.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Team2753Linear;
import org.firstinspires.ftc.teamcode.auto.AutoParams;

import static java.lang.Math.PI;
import static org.firstinspires.ftc.teamcode.subsystems.Drive.WHEEL_BASE;

/**
 * Created by joshua9889 on 3/12/2018.
 */

@Autonomous(group = "3 Glyph")
public class B_Far_Multi extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("BFar", AutoParams.AUTO, true);
        getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        getJewel().hit(this.jewel_Color, Jewel_Color.Blue);

        switch (WhatColumnToScoreIn()){
            case LEFT:
                getDrive().encoderDrive(0.6, -24, -24, 5);
                getDrive().encoderDrive(0.6, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90
                getDrive().encoderDrive(0.5, -6, -6, 3);

                getDrive().turnCW(54, 0.3, 3); // -90-52
                getDrive().encoderDrive(0.2, 1, 1, 3);
                getSlammer().stopperUp();
                sleep(400);
                getDrive().encoderDrive(0.2, -3, -3, 3);

                getIntake().releaseIntake();
                getDrive().encoderDrive(0.5, -10, -10, 2);
                getRelic().setAngles(0,0);
                getRelic().setWristPostion(50);
                sleep(400);

                getSlammer().stopperDown();
                getDrive().turnCW(8, 1.0, 3); // -150

                getIntake().reverse();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sleep(1000);
                        getIntake().intake();
                    }
                }).start();
                getDrive().encoderDrive(1.0, -35, -35, 6);

                boolean left = false;
                if(!(getIntake().backDetected() && getIntake().frontDetected())){
                    left = true;
                    getIntake().reverse();
                    sleep(500);
                    getIntake().intake();
                    getDrive().encoderDrive(0.4, -10, -10, 3);
                } else {
                    getIntake().reverse();
                }



                while (opModeIsActive() && t.seconds()>24 && !(getIntake().frontDetected() || getIntake().backDetected())){
                    Thread.yield();
                    telemetry.clearAll();
                    telemetry.addData("Front", getIntake().frontDetected());
                    telemetry.addData("Back", getIntake().backDetected());
                    telemetry.update();
                }
                sleep(500);
                getIntake().reverse();

                if(left){
                    getDrive().encoderDrive(1.0, 0, (WHEEL_BASE*PI*10)/180, 3);
                    getIntake().intake();

                    getDrive().encoderDrive(0.8, 53, 53, 3);
                } else {
                    getDrive().encoderDrive(1.0, 0, (WHEEL_BASE*PI*8)/180, 3);
                    getIntake().intake();

                    getDrive().encoderDrive(0.8, 49, 49, 3);
                }

                getSlammer().autoSlam();
                getDrive().encoderDrive(0.2, -4, -4, 1);
                getSlammer().setPower(-1);
                sleep(100);
                getSlammer().stop();
                break;
            case CENTER:
                getDrive().encoderDrive(0.6, -24, -24, 5);
                getDrive().encoderDrive(0.6, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90
                getDrive().encoderDrive(0.5, -13, -13, 3);

                getDrive().turnCW(54, 0.3, 3); // -90-52
                getDrive().encoderDrive(0.2, 1, 1, 3);
                getSlammer().stopperUp();
                sleep(400);
                getDrive().encoderDrive(0.2, -3, -3, 3);

                getIntake().releaseIntake();
                getDrive().encoderDrive(0.5, -10, -10, 2);
                getRelic().setAngles(0,0);
                getRelic().setWristPostion(50);
                sleep(400);

                getSlammer().stopperDown();
                getDrive().turnCW(20, 1.0, 3); // -150

                getIntake().reverse();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sleep(1000);
                        getIntake().intake();
                    }
                }).start();
                getDrive().encoderDrive(1.0, -35, -35, 6);

                getIntake().reverse();
                sleep(500);
                getIntake().intake();
                getDrive().encoderDrive(0.4, -10, -10, 3);

                while (opModeIsActive() && t.seconds()>24 && !(getIntake().frontDetected() || getIntake().backDetected())){
                    Thread.yield();
                    telemetry.clearAll();
                    telemetry.addData("Front", getIntake().frontDetected());
                    telemetry.addData("Back", getIntake().backDetected());
                    telemetry.update();
                }
                sleep(500);
                getIntake().reverse();

                getDrive().encoderDrive(0.8, 52, 52, 3);
                getSlammer().autoSlam();
                getDrive().encoderDrive(0.2, -2, -2, 1);
                getSlammer().setPower(-1);
                sleep(100);
                getSlammer().stop();
                break;
            case RIGHT:
                // Drive off
                getDrive().encoderDrive(0.6, -30, -30, 3);

                //Turn 90
                getDrive().turnCW(90, 0.3, 2);

                // Drive 17 in
                getDrive().encoderDrive(0.5, -21, -21, 2.5);

                // Turn 90
                getDrive().turnCW(90, 0.4, 3);

                // Release Preloaded Glyph
                getSlammer().stopperUp();
                sleep(600);

                // Release Intake
                getIntake().releaseIntake();

                // Drive Backward
                getDrive().encoderDrive(0.6, -10, -10, 3);

                // Relic
                getRelic().setAngles(0,0);
                getSlammer().stopperDown();

                // Start intaking
                getIntake().intake();

                getDrive().turnCCW(28, 0.5, 2);

                getDrive().encoderDrive(0.5, -25, -25, 5);
                getRelic().lock();
                sleep(200);

                boolean farther = false;
                if(!(getIntake().frontDetected() || getIntake().backDetected())){
                    getIntake().reverse();
                    sleep(400);
                    getIntake().intake();
                    getDrive().encoderDrive(0.2, -7, -7, 3);
                    farther = true;
                }

                while (opModeIsActive() && t.seconds()>24 && !(getIntake().frontDetected() || getIntake().backDetected())){
                    Thread.yield();
                    telemetry.clearAll();
                    telemetry.addData("Front", getIntake().frontDetected());
                    telemetry.addData("Back", getIntake().backDetected());
                    telemetry.update();
                }

                getIntake().reverse();
                boolean farther3 = false;
                if(!getIntake().frontDetected() && !getIntake().backDetected()){
                    sleep(600);
                    getIntake().intake();
                    getDrive().encoderDrive(0.2, -4, -4, 2);
                    getIntake().reverse();
                    farther3 = true;
                }

                getDrive().turnCCW(2, 0.3, 2);
                getDrive().encoderDrive(1, 49, 49, 3);
                getSlammer().autoSlam();
                getDrive().encoderDrive(0.3, -4, -4, 3);
                break;
        }
    }
}