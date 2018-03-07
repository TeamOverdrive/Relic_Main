package org.firstinspires.ftc.teamcode.testing;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 3/5/2018.
 */

public class PIDTest extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {
        //Set up telemetry
        telemetry.setAutoClear(false);
        Telemetry.Item status = telemetry.addData("Status", "Initializing");
        Telemetry.Item currentOpMode = telemetry.addData("Running", "UNKNOWN");
        Telemetry.Item phase = telemetry.addData("Phase", "Init Routine");
        telemetry.update();

        //Initialize Robot
        status.setValue("Initializing...");
        currentOpMode.setValue("PID Test");
        telemetry.update();
        initializeRobot(this, AUTO);

        //Waiting for start
        status.setValue("Initialized, Waiting for Start");
        telemetry.update();
        waitForStart(this);

        status.setValue("Running OpMode");
        telemetry.update();

        int i = 0;

        while(opModeIsActive() && i == 0) {

            getDrive().encoderPIDDrive(this, 24, 24, 10, 0.05, 0, 0, 0.1);
            waitForTick(2000);
            getDrive().encoderPIDDrive(this,-24, -24, 10, 0.05, 0, 0, 0.1);
            waitForTick(2000);
            getDrive().encoderPIDDrive(this, 12, 12, 10, 0.05, 0, 0, 0.1);
            waitForTick(2000);
            getDrive().encoderPIDDrive(this, -12, -12, 10, 0.05, 0, 0, 0.1);
            waitForTick(2000);
            getDrive().turnPIDCCW(this, 90, 10, 0.05, 0, 0, 0.1);
            waitForTick(2000);
            getDrive().turnPIDCW(this, 90, 10, 0.05, 0, 0, 0.1);

            i++;
        }

        finalAction();
    }
}
