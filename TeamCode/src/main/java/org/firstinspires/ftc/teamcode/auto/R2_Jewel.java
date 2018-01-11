package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurn;

/**
 * Same as Auto_B1, but with new backend
 * See this for an example(it's a little jank, but it works OK): http://bit.ly/2AtqtkR
 * Created by joshua9889 on 12/10/2017.
 */
@Autonomous(name = "Red 2 Jewel")
public class R2_Jewel extends Team2753Linear {
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

            //score cryptokey
            //glyphScoreR2();
            getDrive().encoderDrive(0.3, 24, 24, 4);
            getDrive().turnCCW(90, 0.3, 4);
            getDrive().encoderDrive(0.3, 8, 8, 4);
            getDrive().turnCW(90, 0.3, 3);
            getDrive().encoderDrive(0.3, 15, 15, 4);

            getHand().grabBackOpen();

            getDrive().encoderDrive(0.3, -6 ,-6, 3);


            //score 2nd glyph

            //park

            i++;
            getJewel().retract();
            sleep(1000);
        }

        finalAction();
    }
}
