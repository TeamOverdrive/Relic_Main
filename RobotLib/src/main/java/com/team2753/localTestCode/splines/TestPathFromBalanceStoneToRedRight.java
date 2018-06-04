package com.team2753.localTestCode.splines;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;

/**
 * Created by joshua9889 on 6/1/2018.
 */

public class TestPathFromBalanceStoneToRedRight {
    public static void main(String... args){

        TrajectoryGenerator.Config defaultTrajectoryConfig = new TrajectoryGenerator.Config();
        defaultTrajectoryConfig.max_vel = 23.832; // In/s
        defaultTrajectoryConfig.max_acc = 100; // In/s^2
        defaultTrajectoryConfig.max_jerk = 100; // In/s^3
        defaultTrajectoryConfig.dt = 0.01; // seconds, change of time in each update

        TrajectoryGenerator.Config aggressiveTrajectoryConfig = new TrajectoryGenerator.Config();
        aggressiveTrajectoryConfig.max_vel = defaultTrajectoryConfig.max_vel;
        aggressiveTrajectoryConfig.max_acc = 1000;
        aggressiveTrajectoryConfig.max_jerk = 1000;
        aggressiveTrajectoryConfig.dt = defaultTrajectoryConfig.dt;

        double WHEEL_BASE = 12.625;


        WaypointSequence waypointSequence = new WaypointSequence(10);

        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        waypointSequence.addWaypoint(
                new WaypointSequence.Waypoint(
                        48.5 - 21, 23 - 36.0,
                        Math.toRadians(14)));
        Path pathToRightColumnRedFar = PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig, WHEEL_BASE, "");

        waypointSequence = new WaypointSequence(10);
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(12, -3, 0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(25, 0, Math.toRadians(-10)));
        Path pathFromFarRedRightToGlyphPit = PathGenerator.makePath(waypointSequence, aggressiveTrajectoryConfig, WHEEL_BASE, "");

        Trajectory right = pathToRightColumnRedFar.getRightWheelTrajectory();

        double time = 0;

        time = 250.0/1000.0;
        System.out.println(time);

        time = right.getNumSegments() * defaultTrajectoryConfig.dt;
        System.out.println(time);

        time = 200.0/1000;
        System.out.println(time);

        time = pathFromFarRedRightToGlyphPit.getRightWheelTrajectory().getNumSegments() * aggressiveTrajectoryConfig.dt;
        System.out.println(time);

        time = 1;
        System.out.println(time);

        time = 3.5;
        System.out.println(time);

        time = 2;
        System.out.println(time);

        time= 3.5;
        System.out.println(time);



    }
}
