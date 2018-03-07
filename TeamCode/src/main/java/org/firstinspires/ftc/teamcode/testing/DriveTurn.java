package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.Kd;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.Ki;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.Kp;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoTurnSpeed;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/30/2018.
 */

@Autonomous(name = "Drive Test", group = "test")
//@Disabled
public class DriveTurn extends Team2753Linear{

    //Runs position b1 for now bc its the closest on test field
    private static final double turnSpeed = 0.4;

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
        currentOpMode.setValue("Drive Test");
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

                getDrive().encoderProportionDrive(0.00625, 0, 0.1, 24, 24, 10);
                waitForTick(2000);
                getDrive().encoderProportionDrive(0.00625, 0, 0.1, -24, -24, 10);
                waitForTick(2000);
                getDrive().encoderProportionDrive(0.00625, 0, 0.1, 3, 3, 10);
                waitForTick(2000);
                getDrive().encoderProportionDrive(0.00625, 0, 0.1, -3, -3, 10);
                waitForTick(2000);
                getDrive().turnProportionCCW(90, 10);
                waitForTick(2000);
                getDrive().turnProportionCW(90, 10);

            i++;
        }

        finalAction();
    }
}
