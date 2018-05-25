package com.team2753.testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by joshua9889 on 5/24/2018.
 */

@TeleOp
@Disabled
public class Telems extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.setCaptionValueSeparator("");
        telemetry.addData("12345678901234567890123456789012345678901234567890", "");
        telemetry.update();
        waitForStart();
    }
}
