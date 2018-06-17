package com.team2753.auto.SPLINE.paths;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;
import com.team2753.splines.field.FieldConfig;

import static com.team2753.Constants.WHEEL_BASE;
import static com.team2753.Constants.defaultTrajectoryConfig;

public class BlueClose_Paths {

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
}
