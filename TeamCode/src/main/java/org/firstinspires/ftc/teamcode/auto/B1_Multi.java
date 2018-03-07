package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 2/20/2018.
 */

@Autonomous(name = "Blue 1 Multiglyph", group = "multiglyph")
public class B1_Multi extends Team2753Linear{

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
        currentOpMode.setValue("B1 Multiglyph");
        telemetry.update();
        initializeRobot(this, AUTO);
        startVuforia(BACK);
        initJewelDetector();

        //Waiting for start
        status.setValue("Initialized, Waiting for Start");
        telemetry.update();
        waitForStart(this);

        status.setValue("Running OpMode");
        telemetry.update();

        int i = 0;

        while(opModeIsActive() && i == 0) {

            //Vuforia
            columnVote(this);
            closeVuforia();

            //Jewel Phase
            phase.setValue("Jewel");
            telemetry.update();
            //initJewelDetector();
            enableJewelDetector();
            jewelBlue(this);
            disableJewelDetector();


            //score cryptokey
            phase.setValue("Cryptokey");
            telemetry.update();
            glyphScoreB1(this);


            //grab more glyphs
            phase.setValue("Multiglyph");
            telemetry.update();
            multiGlyphPos1();

            //park
            phase.setValue("Parking");
            telemetry.update();

            i++;
        }

        finalAction();
    }
}
