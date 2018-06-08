package com.team2753.testing.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team2753.auto.AutoModeBase;


/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/27/2018.
 */

@Autonomous(name = "Jewel Test",group = "Jewel")
//@Disabled
public class Jewel_Test extends AutoModeBase {

    private Jewel_Color alliance_color = Jewel_Color.Red;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("Jewel Test", true);
        hitJewel(this.jewel_Color, Jewel_Color.Red);
    }
}
