package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/10/2018.
 */

@Autonomous(name = "Blue 2 Vuforia", group = "Vuforia")
public class B2_Vuforia extends Team2753Linear{

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart(this, true);

        int i = 0;

        while (opModeIsActive() && i == 0) {

            //grab cryptokey
            getHand().grabFrontClose();
            sleep(500);
            initialLift();

            //lower jewel arm
            getJewel().deploy();
            sleep(300);

            // Vote and then hit jewel off
            jewelBlue();

            //raise jewel arm
            getJewel().retract();

            //score cryptokey0
            glyphScoreB2();
            sleep(300);

            //score 2nd glyph

            //park

            i++;
        }


        finalAction();
    }
}
