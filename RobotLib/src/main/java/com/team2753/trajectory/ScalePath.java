package com.team2753.trajectory;

import com.team254.lib_2014.trajectory.Path;

/**
 * Created by joshua9889 on 5/26/2018.
 */

public class ScalePath {
    public static void calculate(Path original, double scale_value){
        original.getLeftWheelTrajectory().scale(scale_value);
        original.getRightWheelTrajectory().scale(scale_value);
    }
}
