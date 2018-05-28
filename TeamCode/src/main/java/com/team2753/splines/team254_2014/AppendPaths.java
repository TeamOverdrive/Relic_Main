package com.team2753.splines.team254_2014;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.Trajectory;

/**
 * Created by joshua9889 on 5/26/2018.
 */

public class AppendPaths {
    public static void appendPath(Path original, Path to_append){
        original.getLeftWheelTrajectory().append(to_append.getLeftWheelTrajectory());
        original.getRightWheelTrajectory().append(to_append.getRightWheelTrajectory());
    }
}
