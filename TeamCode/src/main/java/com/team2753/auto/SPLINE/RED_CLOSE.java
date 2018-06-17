package com.team2753.auto.SPLINE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.SPLINE.paths.RedClose_Paths;
import com.team2753.splines.field.JoshuaField;

@Autonomous
public class RED_CLOSE extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        RedClose_Paths.calculateRedClose(new JoshuaField());

        waitForStart("Red", true);
    }
}
