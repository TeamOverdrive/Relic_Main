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
        WaypointSequence waypointSequence = new WaypointSequence(5);

        // Path from Red Far Stone to Right Column
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        waypointSequence.addWaypoint(
                new WaypointSequence.Waypoint(
                        field.getFarRedCryptoboxToFarStone() - 21, 23 - field.getFarRedCenterToWall(),
                        Math.toRadians(14)));
        pathToRightColumnRedFar =
                PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig, WHEEL_BASE, "");

        // Path from Red Fat Stone to Center Column
        waypointSequence = new WaypointSequence(5);
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        waypointSequence.addWaypoint(
                new WaypointSequence.Waypoint(
                        field.getFarRedCryptoboxToFarStone() - 21, 29 - field.getFarRedCenterToWall(),
                        Math.toRadians(14)));
        pathToCenterColumnRedFar =
                PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig, WHEEL_BASE, "");

        // Path from Right Column to Glyph Pit
        waypointSequence = new WaypointSequence(5);
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(12, -3, 0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(25, 0, Math.toRadians(-10)));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(35, -2, Math.toRadians(-10)));
        pathFromFarRedRightToGlyphPit =
                PathGenerator.makePath(waypointSequence, aggressiveTrajectoryConfig, WHEEL_BASE, "");

        // Path from Glyph Pit to Center Column
        waypointSequence = new WaypointSequence(5);
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
        waypointSequence.addWaypoint(new WaypointSequence.Waypoint(25, -20, Math.toRadians(0)));
        pathFromGlyphPitToCenterRedFar =
                PathGenerator.makePath(waypointSequence, aggressiveTrajectoryConfig, WHEEL_BASE, "");
    }

    public static Path pathFromBlueCloseToGlyphPit = null;
    public static Path pathFromBlueCloseGlyphPitToLeftColumn = null;

    public static void calculateBlueClose(final FieldConfig fieldConfig){
        if (pathFromBlueCloseToGlyphPit == null) {
            WaypointSequence waypointSequence = new WaypointSequence(5);
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(5, 0, 0));
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(24, 20, -89.4));
            pathFromBlueCloseToGlyphPit =
                    PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig, WHEEL_BASE, "");

            waypointSequence = new WaypointSequence(5);
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(25, 0, 5));
            pathFromBlueCloseGlyphPitToLeftColumn =
                    PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig, WHEEL_BASE, "");
        }
    }

    static {
        PathStorage.calculateAll(new JoshuaField());
    }
}
