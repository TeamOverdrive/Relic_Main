package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.teamcode.AutoParams.BLUE;
import static org.firstinspires.ftc.teamcode.AutoParams.jewelArmDelayMS;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/10/2018.
 */
@Autonomous(name = "Blue 1 Vuforia", group = "Vuforia")
public class B1_Vuforia extends Team2753Linear{


    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Running", "Blue 1 Vuforia");
        telemetry.update();
        waitForStart(this, true);

        int i =0;

        while(opModeIsActive() && i == 0) {

            //grab cryptokey
            initialLift(BLUE);

            //lower jewel arm
            getJewel().deploy();
            sleep(jewelArmDelayMS);

            //Hit off the blue jewel
            jewelBlue();

            //raise jewel arm
            getJewel().retract();
            sleep(jewelArmDelayMS);

            //score cryptokey
            glyphScoreB1();

            //grab more glyphs
            multiGlyphB1(13);

            //score extra glyphs

            //park

            i++;
        }

        finalAction();
    }
}
