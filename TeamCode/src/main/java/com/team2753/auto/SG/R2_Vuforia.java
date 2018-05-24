package com.team2753.auto.SG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.team2753.Team2753Linear;

import static com.team2753.auto.AutoParams.AUTO;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/10/2018.
 */

@Autonomous(name = "Red 2 Vuforia", group = "Vuforia")
@Disabled
public class R2_Vuforia extends Team2753Linear{

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("R2 Vuforia", AUTO);

            //score cryptokey
        SetStatus("Cryptokey");
        glyphScoreR2();

        //grab more glyphs
        SetStatus("Multiglyph");
        multiGlyphR2();

        //score extra glyphs

        //park


        finalAction();

    }
}
