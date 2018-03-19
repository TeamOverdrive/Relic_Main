package org.firstinspires.ftc.teamcode.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Team2753Linear;
import org.firstinspires.ftc.teamcode.auto.AutoParams;

import static java.lang.Math.PI;
import static org.firstinspires.ftc.teamcode.subsystems.Drive.WHEEL_BASE;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous(group = "3 Glyph")
@Disabled
public class R_Back_3Glyph extends Team2753Linear {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("RBack", AutoParams.AUTO, true);
        getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        getJewel().hit(this.jewel_Color, Jewel_Color.Red);


        switch (RelicRecoveryVuMark.RIGHT){
            case RIGHT:
                boolean rightFarther = false;
                getDrive().encoderDrive(0.6, 21, 21, 3);
                getDrive().encoderDrive(0.6, 0, (WHEEL_BASE*PI*(90))/180, 3);
                getDrive().encoderDrive(0.6, 2, 2, 3);
                getSlammer().stopperUp();
                sleep(400);
                getIntake().releaseIntake();
                getRelic().setAngles(0,0);
                getRelic().setWristPostion(64);
                getRelic().close();
                getDrive().encoderDrive(0.7, -4, -4, 3);
                getSlammer().stopperDown();
                getIntake().intake();

                getDrive().encoderDrive(0.3, -20, -20, 3);
                getDrive().encoderDrive(0.2, -5, -5, 2);
                getRelic().lock();

                telemetry.clearAll();
                telemetry.addData("Front", getIntake().frontDetected());
                telemetry.addData("Back", getIntake().backDetected());
                telemetry.update();

                int counter = 0;
                for (int i=0;i<30;i++){
                    if((getIntake().frontDetected() && getIntake().backDetected()))
                        counter++;
                }

                if(counter<10){
                    rightFarther = true;
                    sleep(400);
                    getIntake().reverse();
                    sleep(400);
                    getIntake().intake();
                    getDrive().encoderDrive(0.4, 3, 3, 3);
                    getDrive().encoderDrive(0.4, -6, -6, 3);
                    while (opModeIsActive() && t.seconds()<18 && !(getIntake().frontDetected() && getIntake().backDetected())){
                        Thread.yield();
                        telemetry.clearAll();
                        telemetry.addData("Front", getIntake().frontDetected());
                        telemetry.addData("Back", getIntake().backDetected());
                        telemetry.update();
                    }
                    getIntake().reverse();
                    sleep(1000);
                } else {
                    rightFarther = false;
                    sleep(500);
                    getIntake().reverse();
                    sleep(1000);
                }

                getIntake().setPower(0.5);

                if(rightFarther){
                    getDrive().encoderDrive(0.4, (WHEEL_BASE*PI*(17))/180, 0, 3);
                    getDrive().encoderDrive(0.4, 36, 36, 4);
                } else {
                    getDrive().encoderDrive(0.4, (WHEEL_BASE*PI*(25))/180, 0, 3);
                    getDrive().encoderDrive(0.4, 32, 32, 4);
                }

                getSlammer().autoSlam();
                getDrive().encoderDrive(0.5, -5, -5, 2);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getSlammer().setPower(-1);
                        sleep(300);
                        getSlammer().setPower(0);
                    }
                }).start();

                if(30-t.seconds()>=4){
                    getDrive().encoderDrive(1, 4, 4, 2);
                    getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;

            case CENTER:
                boolean centerFarther = false;
                getDrive().encoderDrive(0.6, 22, 22, 4);
                getDrive().encoderDrive(0.6, 0, (WHEEL_BASE*PI*(57))/180, 3);
                getDrive().encoderDrive(0.6, 7, 7, 3);
                getSlammer().stopperUp();
                sleep(400);
                getIntake().releaseIntake();
                getDrive().encoderDrive(0.6, -(WHEEL_BASE*PI*(90-57))/180, 0, 3);
                getDrive().encoderDrive(0.6, -5, -5, 2);
                getSlammer().stopperDown();
                getIntake().intake();
                getRelic().setAngles(0,0);
                getRelic().setWristPostion(64);
                getRelic().close();

                getDrive().encoderDrive(0.3, -20, -20, 3);
                getDrive().encoderDrive(0.2, -5, -5, 2);

                telemetry.clearAll();
                telemetry.addData("Front", getIntake().frontDetected());
                telemetry.addData("Back", getIntake().backDetected());
                telemetry.update();

                int counter2 = 0;
                for (int i=0;i<30;i++){
                    if((getIntake().frontDetected() && getIntake().backDetected()))
                        counter2++;
                }

                if(counter2<10){
                    centerFarther = true;
                    sleep(400);
                    getIntake().reverse();
                    sleep(400);
                    getIntake().intake();
                    getDrive().encoderDrive(0.4, 3, 3, 3);
                    getDrive().encoderDrive(0.4, -6, -6, 3);
                    while (opModeIsActive() && t.seconds()<18 && !(getIntake().frontDetected() && getIntake().backDetected())){
                        Thread.yield();
                        telemetry.clearAll();
                        telemetry.addData("Front", getIntake().frontDetected());
                        telemetry.addData("Back", getIntake().backDetected());
                        telemetry.update();
                    }
                    getIntake().reverse();
                    sleep(1000);
                } else {
                    centerFarther = false;
                    sleep(500);
                    getIntake().reverse();
                    sleep(1000);
                }

                getIntake().intake();

                if(centerFarther){
                    getDrive().encoderDrive(0.4, 0, (WHEEL_BASE*PI*(-7))/180, 3);
                    getDrive().encoderDrive(0.4, 39, 39, 4);
                } else {
                    getDrive().encoderDrive(0.4, 0, (WHEEL_BASE*PI*(-3))/180, 3);
                    getDrive().encoderDrive(0.4, 35, 35, 4);
                }

                telemetry.addData("Counter", counter2);
                telemetry.update();

                getSlammer().autoSlam();
                getDrive().encoderDrive(0.5, -5, -5, 2);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getSlammer().setPower(-1);
                        sleep(300);
                        getSlammer().setPower(0);
                    }
                }).start();

                if(30-t.seconds()>=4){
                    getDrive().encoderDrive(1, 4, 4, 2);
                    getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;

            case LEFT:
                boolean leftFarther = false;
                getDrive().encoderDrive(0.6, 27, 27, 4);
                getDrive().encoderDrive(0.6, 0, (WHEEL_BASE*PI*(54))/180, 3);
                getDrive().encoderDrive(0.6, 7, 7, 3);
                getSlammer().stopperUp();
                sleep(400);
                getIntake().releaseIntake();
                getDrive().encoderDrive(0.6, -(WHEEL_BASE*PI*(90-54))/180, 0, 3);
                getRelic().setAngles(0,0);
                getRelic().setWristPostion(64);
                getRelic().close();
                getDrive().encoderDrive(0.6, -5, -5, 2);
                getSlammer().stopperDown();
                getIntake().intake();

                getDrive().encoderDrive(0.3, -20, -20, 3);
                getDrive().encoderDrive(0.2, -5, -5, 2);
                getRelic().lock();

                telemetry.clearAll();
                telemetry.addData("Front", getIntake().frontDetected());
                telemetry.addData("Back", getIntake().backDetected());
                telemetry.update();

                if(!(getIntake().frontDetected() && getIntake().backDetected())){
                    leftFarther = true;
                    sleep(400);
                    getIntake().reverse();
                    sleep(400);
                    getIntake().intake();
                    getDrive().encoderDrive(0.4, 3, 3, 3);
                    getDrive().encoderDrive(0.4, -6, -6, 3);
                    while (opModeIsActive() && t.seconds()<18 && !(getIntake().frontDetected() && getIntake().backDetected())){
                        Thread.yield();
                        telemetry.clearAll();
                        telemetry.addData("Front", getIntake().frontDetected());
                        telemetry.addData("Back", getIntake().backDetected());
                        telemetry.update();
                    }
                    getIntake().reverse();
                    sleep(500);
                } else {
                    leftFarther = false;
                    sleep(500);
                    getIntake().reverse();
                    sleep(300);
                }

                getIntake().setPower(0.5);

                if(leftFarther){
                    getDrive().encoderDrive(0.4, 0, (WHEEL_BASE*PI*(9))/180, 3);
                    getDrive().encoderDrive(0.4, 39, 39, 4);
                } else {
                    getDrive().encoderDrive(0.4, 0, (WHEEL_BASE*PI*(10))/180, 3);
                    getDrive().encoderDrive(0.4, 35, 35, 4);
                }

                getSlammer().autoSlam();
                getDrive().encoderDrive(0.5, -5, -5, 2);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getSlammer().setPower(-1);
                        sleep(300);
                        getSlammer().setPower(0);
                    }
                }).start();

                if(30-t.seconds()>=4){
                    getDrive().encoderDrive(1, 4, 4, 2);
                    getDrive().encoderDrive(1, -5, -5, 2);
                }
                break;
        }
    }
}