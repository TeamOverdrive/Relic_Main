package com.team2753.auto;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;
import com.team2753.Constants;

import static com.team2753.Constants.WHEEL_BASE;
import static com.team2753.Constants.defaultTrajectoryConfig;

public class HoldMyPaths {
    public static Path farBlueLeftToGlyphPit = null;

    public static void calculateBlueFarLeftToGlyphPit(){
        if(farBlueLeftToGlyphPit == null) {
            WaypointSequence waypointSequence = new WaypointSequence(5);
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(13, 1, 0));
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(41, -8, Math.toRadians(-23)));
            farBlueLeftToGlyphPit = PathGenerator.makePath(waypointSequence, Constants.aggressiveTrajectoryConfig,
                    WHEEL_BASE, "");
        }
    }

    public static Path farBlueCenterToGlyphPit = null;

    public static void calculateBlueFarCenterToGlyphPit(){
        if(farBlueCenterToGlyphPit == null){
            WaypointSequence waypointSequence = new WaypointSequence(5);
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(34, -10, Math.toRadians(-26)));
            farBlueCenterToGlyphPit = PathGenerator.makePath(waypointSequence, Constants.aggressiveTrajectoryConfig,
                    WHEEL_BASE, "");
        }
    }

    public static Path farBlueRightToGlyphPit = null;

    public static void calculateBlueFarRightToGlyphPit(){
        if(farBlueRightToGlyphPit == null){
            WaypointSequence waypointSequence = new WaypointSequence(5);
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(22, -11, Math.toRadians(-10.3)));
            farBlueRightToGlyphPit = PathGenerator.makePath(waypointSequence, Constants.aggressiveTrajectoryConfig,
                    WHEEL_BASE, "");
        }
    }

    public static Path closeBlueLeftToGlyphPit = null;

    public static void calculateBlueCloseLeftToGlyphPit(){
        if(closeBlueLeftToGlyphPit == null){
            WaypointSequence waypointSequence = new WaypointSequence(5);
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(9, -9, Math.toRadians(-70)));
            closeBlueLeftToGlyphPit = PathGenerator.makePath(waypointSequence, Constants.aggressiveTrajectoryConfig,
                    WHEEL_BASE, "");
        }
    }

    public static Path farRedRightToGlyphPit = null;

    public static void calculateRedRightToGlyphPit(){
        if(farRedRightToGlyphPit == null){
            WaypointSequence RightColumnToGlyphPit = new WaypointSequence(5);
            RightColumnToGlyphPit.addWaypoint(new WaypointSequence.Waypoint(0,0,0));
            RightColumnToGlyphPit.addWaypoint(new WaypointSequence.Waypoint(13, -2, 0));
            RightColumnToGlyphPit.addWaypoint(new WaypointSequence.Waypoint(39, 4, Math.toRadians(4)));
            farRedRightToGlyphPit = PathGenerator.makePath(RightColumnToGlyphPit, defaultTrajectoryConfig, WHEEL_BASE, "");
        }
    }
}
