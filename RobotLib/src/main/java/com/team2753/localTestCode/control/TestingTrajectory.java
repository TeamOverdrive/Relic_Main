package com.team2753.localTestCode.control;

import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryGenerator;

/**
 * Created by joshua9889 on 4/24/2018.
 */

public class TestingTrajectory {
    public static void main(String[] args){
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 2.0; // F/s
        config.max_acc = 0.6; // F/s^2
        config.max_jerk = 0.4; // F/s^3
        config.dt = 0.01; // seconds

        Trajectory t = TrajectoryGenerator.generate(config, TrajectoryGenerator.TrapezoidalStrategy, 0, 0, 3, 0, 0);
        if (t != null) {
            System.out.println(t.toString());
        }

        System.out.println("".length());
    }
}
