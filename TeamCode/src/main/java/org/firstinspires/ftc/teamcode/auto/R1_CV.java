package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.RED;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoTurnSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelArmDelayMS;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/10/2018.
 */

@Autonomous(name = "Red 1 CV", group = "CV")
public class R1_CV extends Team2753Linear{

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("R1 CV", AUTO, true);

        int i = 0;

        while(opModeIsActive() && i == 0) {

            //score cryptokey
            SetStatus("Cryptokey");
            glyphScoreR1(this);

            //grab more glyph
            SetStatus("Multiglyph");
            //multiGlyphPos1(13);

            //score extra glyphs

            //park
            SetStatus("Parking");
            telemetry.update();

            i++;
        }

        finalAction();
    }
}
