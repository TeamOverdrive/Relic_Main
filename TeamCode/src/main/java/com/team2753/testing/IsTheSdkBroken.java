package com.team2753.testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.RobotLog;

/**
 * Created by joshua9889 on 3/14/2018.
 */

@TeleOp
@Disabled
public class IsTheSdkBroken extends OpMode {

    @Override
    public void init() {
        RobotLog.v("+++++++++++++++++++++++++++ Init +++++++++++++++++++++++++++++++++");
    }

    @Override
    public void loop() {
        RobotLog.v("+++++++++++++++++++++++++ Finished ++++++++++++++++++++++++++++++++++");
    }
}
