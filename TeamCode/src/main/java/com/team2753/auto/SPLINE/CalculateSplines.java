package com.team2753.auto.SPLINE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.team2753.splines.field.JoshuaField;

/**
 * Created by joshua9889 on 6/1/2018.
 */

@Autonomous
@Disabled
public class CalculateSplines extends LinearOpMode {
    @Override
    public void runOpMode(){
        PathStorage.calculateAll(new JoshuaField());
    }
}
