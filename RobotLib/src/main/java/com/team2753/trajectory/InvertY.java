package com.team2753.trajectory;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.Trajectory;

/**
 * Created by joshua9889 on 5/29/2018.
 */

public class InvertY {
    public static Path calculate(Path original){
        original.getLeftWheelTrajectory().scale(-1);
        original.getRightWheelTrajectory().scale(-1);
        return new Path("Invert", new Trajectory.Pair(
                original.getRightWheelTrajectory(), original.getLeftWheelTrajectory()));
    }
}
