package com.team2753.trajectory;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.Trajectory;
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
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(13, 1, 0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(40, -7, Math.toRadians(-23)));
        Path leftToGP = PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig,
                12.625, "");

        System.out.println(leftToGP.getRightWheelTrajectory().toString());
        System.out.println(Math.toDegrees(leftToGP.getRightWheelTrajectory().getSegment(leftToGP.getRightWheelTrajectory().getNumSegments()-1).heading));
    }
}
