package com.team2753.localTestCode;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;

/**
 * Created by joshua9889 on 5/30/2018.
 */

public class TestCombinePaths {
    public static void main(String... args){
        TrajectoryGenerator.Config RedConfig = new TrajectoryGenerator.Config();
        RedConfig.max_vel = 23.23;
        RedConfig.max_acc = 200;
        RedConfig.max_jerk = 10000;
        RedConfig.dt = 0.01;

        double WHEEL_BASE = 12.625;

        WaypointSequence waypointSequence = new WaypointSequence(10);
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(6, 0, 0));
        waypointSequence.addWaypoint(
                new WaypointSequence.Waypoint(
                        48.0-33,
                        36.0-28.5,
                        Math.toRadians(-10)));
        Path pathToRightColumnRedFar = PathGenerator.makePath(waypointSequence, RedConfig, WHEEL_BASE, "");
        pathToRightColumnRedFar.goRight();

        waypointSequence = new WaypointSequence(10);
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(10, 5, Math.toRadians(20)));
        Path fromRedRightToBox = PathGenerator.makePath(waypointSequence, RedConfig, WHEEL_BASE, "");
        fromRedRightToBox.getLeftWheelTrajectory().scale(-1);
        fromRedRightToBox.getRightWheelTrajectory().scale(-1);

        Trajectory.Pair base = new Trajectory.Pair(pathToRightColumnRedFar.getLeftWheelTrajectory(), pathToRightColumnRedFar.getRightWheelTrajectory());
        base.left.append(fromRedRightToBox.getLeftWheelTrajectory());
        base.right.append(fromRedRightToBox.getRightWheelTrajectory());

        Path output = new Path("Hello", base);
        System.out.println(output.getLeftWheelTrajectory().toStringEuclidean());

    }
}
