package com.team2753.localTestCode;

import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;

/**
 * Created by joshua9889 on 5/26/2018.
 */

public class TestPointTurning {
    public static void main(String... args){
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 2.5*12; // In/s
        config.max_acc = 8*12; // In/s^2
        config.max_jerk = 10*12; // In/s^3
        config.dt = 0.01; // seconds, change of time in each update

        double angle = Math.PI/2;

        TrajectoryGenerator.Strategy strategy = TrajectoryGenerator.SCurvesStrategy;

        Trajectory ref = TrajectoryGenerator.generate(config, strategy, 0,0, 1e-10, 0, angle);

        Trajectory leftProfile = ref;
        Trajectory rightProfile = ref.copy();

        // Calculate the radius of the arc
        double radius = Math.abs(Math.abs(1e-10) / (angle * Math.PI / 180.0));

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

        System.out.println(leftProfile.toString());
        System.out.println("====================");
        System.out.println(rightProfile.toString());
        System.out.println("Angle: " + angle);
        System.out.println(angle - rightProfile.getSegment(rightProfile.getNumSegments()-1).heading);
    }
}
