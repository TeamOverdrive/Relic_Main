package com.team2753.localTestCode;

import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;

/**
 * Created by joshua9889 on 5/26/2018.
 */

public class TestSplines {
    public static void main(String... args){
        // Distance of an arc that we want to travel.
        double distance = 10;

        // Angle that we want to be at, at the end of the circle.
        // Has to be non-zero because we, for now, assume that we are traveling in a arc.
        // Based on unit circle
        double angle = -0.00001;

        // What kind of trajectory profile do we want?
        TrajectoryGenerator.Strategy strategy = TrajectoryGenerator.SCurvesStrategy;

        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 2.5*12; // In/s
        config.max_acc = 7.57*12; // In/s^2
        config.max_jerk = 10*12; // In/s^3
        config.dt = 0.01; // seconds, change of time in each update

        // Generate trajectory based to the inputs
        Trajectory reference = TrajectoryGenerator.generate(
                config,
                strategy,
                0.0, // start velocity
                0.0, // start heading
                Math.abs(distance), // goal position
                5, // goal velocity
                angle); // goal angle

        // Copy the trajectories to left and right
        // Right now the robot would go straight, if we purely used this traject.
        Trajectory leftProfile = reference;
        Trajectory rightProfile = reference.copy(); // Copy

        // Calculate the radius of the arc
        double radius = Math.abs(Math.abs(distance) / (angle * Math.PI / 180.0));

        double width = 12.625;

        // Find the difference between the left and right motors
        double faster = (radius + (width / 2.0)) / radius;
        double slower = (radius - (width / 2.0)) / radius;

        // Determine which way to curve
        if (angle > 0) {
            leftProfile.scale(faster);
            rightProfile.scale(slower);
        } else {
            leftProfile.scale(slower);
            rightProfile.scale(faster);
        }

        // Used to make sure that all the outputs are in the range of [-1,1]
        // This is not scientific but rather a guess based to testing
        double scaleFactor = 1.0/30.0;
        leftProfile.scale(scaleFactor);
        rightProfile.scale(scaleFactor);

        distance = 15;
        angle = -90;

        // Generate trajectory based to the inputs
        reference = TrajectoryGenerator.generate(
                config,
                strategy,
                config.max_vel+2, // start velocity
                -0.00001, // start heading
                Math.abs(distance), // goal position
                0.0, // goal velocity
                angle); // goal angle

        // Copy the trajectories to left and right
        // Right now the robot would go straight, if we purely used this traject.
        Trajectory leftProfile2 = reference;
        Trajectory rightProfile2 = reference.copy(); // Copy

        // Calculate the radius of the arc
        radius = Math.abs(Math.abs(distance) / (angle * Math.PI / 180.0));

        width = 12.;

        // Find the difference between the left and right motors
        faster = (radius + (width / 2.0)) / radius;
        slower = (radius - (width / 2.0)) / radius;

        // Determine which way to curve
        if (angle > 0) {
            leftProfile2.scale(faster);
            rightProfile2.scale(slower);
        } else {
            leftProfile2.scale(slower);
            rightProfile2.scale(faster);
        }

        // Used to make sure that all the outputs are in the range of [-1,1]
        // This is not scientific but rather a guess based to testing
        scaleFactor = 1.0/30.0;
        leftProfile2.scale(scaleFactor);
        rightProfile2.scale(scaleFactor);

//        leftProfile.append(leftProfile2);
//        rightProfile.append(rightProfile2);

        System.out.println(leftProfile.toStringProfile());
        System.out.println(leftProfile.getNumSegments()*config.dt);
    }
}
