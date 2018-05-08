package com.team2753.localTestCode.control;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.TrajectoryGenerator;
import com.team254.lib.trajectory.WaypointSequence;

/**
 * Created by joshua9889 on 4/20/2018.
 */

public class TestingPath {

    public static void main(String[] args){
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.dt = .01;
        config.max_acc = 0.6;
        config.max_jerk = 0.4;
        config.max_vel = 2.0;

        final double kWheelbaseWidth = 12.625/12;
        // Path name must be a valid Java class name.
        final String path_name = "RedCloseCryptobox";

        // Description of this auto mode path.
        // Remember that this is for the GO LEFT CASE!
        WaypointSequence p = new WaypointSequence(10);
        p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        p.addWaypoint(new WaypointSequence.Waypoint(7.0, 0, 0));

        Path path = PathGenerator.makePath(p, config, kWheelbaseWidth, path_name);
    }
}
