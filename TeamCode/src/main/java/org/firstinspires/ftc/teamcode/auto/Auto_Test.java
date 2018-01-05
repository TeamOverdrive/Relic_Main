package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.teamcode.AutoParams.jewelArmDelayMS;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/16/2017.
 */

@Autonomous
public class Auto_Test extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {

        //this.startVuforia();

        waitForStart(this, true);

        //glyphLoad();

        int i = 0;

        while(opModeIsActive() && i == 0) {

            getHand().clawTest();

            /*getHand().frontClose();
            sleep(300);

            getJewel().deploy();
            sleep(jewelArmDelayMS);

            jewelRed();

            getJewel().retract();
            sleep(jewelArmDelayMS);

            glyphScoreR1();
            */

            i++;
        }

        finalAction();

    }
}
