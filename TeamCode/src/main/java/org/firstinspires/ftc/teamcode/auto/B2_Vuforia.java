package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.BLUE;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelArmDelayMS;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/10/2018.
 */

@Autonomous(name = "Blue 2 Vuforia", group = "Vuforia")
@Disabled
public class B2_Vuforia extends Team2753Linear{

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("B2 Vuforia", AUTO, true);

        int i = 0;

        while (opModeIsActive() && i == 0) {
            //score cryptokey
            SetStatus("Cryptokey");
            //glyphScoreB2();

            //grab more glyphs
            SetStatus("Multiglyph");
            telemetry.update();
            //multiGlyphB2(13);

            //score extra glyphs

            //park

            i++;
        }


        finalAction();
    }
}
