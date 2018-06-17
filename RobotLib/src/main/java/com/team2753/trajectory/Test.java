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

        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        waypointSequence.addWaypoint(
                new WaypointSequence.Waypoint(
                        48.5 - 20,
                        28 - 36,
                        Math.toRadians(15)));
            Path bsToRight =
                    PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig, 12.625, "");

        /** Center **/
        // Path from Red Fat Stone to Center Column
        waypointSequence = new WaypointSequence(5);
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        waypointSequence.addWaypoint(
                new WaypointSequence.Waypoint(
                        48.5 - 18,
                        21 - 36,
                        Math.toRadians(0)));
         Path bsToCenter =
                 PathGenerator.makePath(
                         waypointSequence, defaultTrajectoryConfig, 12.625, "");

        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(
                48.5 - 21,
                13-36.0,
                Math.toRadians(0)));
        Path path = PathGenerator.makePath(
                waypointSequence,defaultTrajectoryConfig, 16.625, "");
//        System.out.println(path.getLeftWheelTrajectory().toString());
        System.out.println(path.getLeftWheelTrajectory());

    }
}
