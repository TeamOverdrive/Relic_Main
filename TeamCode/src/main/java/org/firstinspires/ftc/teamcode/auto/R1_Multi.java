package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 2/20/2018.
 */

@Autonomous(name = "Red 1 Multiglyph", group = "multiglyph")
public class R1_Multi extends Team2753Linear{

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("R1 Multiglyph", AUTO, true);

        int i = 0;

        while(opModeIsActive() && i == 0) {

            //score cryptokey
            SetStatus("Cryptokey");
            glyphScoreR1(this);

            //grab more glyphs
            SetStatus("Multiglyph");
            multiGlyphPos1();

            //score extra glyphs

            //park
            SetStatus("Parking");

            i++;
        }

        finalAction();
    }
}
