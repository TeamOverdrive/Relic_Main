package com.team2753.localTestCode.control;

import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryFollower;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;

/**
 * Created by joshua9889 on 5/28/2018.
 */

public class TestTrajectoryGeneration {
    public static void main(String... args){
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 23.832; // In/s
        config.max_acc = 11.9; // In/s^2
        config.max_jerk = 10; // In/s^3
        config.dt = 0.01;
        Trajectory test = TrajectoryGenerator.generate(config, TrajectoryGenerator.SCurvesStrategy,
                0, 0, 24, 0, 0);
        TrajectoryFollower follower = new TrajectoryFollower("");
        follower.setTrajectory(test);
        follower.configure(0.1, 0.0, 0, 0.1,  0.1);
//        System.out.println(test.toString());
//        System.out.println(test.getNumSegments()*config.dt);
        for (int i = 0; i < 250; i++) {
            System.out.println(i/10.0+ "\t"+follower.calculate(i/10));
        }

    }
}
