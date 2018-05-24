package com.team2753.localTestCode.control;

import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryFollower;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;

/**
 * Created by joshua9889 on 4/24/2018.
 */

public class TestingTrajectory {
    public static void main(String[] args){
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 2.0*12; // In/s
        config.max_acc = 0.6*12; // In/s^2
        config.max_jerk = 0.4*12; // In/s^3
        config.dt = 0.01; // seconds

        Trajectory trajectory = TrajectoryGenerator.generate(
                config, TrajectoryGenerator.SCurvesStrategy,
                0, 0,
                10, 0, 0);
        TrajectoryFollower follower = new TrajectoryFollower("Follower");
        follower.configure(10, 0, 2, 0.4, 0.4);
        follower.setTrajectory(trajectory);
        follower.calculate(10);
        //System.out.println(trajectory.toString());
        //====================================

        TrajectoryGenerator.Strategy strategy = TrajectoryGenerator.SCurvesStrategy;
        Trajectory reference = TrajectoryGenerator.generate(
                config,
                strategy,
                0.0, // start velocity
                0,
                Math.abs(10),
                0.0, // goal velocity
                0);

        Trajectory leftProfile = reference;
        Trajectory rightProfile = reference.copy(); // Copy

        double radius = Math.abs(Math.abs(10) / (0 * Math.PI / 180.0));
        double width = 12.625;
        double faster = (radius + (width / 2.0)) / radius;
        double slower = (radius - (width / 2.0)) / radius;
        System.out.println("faster " + faster);
        if (0 > 0) {
            leftProfile.scale(faster);
            rightProfile.scale(slower);
        } else {
            leftProfile.scale(slower);
            rightProfile.scale(faster);
        }

    }
}
