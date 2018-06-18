package com.team2753.trajectory;

import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;

public class Test {
    public static void main(String... args){

        TrajectoryGenerator.Config defaultTrajectoryConfig= new TrajectoryGenerator.Config();
        defaultTrajectoryConfig.max_vel = 23.832; // In/s
        defaultTrajectoryConfig.max_acc = 100; // In/s^2
        defaultTrajectoryConfig.max_jerk = 100; // In/s^3
        defaultTrajectoryConfig.dt = 0.01; // seconds, change of time in each update

        Trajectory left;
        Trajectory right;

        double leftDistance = -10;
        double rightDistance = -10;


            left = TrajectoryGenerator.generate(defaultTrajectoryConfig, TrajectoryGenerator.TrapezoidalStrategy,
                    0, 0, Math.abs(leftDistance), 0, 0);

            right = TrajectoryGenerator.generate(defaultTrajectoryConfig, TrajectoryGenerator.TrapezoidalStrategy,
                    0, 0, Math.abs(rightDistance), 0, 0);

        if(leftDistance<0){
            for (int i=0;i<left.getNumSegments(); i++){
                left.getSegment(i).pos *= -1;
                left.getSegment(i).vel *= -1;
                left.getSegment(i).acc *= -1;
                left.getSegment(i).jerk *= -1;
            }
        }

        if(rightDistance<0){
            for (int i=0;i<right.getNumSegments(); i++){
                right.getSegment(i).pos *= -1;
                right.getSegment(i).vel *= -1;
                right.getSegment(i).acc *= -1;
                right.getSegment(i).jerk *= -1;
            }
        }

        System.out.println(right.toString());
        System.out.println(left.toString());
    }
}
