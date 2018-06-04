package com.team2753.localTestCode.control;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team2753.trajectory.Arc;

import static com.team254.lib_2014.trajectory.TrajectoryGenerator.SCurvesStrategy;

/**
 * Created by joshua9889 on 5/29/2018.
 */

public class InvertTest {
    public static void main(String... args){
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 23.832; // In/s
        config.max_acc = 11.9; // In/s^2
        config.max_jerk = 10; // In/s^3
        config.dt = 0.01;
        //Path t = Arc.calculate(config, TrajectoryGenerator.SCurvesStrategy,
    //                0, 0, -5, 0, -Math.PI/2);

        Path curve = Arc.calculateAngleRadius(config, SCurvesStrategy, 0, 90, -12.625/2, 0, 0);
        System.out.println(curve.getLeftWheelTrajectory().toString());
        System.out.println(curve.getRightWheelTrajectory().toString());

    }
}
