package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.teamcode.AutoParams.RED;
import static org.firstinspires.ftc.teamcode.AutoParams.jewelArmDelayMS;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/10/2018.
 */

@Autonomous(name = "Red 2 Vuforia", group = "Vuforia")
public class R2_Vuforia extends Team2753Linear{

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Running", "Red 2 Vuforia");
        telemetry.update();
        waitForStart(this, true);

        int i = 0;

        while(opModeIsActive() && i == 0) {

            //grab cryptokey
            initialLift(RED);

            //lower jewel arm
            getJewel().deploy();
            sleep(jewelArmDelayMS);

            // Vote and then hit jewel off
            jewelRed();

            //raise jewel arm
            getJewel().retract();
            sleep(jewelArmDelayMS);

            //score cryptokey
            glyphScoreR2();

            //grab more glyphs
            multiGlyphR2(13);

            //score extra glyphs

            //park

            i++;
        }

        finalAction();

    }
}
