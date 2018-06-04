package com.team2753.localTestCode.control;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;

/**
 * Created by joshua9889 on 5/29/2018.
 */

public class TestingFIles {
    public static void main(String... args){
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 23.832; // In/s
        config.max_acc = 11.9; // In/s^2
        config.max_jerk = 10; // In/s^3
        config.dt = 0.01;

//        WaypointSequence waypointSequence = new WaypointSequence(10);
//        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
//        waypointSequence.addWaypoint(
//                new WaypointSequence.Waypoint(4, 0, 0));
//        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(42.25-30, 34.5-26, Math.toRadians(5)));

//        WaypointSequence blueClose = new WaypointSequence(5);
//        blueClose.addWaypoint(new WaypointSequence.Waypoint(0, 4, 0));
//        blueClose.addWaypoint(new WaypointSequence.Waypoint(, 0, 0));
//        blueClose.addWaypoint(new WaypointSequence.Waypoint(5, 36, Math.toRadians(-10)));
//
//        Path path = PathGenerator.makePath(blueClose, config, 12.625, "");

//        System.out.println(path.getLeftWheelTrajectory().toStringEuclidean());

        WaypointSequence waypointSequence = new WaypointSequence(10);

        double xOffset = 120;
        double yOffset = 96;
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(96-yOffset, 120-xOffset, 0));
        waypointSequence.addWaypoint(
                new WaypointSequence.Waypoint(
                        126-yOffset, 115-xOffset,
                        Math.toRadians(-15)));

        Path pathToRightColumnRedFar = PathGenerator.makePath(waypointSequence, config, 12.625, "");
        System.out.println(pathToRightColumnRedFar.getLeftWheelTrajectory().toStringEuclidean());
    }
}
