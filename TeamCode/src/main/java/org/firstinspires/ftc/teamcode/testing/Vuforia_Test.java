package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/31/2018.
 */

@Autonomous
@Disabled
public class Vuforia_Test extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {


        //Initialize Robot
        telemetry.addData("Status","Initializing...");
        telemetry.addData("Current Op Mode","Auto Test");
        telemetry.update();
        initializeRobot(this, AUTO);
        startVuforia(BACK);

        telemetry.addData("Status","Initialized");
        telemetry.update();

        initColumnVote(this);

        telemetry.addData("Status", "Waiting for start");
        telemetry.update();

        while(!isStarted()){}
        resetRuntime();

        int i = 0;
        while(opModeIsActive() && i == 0){

            columnVote(this);

            waitForTick(10000); //wait ten seconds.

            i++;
        }
    }
}
