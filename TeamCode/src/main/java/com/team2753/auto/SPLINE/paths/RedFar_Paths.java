package com.team2753.auto.SPLINE.paths;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;
import com.team2753.splines.field.FieldConfig;

import static com.team2753.Constants.WHEEL_BASE;
import static com.team2753.Constants.aggressiveTrajectoryConfig;
import static com.team2753.Constants.defaultTrajectoryConfig;

public class RedFar_Paths {
    public static Path bsToRight = null;
    public static Path bsToCenter = null;
    public static Path bsToLeft = null;

    public static  Path rightToGP = null;
    public static Path centerToGP = null;
    public static Path leftToGP = null;

    public static Path gpToRight = null;
    public static Path gpToCenter = null;
    public static Path gpToLeft = null;

    public static void calculateRedFar(final FieldConfig field){
        if(bsToRight ==null) {
            WaypointSequence waypointSequence = new WaypointSequence(10);

            /** Right **/
            // Path from Red Far Stone to Right Column
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
            waypointSequence.addWaypoint(
                    new WaypointSequence.Waypoint(
                            field.getFarRedCryptoboxToFarStone() - 20,
                            29 - field.getFarRedCenterToWall(),
                            Math.toRadians(15)));
            bsToRight = PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig, WHEEL_BASE, "");
//
//            // Path from Right Column to Glyph Pit
//            waypointSequence = new WaypointSequence(5);
//            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
//            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(12, -3, 0));
//            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(25, 0, Math.toRadians(-10)));
//            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(35, -2, Math.toRadians(-10)));
//            rightToGP =
//                    PathGenerator.makePath(waypointSequence, aggressiveTrajectoryConfig, WHEEL_BASE, "");
//
//            // Path  from Glyph Pit to Center Column
//            waypointSequence = new WaypointSequence(5);
//            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
//            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(25, -20, Math.toRadians(0)));
//            gpToRight =
//                    PathGenerator.makePath(waypointSequence, aggressiveTrajectoryConfig, WHEEL_BASE, "");

            /** Center **/
            // Path from Red Fat Stone to Center Column
            waypointSequence = new WaypointSequence(10);
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
            waypointSequence.addWaypoint(
                    new WaypointSequence.Waypoint(
                            field.getFarRedCryptoboxToFarStone() - 18,
                            21 - field.getFarRedCenterToWall(),
                            Math.toRadians(0)));
            bsToCenter = PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig, WHEEL_BASE, "");


            /** Left **/
            waypointSequence = new WaypointSequence(10);
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(
                    field.getFarRedCryptoboxToFarStone() - 18,
                    14-field.getFarRedCenterToWall(),
                    Math.toRadians(15)));
            bsToLeft = PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig, WHEEL_BASE, "");
        }
    }
}
