package com.team2753.splines.team254_2014;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team2753.Constants;

/**
 * Created by joshua9889 on 5/26/2018.
 */

public class Arc {
    public static Path calculate(TrajectoryGenerator.Config config,
                                 TrajectoryGenerator.Strategy strategy,
                                 double start_velocity, double start_heading, double distance,
                                 double goal_velocity, double goal_heading){

        double goal_heading_ = start_heading;
        if(goal_heading == 0.0)
            goal_heading_ = 1E-3;

        // Generate trajectory based to the inputs
        Trajectory reference = TrajectoryGenerator.generate(
                config,
                strategy,
                start_velocity, // start velocity
                start_heading, // start heading
                distance, // goal position
                goal_velocity, // goal velocity
                goal_heading_); // goal angle

        Trajectory leftProfile = reference;
        Trajectory rightProfile = reference.copy();

        // Calculate the radius of the arc
        double radius = Math.abs(Math.abs(distance) / (goal_heading_ * Math.PI / 180.0));

        double width = Constants.WHEEL_BASE;

        // Find the difference between the left and right motors
        double faster = (radius + (width / 2.0)) / radius;
        double slower = (radius - (width / 2.0)) / radius;

        // Determine which way to curve
        if (goal_heading_ > 0) {
            leftProfile.scale(faster);
            rightProfile.scale(slower);
        } else {
            leftProfile.scale(slower);
            rightProfile.scale(faster);
        }

        return new Path("Arc", new Trajectory.Pair(leftProfile, rightProfile));
    }
}
