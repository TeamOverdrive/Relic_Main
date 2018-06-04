package com.team2753.trajectory;

import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;

/**
 * Created by joshua9889 on 6/1/2018.
 */

public class AdaptivePurePursuitController {
    public void calculate(TrajectoryGenerator.Config config, WaypointSequence.Waypoint origin, WaypointSequence.Waypoint finalPoint, double direction){
        double firstAngle = 2*(origin.theta + finalPoint.theta);


    }
}
