package com.team2753.localTestCode.control;

import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team254.lib_2016.Path;
import com.team254.lib_2016.Translation2d;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshua9889 on 4/20/2018.
 */

public class TestingPath {

    public static void main(String[] args){
//        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
//        config.dt = .01;
//        config.max_acc = 0.6;
//        config.max_jerk = 0.4;
//        config.max_vel = 2.0;
//
//        final double kWheelbaseWidth = 12.625/12;
//        // Path name must be a valid Java class name.
//        final String path_name = "RedCloseCryptobox";
//
//        com.team254.lib_2014.trajectory.Trajectory newTra = TrajectoryGenerator.generate(config, TrajectoryGenerator.SCurvesStrategy,
//                0, 0, 100, 0, 5);
//
//        System.out.println(newTra.toStringProfile());

        List<Path.Waypoint> first_path = new ArrayList<>();
        first_path.add(new Path.Waypoint(new Translation2d(0, 0), 120.0));
        first_path.add(new Path.Waypoint(new Translation2d(24, 0), 120.0));
        first_path.add(new Path.Waypoint(new Translation2d(24, 18), 60.0));


    }
}
