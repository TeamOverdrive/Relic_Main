package org.firstinspires.ftc.teamcode.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Team2753Linear;
import org.firstinspires.ftc.teamcode.auto.AutoParams;

import static java.lang.Math.PI;
import static org.firstinspires.ftc.teamcode.subsystems.Drive.WHEEL_BASE;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous
public class R_Far_Multi extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("RFar", AutoParams.AUTO, true);
        getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        boolean farther = false;
        getJewel().hit(this.jewel_Color, Jewel_Color.Red);

        switch (WhatColumnToScoreIn()){
            case LEFT:
                // Drive off
                getDrive().encoderDrive(0.6, 31, 31, 3);

                //Turn 90
                getDrive().turnCW(90, 0.3, 2);

                // Drive 17 in
                getDrive().encoderDrive(0.5, -18.5, -18.5, 2.5);

                // Turn 90
                getDrive().turnCCW(90, 0.4, 3);

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

                getDrive().turnCW(28, 0.5, 2);

                getDrive().encoderDrive(0.5, -25, -25, 5);
                sleep(200);

                farther = false;
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

                getDrive().turnCW(4, 0.3, 2);
                getDrive().encoderDrive(1, 49, 49, 3);
                getSlammer().autoSlam();
                getDrive().encoderDrive(0.3, -4, -4, 3);

                break;

            case CENTER:
                // Drive off
                getDrive().encoderDrive(0.6, 31, 31, 3);

                //Turn 90
                getDrive().turnCW(90, 0.3, 2);

                // Drive 17 in
                getDrive().encoderDrive(0.5, -17, -17, 2.5);

                // Turn 54
                getDrive().turnCCW(54, 0.4, 3);

                // Release Preloaded Glyph
                getSlammer().stopperUp();
                sleep(400);

                // Release Intake
                getIntake().releaseIntake();

                // Drive Backward
                getDrive().encoderDrive(0.6, -10, -10, 3);

                // Relic
                getRelic().setAngles(0,0);
                getSlammer().stopperDown();

                // Start intaking
                getIntake().intake();

                getDrive().turnCCW(8, 0.5, 2);

                getDrive().encoderDrive(0.5, -25, -25, 5);
                sleep(200);

                farther = false;
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
                boolean farther4 = false;
                if(!getIntake().frontDetected() && !getIntake().backDetected()){
                    sleep(600);
                    getIntake().intake();
                    getDrive().encoderDrive(0.2, -4, -4, 2);
                    getIntake().reverse();
                    farther4 = true;
                }

                if(farther4){
                    getDrive().encoderDrive(0.6, 22, 22, 3);
                } else {
                    getDrive().encoderDrive(0.6, 20, 20, 3);
                }

                getDrive().encoderDrive(0.2, (WHEEL_BASE*PI*6)/180, 0, 2);

                if(farther)
                    getDrive().encoderDrive(0.6, 25, 25, 3.5);
                else
                    getDrive().encoderDrive(0.6, 20, 20, 3.5);

                getIntake().intake();
                sleep(300);
                getSlammer().stopperUp();
                getIntake().reverse();
                sleep(300);
                getSlammer().autoSlam();
                getDrive().encoderDrive(0.3, -6, -6, 3);
                break;


            case RIGHT:
                // Drive off
                getDrive().encoderDrive(0.6, 31, 31, 3);

                //Turn 90
                getDrive().turnCW(90, 0.3, 2);

                // Drive 11 in
                getDrive().encoderDrive(0.5, -11, -11, 2.5);

                // Turn 54
                getDrive().turnCCW(54, 0.4, 3);

                // Release Preloaded Glyph
                getSlammer().stopperUp();
                sleep(400);

                // Release Intake
                getIntake().releaseIntake();

                // Drive Backward
                getDrive().encoderDrive(0.6, -15, -15, 3);

                getSlammer().stopperDown();

                // Relic
                getRelic().setAngles(0,0);

                // Start intaking
                getIntake().setPower(0.6);

                getDrive().turnCCW(10, 0.5, 2);

                getDrive().encoderDrive(0.5, -25, -25, 5);
                sleep(200);

                farther = false;
                if(!(getIntake().frontDetected() && getIntake().backDetected())){
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

                sleep(1000);
                getIntake().reverse();
                getDrive().turnCCW(10, 0.4, 3);
                getDrive().encoderDrive(0.6, 40, 40, 4);
                getIntake().intake();
                sleep(300);
                getSlammer().stopperUp();
                getIntake().reverse();
                sleep(300);
                getSlammer().autoSlam();
                getDrive().encoderDrive(0.3, -6, -6, 3);
                break;
        }
    }
}
