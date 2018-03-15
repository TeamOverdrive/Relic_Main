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

@Autonomous
public class B_Far_Multi extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("BFar", AutoParams.AUTO, true);
        getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        getJewel().hit(this.jewel_Color, Jewel_Color.Blue);

        switch (RelicRecoveryVuMark.CENTER){
            case LEFT:
                getDrive().encoderDrive(0.6, -23, -23, 5);
                getDrive().encoderDrive(0.6, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90
                getDrive().encoderDrive(0.5, -6, -6, 3);
                getDrive().turnCW(52, 0.3, 3); // -90-52
                getSlammer().stopperUp();
                sleep(400);
                getIntake().releaseIntake();
                getDrive().encoderDrive(0.5, -10, -10, 2);
                getRelic().setAngles(0,0);
                sleep(400);
                getSlammer().stopperDown();
                getIntake().intake();
                getDrive().turnCW(8, 1.0, 3); // -150
                getDrive().encoderDrive(1.0, -35, -35, 6);
                while (opModeIsActive() && t.seconds()>24 && !(getIntake().frontDetected() || getIntake().backDetected())){
                    Thread.yield();
                    telemetry.clearAll();
                    telemetry.addData("Front", getIntake().frontDetected());
                    telemetry.addData("Back", getIntake().backDetected());
                    telemetry.update();
                }
                sleep(500);
                getIntake().reverse();

                getDrive().encoderDrive(1.0, 0, (WHEEL_BASE*PI*5)/180, 3); // -158
                getIntake().intake();

                getDrive().encoderDrive(0.8, 35, 35, 3);
                getDrive().encoderDrive(0.2, 5, 5, 2);
                getIntake().reverse();
                getSlammer().autoSlam();
                getDrive().encoderDrive(0.3, -4, -4, 1);
                getDrive().encoderDrive(1.0, 3, 3, 1);
                getDrive().encoderDrive(1.0, -5, -5, 4);
                break;
            case CENTER:
                getDrive().encoderDrive(0.5, -23, -23, 6);
                getDrive().encoderDrive(0.4, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90
                getDrive().encoderDrive(0.5, -11, -11, 5);
                getDrive().turnCW(54, 0.2, 3); // -90-54
                getSlammer().stopperUp();
                sleep(400);
                getIntake().releaseIntake();
                getDrive().encoderDrive(0.5, -10, -10, 2);
                getRelic().setAngles(0,0);
                sleep(400);
                getSlammer().stopperDown();
                getIntake().intake();
                getDrive().turnCW(15, 1.0, 3); // -90-54-15
                getDrive().encoderDrive(0.6, -25, -25, 6);

                sleep(300);
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
                sleep(500);
                getIntake().reverse();
                sleep(500);
                getIntake().intake();

                if(farther) {
                    getDrive().encoderDrive(1.0, 0, (WHEEL_BASE*PI*2)/180, 3);
                    getDrive().encoderDrive(0.8, 25, 25, 3);
                    getDrive().turnCW(5, 0.5, 4);
                } else {
                    getDrive().encoderDrive(1.0, 0, (WHEEL_BASE*PI*1)/180, 3);
                    getDrive().encoderDrive(0.8, 27, 27, 3);
                    getDrive().turnCW(5, 0.5, 4);
                }

                getDrive().encoderDrive(0.3, 5, 5, 2);
                getIntake().reverse();
                getSlammer().autoSlam();
                getDrive().encoderDrive(0.3, -4, -4, 1);
                getDrive().encoderDrive(1.0, 3, 3, 1);
                getDrive().encoderDrive(1.0, -5, -5, 4);
                break;
            case RIGHT:
                getDrive().encoderDrive(0.5, -23, -23, 6);
                getDrive().encoderDrive(0.4, -(WHEEL_BASE*PI*90)/180, 0, 3); // -90
                getDrive().encoderDrive(0.5, -18, -18, 5);
                getDrive().turnCW(54, 0.2, 3);
                getSlammer().stopperUp();
                sleep(400);
                getIntake().releaseIntake();
                getDrive().encoderDrive(0.5, -5, -5, 1);
                getRelic().setAngles(0,0);
                sleep(400);
                getSlammer().stopperDown();
                getIntake().intake();
                getDrive().turnCW(90-54, 1.0, 3); // -90-54-15
                getDrive().encoderDrive(0.6, -25, -25, 6);

                boolean fartherR = false;
                if(!(getIntake().frontDetected() || getIntake().backDetected())){
                    getIntake().reverse();
                    sleep(400);
                    getIntake().intake();
                    getDrive().encoderDrive(0.2, -7, -7, 3);
                    fartherR = true;
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

                if(fartherR) {
//                    if(getDrive().getGyroAngleDegrees()<360 && getDrive().getGyroAngleDegrees()>-360)
//                        getDrive().encoderDrive(1.0, 0, (WHEEL_BASE*PI*(-150+getDrive().getGyroAngleDegrees()+180))/180, 3);
//                    else
                        getDrive().encoderDrive(1.0, 0, (WHEEL_BASE*PI*4)/180, 3);

                    getIntake().intake();
                    getDrive().encoderDrive(0.8, 35, 35, 3);
                } else {
//                    if(getDrive().getGyroAngleDegrees()<360 && getDrive().getGyroAngleDegrees()>-360)
//                        getDrive().encoderDrive(1.0, 0, (WHEEL_BASE*PI*(-160+getDrive().getGyroAngleDegrees()+180))/180, 3);
//                    else
                        getDrive().encoderDrive(1.0, 0, (WHEEL_BASE*PI*3)/180, 3);
                    getIntake().intake();
                    getDrive().encoderDrive(0.8, 30, 30, 3);
                }

                getIntake().reverse();
                getSlammer().autoSlam();
                getDrive().encoderDrive(0.3, -4, -4, 1);
                getDrive().encoderDrive(1.0, 3, 3, 1);
                getDrive().encoderDrive(1.0, -5, -5, 4);
                break;
        }
    }
}