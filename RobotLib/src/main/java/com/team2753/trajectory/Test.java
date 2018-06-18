package com.team2753.trajectory;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;

public class Test {
    public static void main(String... args){

        TrajectoryGenerator.Config defaultTrajectoryConfig= new TrajectoryGenerator.Config();
        defaultTrajectoryConfig.max_vel = 23.832; // In/s
        defaultTrajectoryConfig.max_acc = 100; // In/s^2
        defaultTrajectoryConfig.max_jerk = 100; // In/s^3
        defaultTrajectoryConfig.dt = 0.01; // seconds, change of time in each update

        WaypointSequence waypointSequence = new WaypointSequence(5);

        Path path = Arc.calculate(defaultTrajectoryConfig, TrajectoryGenerator.SCurvesStrategy,
                0, 0, 10, 0, 15);
//        System.out.println(path.getLeftWheelTrajectory().toString());
        System.out.println(path.getLeftWheelTrajectory().toStringEuclidean());
        System.out.println(path.getRightWheelTrajectory().toStringEuclidean());

    }
}
