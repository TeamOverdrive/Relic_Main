package com.team2753.splines;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;
import com.team2753.splines.field.FieldConfig;
import com.team2753.splines.field.JoshuaField;

import static com.team2753.Constants.WHEEL_BASE;
import static com.team2753.Constants.aggressiveTrajectoryConfig;
import static com.team2753.Constants.defaultTrajectoryConfig;

/**
 * Created by joshua9889 on 5/31/2018.
 */

public class PathStorage {
    public static Path pathToRightColumnRedFar = null;
    public static Path pathToCenterColumnRedFar = null;

    public static  Path pathFromFarRedRightToGlyphPit = null;

    public static Path pathFromGlyphPitToCenterRedFar = null;

    public static Path pathFromCenterColumnToGlyphPitRedFar = null;

    public static void calculateAll(FieldConfig field) {
        calculateRedFar(field);
    }

    public static void calculateRedFar(final FieldConfig field){
        WaypointSequence waypointSequence = new WaypointSequence(10);
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        waypointSequence.addWaypoint(
                new WaypointSequence.Waypoint(
                        field.getFarRedCryptoboxToFarStone() - 21, 23 - field.getFarRedCenterToWall(),
                        Math.toRadians(14)));
        pathToRightColumnRedFar = PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig, WHEEL_BASE, "");

        waypointSequence = new WaypointSequence(10);
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(12, -3, 0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(25, 0, Math.toRadians(-10)));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(35, -2, Math.toRadians(-10)));
        pathFromFarRedRightToGlyphPit = PathGenerator.makePath(waypointSequence, aggressiveTrajectoryConfig, WHEEL_BASE, "");

        waypointSequence = new WaypointSequence(10);
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(45, -2, Math.toRadians(5)));
        pathFromGlyphPitToCenterRedFar = PathGenerator.makePath(waypointSequence, aggressiveTrajectoryConfig, WHEEL_BASE, "");

//        waypointSequence = new WaypointSequence(10);
//        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
//        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(30, -6, 0));
//        pathFromCenterColumnToGlyphPitRedFar = PathGenerator.makePath(waypointSequence, aggressiveTrajectoryConfig, WHEEL_BASE, "");
    }

    static {
        PathStorage.calculateAll(new JoshuaField());
    }
}
