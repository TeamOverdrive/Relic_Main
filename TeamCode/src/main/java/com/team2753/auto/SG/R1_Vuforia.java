package com.team2753.auto.SG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team2753.Team2753Linear;

import static com.team2753.auto.AutoParams.AUTO;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/10/2018.
 */

@Autonomous(name = "Red 1 Vuforia", group = "Vuforia")
@Disabled
@Deprecated
public class R1_Vuforia extends Team2753Linear{

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("R1 Vuforia", AUTO);

        int i = 0;

        while(opModeIsActive() && i == 0) {

            //score cryptokey
            SetStatus("Cryptokey");
            //glyphScoreR1(this);

            //grab more glyphs
            SetStatus("Multiglyph");
            telemetry.update();
            //multiGlyphR1(13);

            //score extra glyphs

            //park

            i++;
        }

        finalAction();
    }
}
