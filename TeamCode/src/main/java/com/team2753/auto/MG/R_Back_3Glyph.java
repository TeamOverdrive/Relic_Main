package com.team2753.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team2753.Constants;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous(name = "Red Back 3", group = "3 Glyph")
public class R_Back_3Glyph extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("RBack", AutoParams.AUTO);
        Robot.getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        hitJewel(this.jewel_Color, Jewel_Color.Red);

        switch (RelicRecoveryVuMark.RIGHT){
            case RIGHT:
                boolean rightFarther = false;
                Robot.getDrive().encoderDrive(0.6, 21, 21, 3);
                Robot.getDrive().encoderDrive(0.6, 0, (Constants.WHEEL_BASE*PI*(90))/180, 3);
                Robot.getDrive().encoderDrive(0.6, 2, 2, 3);
                Robot.getSlammer().stopperUp();
                sleep(400);
                Robot.getIntake().releaseIntake();
//                Robot.getRelic().setAngles(0,0);
//                Robot.getRelic().setWristPostion(64);
//                Robot.getRelic().close();
                Robot.getDrive().encoderDrive(0.7, -4, -4, 3);
                Robot.getSlammer().stopperDown();
                Robot.getIntake().intake();

                Robot.getDrive().encoderDrive(0.3, -20, -20, 3);
                Robot.getDrive().encoderDrive(0.2, -5, -5, 2);
//                Robot.getRelic().lock();

                telemetry.clearAll();
                telemetry.addData("Front", Robot.getIntake().frontDetected());
                telemetry.addData("Back", Robot.getIntake().backDetected());
                telemetry.update();

                int counter = 0;
                for (int i=0;i<30;i++){
                    if((Robot.getIntake().frontDetected() && Robot.getIntake().backDetected()))
                        counter++;
                }

                if(counter<10){
                    rightFarther = true;
                    sleep(400);
                    Robot.getIntake().reverse();
                    sleep(400);
                    Robot.getIntake().intake();
                    Robot.getDrive().encoderDrive(0.4, 3, 3, 3);
                    Robot.getDrive().encoderDrive(0.4, -6, -6, 3);
                    while (opModeIsActive() && t.seconds()<18 && !(Robot.getIntake().frontDetected() && Robot.getIntake().backDetected())){
                        Thread.yield();
                        telemetry.clearAll();
                        telemetry.addData("Front", Robot.getIntake().frontDetected());
                        telemetry.addData("Back", Robot.getIntake().backDetected());
                        telemetry.update();
                    }
                    Robot.getIntake().reverse();
                    sleep(1000);
                } else {
                    rightFarther = false;
                    sleep(500);
                    Robot.getIntake().reverse();
                    sleep(1000);
                }

                Robot.getIntake().setPower(0.5);

                if(rightFarther){
                    Robot.getDrive().encoderDrive(0.4, (Constants.WHEEL_BASE*PI*(17))/180, 0, 3);
                    Robot.getDrive().encoderDrive(0.4, 36, 36, 4);
                } else {
                    Robot.getDrive().encoderDrive(0.4, (Constants.WHEEL_BASE*PI*(25))/180, 0, 3);
                    Robot.getDrive().encoderDrive(0.4, 32, 32, 4);
                }

                Robot.getSlammer().autoSlam();
                Robot.getDrive().encoderDrive(0.5, -5, -5, 2);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        Robot.getSlammer().setPower(-1);
//                        sleep(300);
//                        Robot.getSlammer().setPower(0);
                    }
                }).start();

                if(30-t.seconds()>=4){
                    Robot.getDrive().encoderDrive(1, 4, 4, 2);
                    Robot.getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;

            case CENTER:
                boolean centerFarther = false;
                Robot.getDrive().encoderDrive(0.6, 22, 22, 4);
                Robot.getDrive().encoderDrive(0.6, 0, (Constants.WHEEL_BASE*PI*(57))/180, 3);
                Robot.getDrive().encoderDrive(0.6, 7, 7, 3);
                Robot.getSlammer().stopperUp();
                sleep(400);
                Robot.getIntake().releaseIntake();
                Robot.getDrive().encoderDrive(0.6, -(Constants.WHEEL_BASE*PI*(90-57))/180, 0, 3);
                Robot.getDrive().encoderDrive(0.6, -5, -5, 2);
                Robot.getSlammer().stopperDown();
                Robot.getIntake().intake();
//                Robot.getRelic().setAngles(0,0);
//                Robot.getRelic().setWristPostion(64);
//                Robot.getRelic().close();

                Robot.getDrive().encoderDrive(0.3, -20, -20, 3);
                Robot.getDrive().encoderDrive(0.2, -5, -5, 2);

                telemetry.clearAll();
                telemetry.addData("Front", Robot.getIntake().frontDetected());
                telemetry.addData("Back", Robot.getIntake().backDetected());
                telemetry.update();

                int counter2 = 0;
                for (int i=0;i<30;i++){
                    if((Robot.getIntake().frontDetected() && Robot.getIntake().backDetected()))
                        counter2++;
                }

                if(counter2<10){
                    centerFarther = true;
                    sleep(400);
                    Robot.getIntake().reverse();
                    sleep(400);
                    Robot.getIntake().intake();
                    Robot.getDrive().encoderDrive(0.4, 3, 3, 3);
                    Robot.getDrive().encoderDrive(0.4, -6, -6, 3);
                    while (opModeIsActive() && t.seconds()<18 && !(Robot.getIntake().frontDetected() && Robot.getIntake().backDetected())){
                        Thread.yield();
                        telemetry.clearAll();
                        telemetry.addData("Front", Robot.getIntake().frontDetected());
                        telemetry.addData("Back", Robot.getIntake().backDetected());
                        telemetry.update();
                    }
                    Robot.getIntake().reverse();
                    sleep(1000);
                } else {
                    centerFarther = false;
                    sleep(500);
                    Robot.getIntake().reverse();
                    sleep(1000);
                }

                Robot.getIntake().intake();

                if(centerFarther){
                    Robot.getDrive().encoderDrive(0.4, 0, (Constants.WHEEL_BASE*PI*(-7))/180, 3);
                    Robot.getDrive().encoderDrive(0.4, 39, 39, 4);
                } else {
                    Robot.getDrive().encoderDrive(0.4, 0, (Constants.WHEEL_BASE*PI*(-3))/180, 3);
                    Robot.getDrive().encoderDrive(0.4, 35, 35, 4);
                }

                telemetry.addData("Counter", counter2);
                telemetry.update();

                Robot.getSlammer().autoSlam();
                Robot.getDrive().encoderDrive(0.5, -5, -5, 2);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        Robot.getSlammer().setPower(-1);
//                        sleep(300);
//                        Robot.getSlammer().setPower(0);
                    }
                }).start();

                if(30-t.seconds()>=4){
                    Robot.getDrive().encoderDrive(1, 4, 4, 2);
                    Robot.getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;

            case LEFT:
                boolean leftFarther = false;
                Robot.getDrive().encoderDrive(0.6, 27, 27, 4);
                Robot.getDrive().encoderDrive(0.6, 0, (Constants.WHEEL_BASE*PI*(54))/180, 3);
                Robot.getDrive().encoderDrive(0.6, 7, 7, 3);
                Robot.getSlammer().stopperUp();
                sleep(400);
                Robot.getIntake().releaseIntake();
                Robot.getDrive().encoderDrive(0.6, -(Constants.WHEEL_BASE*PI*(90-54))/180, 0, 3);
//                Robot.getRelic().setAngles(0,0);
//                Robot.getRelic().setWristPostion(64);
//                Robot.getRelic().close();
                Robot.getDrive().encoderDrive(0.6, -5, -5, 2);
                Robot.getSlammer().stopperDown();
                Robot.getIntake().intake();

                Robot.getDrive().encoderDrive(0.3, -20, -20, 3);
                Robot.getDrive().encoderDrive(0.2, -5, -5, 2);
//                Robot.getRelic().lock();

                telemetry.clearAll();
                telemetry.addData("Front", Robot.getIntake().frontDetected());
                telemetry.addData("Back", Robot.getIntake().backDetected());
                telemetry.update();

                if(!(Robot.getIntake().frontDetected() && Robot.getIntake().backDetected())){
                    leftFarther = true;
                    sleep(400);
                    Robot.getIntake().reverse();
                    sleep(400);
                    Robot.getIntake().intake();
                    Robot.getDrive().encoderDrive(0.4, 3, 3, 3);
                    Robot.getDrive().encoderDrive(0.4, -6, -6, 3);
                    while (opModeIsActive() && t.seconds()<18 && !(Robot.getIntake().frontDetected() && Robot.getIntake().backDetected())){
                        Thread.yield();
                        telemetry.clearAll();
                        telemetry.addData("Front", Robot.getIntake().frontDetected());
                        telemetry.addData("Back", Robot.getIntake().backDetected());
                        telemetry.update();
                    }
                    Robot.getIntake().reverse();
                    sleep(500);
                } else {
                    leftFarther = false;
                    sleep(500);
                    Robot.getIntake().reverse();
                    sleep(300);
                }

                Robot.getIntake().setPower(0.5);

                if(leftFarther){
                    Robot.getDrive().encoderDrive(0.4, 0, (Constants.WHEEL_BASE*PI*(9))/180, 3);
                    Robot.getDrive().encoderDrive(0.4, 39, 39, 4);
                } else {
                    Robot.getDrive().encoderDrive(0.4, 0, (Constants.WHEEL_BASE*PI*(10))/180, 3);
                    Robot.getDrive().encoderDrive(0.4, 35, 35, 4);
                }

                Robot.getSlammer().autoSlam();
                Robot.getDrive().encoderDrive(0.5, -5, -5, 2);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        Robot.getSlammer().setPower(-1);
//                        sleep(300);
//                        Robot.getSlammer().setPower(0);
                    }
                }).start();

                if(30-t.seconds()>=4){
                    Robot.getDrive().encoderDrive(1, 4, 4, 2);
                    Robot.getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;
        }
    }
}