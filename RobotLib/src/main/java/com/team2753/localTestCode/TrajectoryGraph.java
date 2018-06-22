package com.team2753.localTestCode;

import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;

public class TrajectoryGraph {
    public static void main(String... args){
        TrajectoryGenerator.Config defaultTrajectoryConfig = new TrajectoryGenerator.Config();
        defaultTrajectoryConfig.max_vel = 23.832; // In/s
        defaultTrajectoryConfig.max_acc = 40; // In/s^2
        defaultTrajectoryConfig.max_jerk = 40; // In/s^3
        defaultTrajectoryConfig.dt = 0.01; // seconds, change of time in each update

        Trajectory trajectory = TrajectoryGenerator.generate(defaultTrajectoryConfig, TrajectoryGenerator.TrapezoidalStrategy,
                0, 0, 15, 0, 0);
        System.out.println(trajectory.toString());

    }
}
