package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.teamcode.AutoParams.BLUE;
import static org.firstinspires.ftc.teamcode.AutoParams.autoSpeed;
import static org.firstinspires.ftc.teamcode.AutoParams.autoTurnSpeed;
import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurn;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/12/2017.
 */

@Autonomous(name = "Blue 1 Jewel", group = "Stable")
public class B1_Jewel extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart(this, true);

        int i =0;

        while(opModeIsActive() && i == 0) {

            //grab cryptokey
            initialLift(BLUE);

            //lower jewel arm
            getJewel().deploy();
            sleep(300);

            // Vote and then hit jewel off
            jewelBlue();

            //raise jewel arm
            getJewel().retract();
            sleep(300);

            //score cryptokey
            //glyphScoreB1();

            getDrive().encoderDrive(autoSpeed, -36, -36, 4);
            getDrive().turnCCW(90, autoTurnSpeed, 4);
            glyphBlueScore();

            //score 2nd glyph

            //park

            i++;
        }

        finalAction();
    }
}
