package com.team2753.trajectory;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;

/**
 * Created by joshua9889 on 5/26/2018.
 */

public class Arc {
    public static Path calculateAngleRadius(TrajectoryGenerator.Config config,
                                            TrajectoryGenerator.Strategy strategy,
                                            double start_velocity, double start_heading, double radius,
                                            double goal_velocity, double goal_heading){
        double length = Math.abs(2*Math.PI*radius*(goal_heading-start_heading/ 360));
        Path store=Arc.calculate(config, strategy, start_velocity, start_heading, length, goal_velocity, goal_heading);

        if(radius<0)
            InvertY.calculate(store);
        return store;
    }

    public static Path calculate(TrajectoryGenerator.Config config,
                                 TrajectoryGenerator.Strategy strategy,
                                 double start_velocity, double start_heading, double distance,
                                 double goal_velocity, double goal_heading){

        // Generate trajectory based to the inputs
        Trajectory reference = TrajectoryGenerator.generate(
                config,
                strategy,
                start_velocity, // start velocity
                start_heading, // start heading
                Math.abs(distance), // goal position
                goal_velocity, // goal velocity
                goal_heading); // goal angle

        Trajectory leftProfile = reference;
        Trajectory rightProfile = reference.copy();

        // Calculate the radius of the arc
        double radius = Math.copySign(Math.abs(Math.abs(distance) / (goal_heading-start_heading * Math.PI / 180.0)), distance);

        double width = 12.625;

        // Find the difference between the left and right motors
        double faster = (radius + (width / 2.0)) / radius;
        double slower = (radius - (width / 2.0)) / radius;
        System.out.println(faster);
        System.out.println(slower);

        // Determine which way to curve
        if (goal_heading-start_heading > 0) {
            leftProfile.scale(faster);
            rightProfile.scale(slower);
        } else if (goal_heading-start_heading<0){
            leftProfile.scale(slower);
            rightProfile.scale(faster);
        }
        Path ref = new Path("Arc", new Trajectory.Pair(leftProfile, rightProfile));
        if(distance<0)
            InvertY.calculate(ref);

        return ref;
    }
}
