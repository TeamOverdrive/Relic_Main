package com.team2753.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;
import com.team2753.auto.actions.JewelHitColor;

import static com.team2753.subsystems.Drive.WHEEL_BASE;
import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 3/14/2018.
 */

@Autonomous(group = "5 Glyph")
@Disabled
public class R_Back_5Glyph extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("RBack", AutoParams.AUTO, true);
        Robot.getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        runAction(new JewelHitColor(this.jewel_Color, Jewel_Color.Red));

        Robot.getDrive().encoderDrive(0.5, 34.5, 34.5, 3);
        Robot.getDrive().turnCW(90, 0.5, 3);
        Robot.getDrive().encoderDrive(0.7, 8, 8, 2);

        Robot.getSlammer().stopperUp();
        sleep(400);
        Robot.getIntake().releaseIntake();
        Robot.getDrive().encoderDrive(1, -8, -8, 1.6);
        Robot.getIntake().intake();
        Robot.getRelic().setAngles(0,0);
        Robot.getSlammer().stopperDown();
        sleep(200);

        Robot.getDrive().encoderDrive(0.4, -28, -28, 3);
        Robot.getIntake().reverse();
        sleep(400);

        boolean farther = false;
        if(!Robot.getIntake().frontDetected() && !Robot.getIntake().backDetected()){
            Robot.getIntake().intake();
            Robot.getDrive().encoderDrive(0.2, -7, -7, 3);
            Robot.getIntake().reverse();
            sleep(1000);
            farther = true;
        }

        Robot.getIntake().intake();
//        getDrive().encoderDrive(1, 0, (WHEEL_BASE*PI*7)/180, 2);
        Robot.getDrive().encoderDrive(1, 37, 37, 3);
        Robot.getSlammer().autoSlam();
        Robot.getDrive().encoderDrive(1, -5, -5, 2);
        Robot.getSlammer().setPower(-1);
        sleep(1000);
        Robot.getSlammer().stop();
        Robot.getSlammer().stopperDown();
        Robot.getIntake().intake();
        Robot.getDrive().encoderDrive(1, -37, -37, 3);
        Robot.getDrive().encoderDrive(1, (WHEEL_BASE*PI*9)/180, 0, 2);
        Robot.getDrive().encoderDrive(1, 40, 40, 3);
        Robot.getSlammer().autoSlam();
        Robot.getDrive().encoderDrive(1, -4, -4, 2);
    }
}
