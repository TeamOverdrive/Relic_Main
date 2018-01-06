package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;
import org.firstinspires.ftc.teamcode.VuMark;

import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurn;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/12/2017.
 */

@Autonomous(name = "Red 1 Jewel")
public class R1_Jewel extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {

        //Init
        //startVuforia();

        waitForStart(this, true);


        int i = 0;

        while(opModeIsActive() && i == 0) {

            getHand().grabBackClose();
            sleep(300);


            getJewel().deploy();
            sleep(300);

            // Vote and then hit jewel off
            jewelRed();

            //park
            getJewel().retract();
            sleep(1000);

            //score glyph
            glyphScoreR1();

            //getDrive().encoderDrive(0.8, 30, 30, 10);


            i++;
        }

        finalAction();
    }
}
