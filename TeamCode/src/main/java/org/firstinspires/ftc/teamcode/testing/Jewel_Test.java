package org.firstinspires.ftc.teamcode.testing;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoTurnSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelArmDelayMS;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelTurn;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelTurnSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelTurnTimeoutS;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/27/2018.
 */

@Autonomous(name = "Jewel Test",group = "Jewel")
@Disabled
public class Jewel_Test extends Team2753Linear {

    private JewelDetector jewelDetector = null;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initializing...");
        telemetry.update();
        initializeRobot(this, AUTO);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //initJewelDetector();
        jewelDetector = new JewelDetector();
        jewelDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        //Jewel Detector Settings
        jewelDetector.areaWeight = 0.02;
        //jewelDetector.detectionMode = JewelDetector.JewelDetectionMode.PERFECT_AREA;
        jewelDetector.detectionMode = JewelDetector.JewelDetectionMode.MAX_AREA;
        jewelDetector.perfectArea = 1600;
        jewelDetector.debugContours = false;
        jewelDetector.maxDiffrence = 15;
        jewelDetector.ratioWeight = 15;
        jewelDetector.minArea = 800;
        //getPhoneServo().jewelPosition();

        waitForStart(this);

        int i = 0;
        while(opModeIsActive() && i == 0) {

            enableJewelDetector();

            jewelRed(this);

            disableJewelDetector();

            i++;

        }
    }
}
