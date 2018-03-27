package org.firstinspires.ftc.teamcode.testing.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Team2753Linear;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/27/2018.
 */

@Autonomous(name = "Jewel Test",group = "Jewel")
@Disabled
public class Jewel_Test extends Team2753Linear {

    private Jewel_Color alliance_color = Jewel_Color.Red;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("Jewel Test", true, true);

        Robot.getJewel().deploy(true);
        sleep(500);

        if(jewel_Color==alliance_color){
            Robot.getJewel().rightHit();
        } else {
            Robot.getJewel().leftHit();
        }

        sleep(200);

        Robot.getJewel().retract(true);
        sleep(1000);
    }
}
