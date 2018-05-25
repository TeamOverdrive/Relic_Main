package com.team2753.localTestCode;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;

/**
 * Created by joshua9889 on 5/22/2018.
 */

public class howlong {
    public static void main(String[] args){
        double starttime = System.currentTimeMillis();
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 2.0*12; // In/s
        config.max_acc = 0.6*12; // In/s^2
        config.max_jerk = 0.4*12; // In/s^3
        config.dt = 0.001; // seconds

        double distance = 20;
        double angle = -90;

        double velocityMax = config.max_vel;
        double Vmax = 12;
        double kv = Vmax/velocityMax;
        System.out.println(kv);

        TrajectoryGenerator.Strategy strategy = TrajectoryGenerator.TrapezoidalStrategy;
        Trajectory reference = TrajectoryGenerator.generate(
                config,
                strategy,
                0.0, // start velocity
                0,
                Math.abs(distance),
                0.0, // goal velocity
                angle);

        System.out.println(reference.toStringEuclidean());

        Trajectory leftProfile = reference;
        Trajectory rightProfile = reference.copy(); // Copy

        double radius = Math.abs(Math.abs(distance) / (angle * Math.PI / 180.0));
        double width = 12;

        double faster = (radius + (width / 2.0)) / radius;
        double slower = (radius - (width / 2.0)) / radius;
        //System.out.println("faster " + faster);
        if (angle > 0) {
            leftProfile.scale(faster);
            rightProfile.scale(slower);
        } else {
            leftProfile.scale(slower);
            rightProfile.scale(faster);
        }

        System.out.println((System.currentTimeMillis()-starttime)/1000);
    }
}
