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

        waitForStart(this, true);

        int i = 0;

        while(opModeIsActive() && i == 0) {

            //grab cryptokey
            getHand().grabBackClose();
            sleep(500);
            initialLift();

            //lower jewel arm
            getJewel().deploy();
            sleep(300);

            // Vote and then hit jewel off
            jewelRed();

            //raise jewel arm
            getJewel().retract();
            sleep(1000);

            //score cryptokey
            //glyphScoreR1();

            getDrive().encoderDrive(0.3, 36, 36, 4);
            getDrive().turnCW(90, 0.3, 4);
            getDrive().encoderDrive(0.3, 12, 12, 4);

            getHand().grabBackOpen();

            getDrive().encoderDrive(0.3, -6 ,-6, 3);

            //Get 2nd glyph

            //park

            i++;
        }

        finalAction();
    }
}
