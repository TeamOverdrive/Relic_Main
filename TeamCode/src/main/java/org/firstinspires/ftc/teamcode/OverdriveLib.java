package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Used to store math and things
 * Created by joshua9889 on 12/10/2017.
 */

public class OverdriveLib {

    // Scale driver joystick input to make it easier to control a low speeds
    static double scaleInput(double dVal)   {
        double[] scaleArray = {
                0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24, 0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00
                //to use a different scale, list alternate scale values here and comment out the line above
        };

        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16)  {
            index = 16;
        }

        double dScale = 0.0;
        if (dVal < 0)  {
            dScale = -scaleArray[index];
        }  else {
            dScale = scaleArray[index];
        }

        return dScale;
    }

    static double scaleLift(double dVal)   {
        double[] scaleArray = {
                0.0, 0.05, 0.07, 0.10, 0.12, 0.15, 0.2, 0.3, 0.35, 0.4, 0.45, 0.50, 0.55, 0.6, 0.65, 0.7, 0.75
                //to use a different scale, list alternate scale values here and comment out the line above
        };

        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16)  {
            index = 16;
        }

        double dScale = 0.0;
        if (dVal < 0)  {
            dScale = -scaleArray[index];
        }  else {
            dScale = scaleArray[index];
        }

        return dScale;
    }

    // Still need to test this code
    class VuMark{
        VuforiaLocalizer vuforia;

        public void init(LinearOpMode linearOpMode){
            int cameraMonitorViewId = linearOpMode.hardwareMap.appContext.getResources().getIdentifier
                    ("cameraMonitorViewId", "id", linearOpMode.hardwareMap.appContext.getPackageName());
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
        }
    }
}
