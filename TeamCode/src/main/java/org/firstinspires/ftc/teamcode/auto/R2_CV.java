package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.RED;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.TELEOP;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoTurnSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelArmDelayMS;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/10/2018.
 */

@Autonomous(name = "Red 2 CV", group = "CV")
public class R2_CV extends Team2753Linear {

    @Override
    public void runOpMode() throws InterruptedException {

        //Set up telemetry
        telemetry.setAutoClear(false);
        Telemetry.Item status = telemetry.addData("Status", "Initializing");
        Telemetry.Item currentOpMode = telemetry.addData("Running", "UNKNOWN");
        Telemetry.Item phase = telemetry.addData("Phase", "Init Routine");
        telemetry.update();

        //Initialize
        status.setValue("Initializing...");
        currentOpMode.setValue("R2 CV");
        telemetry.update();
        initializeRobot(this, AUTO);
        startVuforia(BACK);

        //Waiting for start
        status.setValue("Initialized, Waiting for Start");
        telemetry.update();

        waitForStart(this);
        status.setValue("Running OpMode");

        int i = 0;

        while(opModeIsActive() && i == 0) {

            columnVote(this);
            closeVuforia();

            //Jewel Phase
            phase.setValue("Jewel");
            telemetry.update();

            initJewelDetector();
            enableJewelDetector();
            jewelRed(this);
            disableJewelDetector();

            //score cryptokey
            phase.setValue("Cryptokey");
            telemetry.update();
            glyphScoreR2();

            //grab more glyphs
            phase.setValue("Multiglyph");
            telemetry.update();
            //multiGlyphPos2(13);

            //score extra glyphs

            //park
            phase.setValue("Parking");
            telemetry.update();


            i++;
        }

        finalAction();

    }
}
