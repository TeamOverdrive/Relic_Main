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

@Autonomous(group = "3 Glyph")
public class B_Back_3Glyph extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("BBack", AutoParams.AUTO, true );
        Robot.getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        Robot.getJewel().hit(this.jewel_Color, Jewel_Color.Blue);

        Robot.getDrive().encoderDrive(0.3, -36, -36, 4);

        Robot.getDrive().encoderDrive(0.3, 0, (WHEEL_BASE*PI*(82))/180, 4);
        Robot.getDrive().encoderDrive(0.3, 7, 7, 3);
        Robot.getSlammer().stopperUp();
        sleep(600);
        Robot.getDrive().encoderDrive(0.3, -10, -10, 3);
//        getDrive().turnCW(56, 0.3, 3);
//        getDrive().encoderDrive(0.3, 7, 7, 3);
//        getSlammer().stopperUp();
//        sleep(600);
//        getDrive().encoderDrive(0.3, -7, -7, 4);
//        getSlammer().stopperDown();
//        getIntake().releaseIntake();
//        getDrive().turnCW(98-56, 0.6, 3);
//        //getDrive().encoderDrive(0.6, -(WHEEL_BASE*PI*(90-70))/180, 0, 3);
//
//        getDrive().encoderDrive(0.6, -5, -5, 4);
//
//        getSlammer().stopperDown();
//        getIntake().intake();
//        getRelic().setWristPostion(50);
//        getRelic().setAngles(0,0);
//
//        getIntake().reverse();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                sleep(500);
//                getIntake().intake();
//            }
//        }).start();
//
//        getDrive().encoderDrive(0.3, -25, -25, 4);
//
//        while (opModeIsActive() && t.seconds()<16 && !(getIntake().frontDetected() || getIntake().backDetected())){
//            Thread.yield();
//            telemetry.clearAll();
//            telemetry.addData("Front", getIntake().frontDetected());
//            telemetry.addData("Back", getIntake().backDetected());
//            telemetry.update();
//        }

    }
}
