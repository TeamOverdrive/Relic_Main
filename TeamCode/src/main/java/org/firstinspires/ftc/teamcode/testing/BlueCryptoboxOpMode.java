
package org.firstinspires.ftc.teamcode.testing;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetector;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Blue Cryptobox Detector", group="DogeCV")
@Disabled
public class BlueCryptoboxOpMode extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();


     private CryptoboxDetector cryptoboxDetector = null;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");


        cryptoboxDetector = new CryptoboxDetector();
        cryptoboxDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        cryptoboxDetector.detectionMode = CryptoboxDetector.CryptoboxDetectionMode.BLUE;

        cryptoboxDetector.rotateMat = false;

        cryptoboxDetector.enable();


    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void start() {
        runtime.reset();


    }

    @Override
    public void loop() {



        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("isCryptoBoxDetected", cryptoboxDetector.isCryptoBoxDetected());
        telemetry.addData("isColumnDetected ",  cryptoboxDetector.isColumnDetected());

        telemetry.addData("Column Left ",  cryptoboxDetector.getCryptoBoxLeftPosition());
        telemetry.addData("Column Center ",  cryptoboxDetector.getCryptoBoxCenterPosition());
        telemetry.addData("Column Right ",  cryptoboxDetector.getCryptoBoxRightPosition());
        //telemetry.addData("Cryptobox Position", cryptoboxDetector.getCryptoBoxPositions());


    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        cryptoboxDetector.disable();
    }

}
