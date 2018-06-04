package com.team2753.trajectory;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;

/**
 * Created by joshua9889 on 5/26/2018.
 */

public class Line {
    public static Path calculate(TrajectoryGenerator.Config config,
                                 TrajectoryGenerator.Strategy strategy,
                                 double start_velocity, double heading, double distance,
                                 double goal_velocity){
        Path reference = Arc.calculate(config, strategy,
                start_velocity, heading, Math.abs(distance), goal_velocity, heading);
        if(distance<0)
            InvertY.calculate(reference);

        return reference;
    }
}
