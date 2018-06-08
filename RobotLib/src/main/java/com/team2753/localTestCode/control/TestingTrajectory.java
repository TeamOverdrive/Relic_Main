package com.team2753.localTestCode.control;

import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryFollower;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;

/**
 * Created by joshua9889 on 4/24/2018.
 */

public class TestingTrajectory {
    public static void main(String[] args){
        // Distance of an arc that we want to travel.
        double distance = 10;

        // Angle that we want to be at, at the end of the circle.
        // Has to be non-zero because we, for now, assume that we are traveling in a arc.
        // Based on unit circle
        double angle = -0.00001;

        // Config for the based on what we want the robot to do, not
        // based on the characteristics of the robot.
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 2.5*12; // In/s
        config.max_acc = 7.57*12; // In/s^2
        config.max_jerk = 10*12; // In/s^3
        config.dt = 0.01; // seconds, change of time in each update

        TrajectoryGenerator.Strategy strategy = TrajectoryGenerator.TrapezoidalStrategy;
        Trajectory reference = TrajectoryGenerator.generate(
                config,
                strategy,
                0.0, // start velocity
                0,
                Math.abs(distance),
                0.0, // goal velocity
                0);

        Trajectory leftProfile = reference;
        Trajectory rightProfile = reference.copy(); // Copy

        double radius = Math.abs(Math.abs(distance) / (angle * Math.PI / 180.0));
        double width = 12.625;
        double faster = (radius + (width / 2.0)) / radius;
        double slower = (radius - (width / 2.0)) / radius;
        if (angle > 0) {
            leftProfile.scale(faster);
            rightProfile.scale(slower);
        } else {
            leftProfile.scale(slower);
            rightProfile.scale(faster);
        }

        double scaleFactor = 1.0/30.0;
        leftProfile.scale(scaleFactor);
        rightProfile.scale(scaleFactor);

        double max = 0;
        for (int i = 0; i < leftProfile.getNumSegments(); i++) {
            if(leftProfile.getSegment(i).vel>max)
                max = leftProfile.getSegment(i).vel;
        }
        System.out.println(leftProfile.getNumSegments());
        System.out.println(max);
        //System.out.println(leftProfile.toStringProfile());
        //System.out.println(rightProfile.toStringProfile());
    }
}
