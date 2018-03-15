package org.firstinspires.ftc.teamcode.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Team2753Linear;
import org.firstinspires.ftc.teamcode.auto.AutoParams;

/**
 * Created by joshua9889 on 3/14/2018.
 */

@Autonomous
public class R_Back_Multi extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("RBack", AutoParams.AUTO, true);
        getDrive().zeroSensors();

        ElapsedTime t = new ElapsedTime();
        getJewel().hit(this.jewel_Color, Jewel_Color.Red);

        getDrive().encoderDrive(0.6, 35, 35, 4);
        getDrive().turnCW(90, 0.3, 3);
        getDrive().encoderDrive(0.6, 7, 7, 3);
        getSlammer().stopperUp();
        sleep(400);
        getIntake().releaseIntake();
        getIntake().intake();
        getRelic().setAngles(0,0);
        getDrive().encoderDrive(0.5, -5, -5, 2);
        sleep(500);
        getDrive().encoderDrive(0.3, -30, -30, 6);

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
        sleep(1000);
        getIntake().intake();
        getDrive().encoderDrive(0.5, 40, 40, 5);
    }
}
