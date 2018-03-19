package org.firstinspires.ftc.teamcode.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Team2753Linear;
import org.firstinspires.ftc.teamcode.auto.AutoParams;

import static java.lang.Math.PI;
import static org.firstinspires.ftc.teamcode.subsystems.Drive.WHEEL_BASE;

/**
 * Created by joshua9889 on 3/14/2018.
 */

@Autonomous(group = "5 Glyph")
@Disabled
public class R_Back_5Glyph extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("RBack", AutoParams.AUTO, true);
        getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        getJewel().hit(this.jewel_Color, Jewel_Color.Red);

        getDrive().encoderDrive(0.5, 34.5, 34.5, 3);
        getDrive().turnCW(90, 0.5, 3);
        getDrive().encoderDrive(0.7, 8, 8, 2);

        getSlammer().stopperUp();
        sleep(400);
        getIntake().releaseIntake();
        getDrive().encoderDrive(1, -8, -8, 1.6);
        getIntake().intake();
        getRelic().setAngles(0,0);
        getSlammer().stopperDown();
        sleep(200);

        getDrive().encoderDrive(0.4, -28, -28, 3);
        getIntake().reverse();
        sleep(400);

        boolean farther = false;
        if(!getIntake().frontDetected() && !getIntake().backDetected()){
            getIntake().intake();
            getDrive().encoderDrive(0.2, -7, -7, 3);
            getIntake().reverse();
            sleep(1000);
            farther = true;
        }

        getIntake().intake();
//        getDrive().encoderDrive(1, 0, (WHEEL_BASE*PI*7)/180, 2);
        getDrive().encoderDrive(1, 37, 37, 3);
        getSlammer().autoSlam();
        getDrive().encoderDrive(1, -5, -5, 2);
        getSlammer().setPower(-1);
        sleep(1000);
        getSlammer().stop();
        getSlammer().stopperDown();
        getIntake().intake();
        getDrive().encoderDrive(1, -37, -37, 3);
        getDrive().encoderDrive(1, (WHEEL_BASE*PI*9)/180, 0, 2);
        getDrive().encoderDrive(1, 40, 40, 3);
        getSlammer().autoSlam();
        getDrive().encoderDrive(1, -4, -4, 2);
    }
}
