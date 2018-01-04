package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

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

            getJewel().deploy();
            sleep(300);

            jewelRed();

            getJewel().retract();
            sleep(300);

            scoreGlyph();
            sleep(5000);

            i++;
        }

        finalAction();

    }
}
