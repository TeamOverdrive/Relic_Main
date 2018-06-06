package com.team2753.trajectory;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;

/**
 * Created by joshua9889 on 5/26/2018.
 */

public class PointTurn {
    public static Path calculate(TrajectoryGenerator.Config config,
                                 TrajectoryGenerator.Strategy strategy,
                                 double start_velocity, double start_heading,
                                 double goal_velocity, double goal_heading){

        Trajectory ref = TrajectoryGenerator.generate(config, strategy,
                start_velocity, start_heading, 1e-10,
                goal_velocity, goal_heading);

        Trajectory leftProfile = ref;
        Trajectory rightProfile = ref.copy();

        // Calculate the radius of the arc
        double radius = Math.abs(Math.abs(1e-10) / (goal_heading * Math.PI / 180.0));

        double width = 12.625;

        // Find the difference between the left and right motors
        double faster = (radius + (width / 2.0)) / radius;
        double slower = (radius - (width / 2.0)) / radius;

        // Determine which way to curve
        if (goal_heading > 0) {
            leftProfile.scale(faster);
            rightProfile.scale(slower);
        } else {
            leftProfile.scale(slower);
            rightProfile.scale(faster);
        }

        return new Path("Turn", new Trajectory.Pair(leftProfile, rightProfile));
    }
}
