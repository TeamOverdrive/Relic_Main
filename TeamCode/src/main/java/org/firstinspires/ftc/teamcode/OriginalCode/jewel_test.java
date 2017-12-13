package org.firstinspires.ftc.teamcode.OriginalCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.OriginalCode.AutoSuper;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 11/4/2017.
 */

@Autonomous(name="Jewel Test", group = "Test")
@Disabled
public class jewel_test extends AutoSuper {

    @Override
    public void runOpMode()
    {

        super.runOpMode();

        /*Vuforia*//*
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        //constructor not to initialize the camera
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        //Vuforia License
        parameters.vuforiaLicenseKey = "AeUsQDb/////AAAAGXsDAQwNS0SWopXJpAHyRntcnTcoWD8TnsR6PWGX9OwmlIhNxQgn8RX/1cH2RXXTsuSkHh6OjfMoCuHt35rhumaUsLnk8MZZJ7P9PEu+uSsUbH1hHcnnB6GzJnX/FqlZJX5HWWfeQva5s4OHJEwSbPR2zxhkRxntAjeuIPGVSHeIseAikPB0NF0SqEiPZea+PWrxpryP/bxKqy7VA77krKFtgDi6amam+vWvBCqyIo6tXxbo0w8q/HCXo4v/4UYyoFLRx1l1d2Wya5an5SwFfU3eKxy0BYc3tnsaaDJww59RNJ6IK9D3PZM+oPDrmF9ukQrc/jw+u+6Zm4wQHieHt9urSwLR7dgz0V3aatDx1V7y";

        //Camera Direction
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        //Load targets
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
*/
        waitForStart();

        jewelArm.setPosition(ARMDOWN);
        sleep(1000);
        pushJewel(RED);
        sleep(1000);
        jewelArm.setPosition(ARMUP);
        sleep(1000);

        //encoderDrive(0.6, 5, 5, 10);

        /*if (vuMark != RelicRecoveryVuMark.UNKNOWN){
            if(vuMark == RelicRecoveryVuMark.RIGHT) {
                telemetry.addData("Vumark", "Right visible");
                telemetry.update();
                //drive to right column
                encoderDrive (DRIVE_SPEED, 30, 30, 4);
            }

            else if (vuMark == RelicRecoveryVuMark.CENTER){
                telemetry.addData("Vumark", "Center visible");
                telemetry.update();
                //drive to center column
                encoderDrive(DRIVE_SPEED, 40, 40, 4);
            }
            else if (vuMark == RelicRecoveryVuMark.LEFT){
                telemetry.addData("Vumark", "Left visible");
                telemetry.update();
                //drive to left column
            }
        }
        else
        {
            telemetry.addData("VuMark", "is not visible");
            telemetry.update();
        }*/

        //put glyph in key

    }

}
