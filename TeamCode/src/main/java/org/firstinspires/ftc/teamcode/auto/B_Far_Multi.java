package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Team2753Linear;

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

        ElapsedTime t = new ElapsedTime();
        getJewel().hit(this.jewel_Color, Jewel_Color.Blue);

        getDrive().encoderDrive(0.45, -24, -24, 5);
        getDrive().encoderDrive(0.4, -(WHEEL_BASE*PI*90)/180, 0, 3);
        getDrive().encoderDrive(0.5, -5, -5, 3);
        getDrive().turnCW(50, 0.2, 3);
        getSlammer().stopperUp();
        sleep(200);
        getDrive().encoderDrive(0.3, 2, 2, 3);
        getIntake().releaseIntake();
        getDrive().encoderDrive(0.5, -10, -10, 2);
        sleep(400);
        getSlammer().stopperDown();
        getIntake().intake();
        getDrive().turnCW(15, 0.5, 3);
        getDrive().encoderDrive(.7, -45, -45, 6);
        getDrive().encoderDrive(0.2, -3, -3, 3);
        getDrive().turnCW(5, 0.4, 5);

        while (t.seconds()>24){
            Thread.yield();
        }

        getDrive().encoderDrive(0.6, 50, 50, 3);
        getSlammer().autoSlam();
        getDrive().encoderDrive(1, -4, -4, 1);

    }
}
