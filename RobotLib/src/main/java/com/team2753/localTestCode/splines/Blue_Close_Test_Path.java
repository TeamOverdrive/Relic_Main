package com.team2753.localTestCode.splines;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;

/**
 * Created by joshua9889 on 6/11/2018.
 */

public class Blue_Close_Test_Path {
    public static void main(String[] artgs){

        TrajectoryGenerator.Config defaultTrajectoryConfig = new TrajectoryGenerator.Config();
        // Found by using "FindPDVA" class
        defaultTrajectoryConfig.max_vel = 23.832; // In/s
        defaultTrajectoryConfig.max_acc = 100; // In/s^2
        defaultTrajectoryConfig.max_jerk = 100; // In/s^3
        defaultTrajectoryConfig.dt = 0.01; // seconds, change of time in each update

        WaypointSequence blueClose = new WaypointSequence(5);
        blueClose.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        blueClose.addWaypoint(new WaypointSequence.Waypoint(36-8, 0, 0));
        blueClose.addWaypoint(new WaypointSequence.Waypoint(36, -6, -89));
        //Path blueFirstPath = PathGenerator.makePath(blueClose, defaultTrajectoryConfig, 12.625, "");


        double WHEEL_BASE = 12.625;
        WaypointSequence RightColumnToGlyphPit = new WaypointSequence(5);
        RightColumnToGlyphPit.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
        RightColumnToGlyphPit.addWaypoint(new WaypointSequence.Waypoint(13, -2, 0));
        RightColumnToGlyphPit.addWaypoint(new WaypointSequence.Waypoint(39, 4, Math.toRadians(4)));
        Path farRedRightToGlyphPit = PathGenerator.makePath(RightColumnToGlyphPit, defaultTrajectoryConfig, WHEEL_BASE, "");

//        System.out.println(farRedRightToGlyphPit.getRightWheelTrajectory().toStringEuclidean());
        System.out.println(farRedRightToGlyphPit.getLeftWheelTrajectory().toString());
    }
}
