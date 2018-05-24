package com.team2753.splines;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.Trajectory;

/**
 * Created by joshua9889 on 5/23/2018.
 */

public class CreatePath {
    public static Path CreatePath(Trajectory trajectory){
        Trajectory leftTrajectory = new Trajectory(trajectory.getNumSegments());
        Trajectory rightTrajectory = new Trajectory(trajectory.getNumSegments());


        return new Path(trajectory.getClass().toString(), new Trajectory.Pair(leftTrajectory, rightTrajectory));
    }
}
