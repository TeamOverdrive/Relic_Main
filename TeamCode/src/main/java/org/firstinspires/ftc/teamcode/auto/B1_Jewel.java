package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurn;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/12/2017.
 */

@Autonomous(name = "Blue 1 Jewel")
public class B1_Jewel extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true);

        int i =0;

        while(opModeIsActive() && i == 0) {

            getHand().grabFrontClose();
            sleep(300);

            getJewel().deploy();
            sleep(300);

            // Vote and then hit jewel off
            jewelBlue();

            //park
            //getDrive().encoderDrive(0.8, -30, -30, 7);
            glyphScoreB1();


            i++;
        }

        finalAction();
    }
}
