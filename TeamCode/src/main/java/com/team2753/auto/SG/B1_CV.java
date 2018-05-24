package com.team2753.auto.SG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.team2753.Team2753Linear;

import static com.team2753.auto.AutoParams.AUTO;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/10/2018.
 */
@Autonomous(name = "Blue 1 CV", group = "CV")
@Disabled
@Deprecated
public class B1_CV extends Team2753Linear {

    @Override
    public void runOpMode() throws InterruptedException {


        waitForStart("B1 CV", AUTO);

        int i = 0;

        while(opModeIsActive() && i == 0) {

            //score cryptokey
            SetStatus("Cryptokey");
            //glyphScoreB1(this);


            //grab more glyphs
            SetStatus("Multiglyph");
            //multiGlyphPos1(20);

            //park
            SetStatus("Parking");
            telemetry.update();

            i++;
        }

        finalAction();
    }
}
