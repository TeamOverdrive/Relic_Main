package com.team2753.auto.SPLINE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.SPLINE.paths.BlueFar_Paths;
import com.team2753.splines.field.JoshuaField;

@Autonomous
@Disabled
public class BLUE_FAR extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        BlueFar_Paths.calculateBlueFar(new JoshuaField());

        waitForStart("BlueCloseMTI", true);
    }
}
