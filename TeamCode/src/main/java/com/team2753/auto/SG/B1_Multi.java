package com.team2753.auto.SG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.team2753.Team2753Linear;

import static com.team2753.auto.AutoParams.AUTO;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 2/20/2018.
 */

@Autonomous(name = "Blue 1 Multiglyph", group = "multiglyph")
@Disabled
@Deprecated
public class B1_Multi extends Team2753Linear{

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("B1 Multiglyph", AUTO);

        int i = 0;

        while(opModeIsActive() && i == 0) {


            SetStatus("Cryptokey");
            //glyphScoreB1(this);


            //grab more glyphs
            SetStatus("Multiglyph");

           // multiGlyphPos1();


            //park
            SetStatus("Parking");
            telemetry.update();

            i++;
        }

        finalAction();
    }
}
