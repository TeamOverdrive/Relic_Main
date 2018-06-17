package com.team2753.auto.SPLINE.paths;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;
import com.team2753.splines.field.FieldConfig;

import static com.team2753.Constants.WHEEL_BASE;
import static com.team2753.Constants.defaultTrajectoryConfig;

public class RedClose_Paths {
    public static Path bsToRight = null;
    public static Path bsToCenter = null;
    public static Path bsToLeft = null;

    public static  Path rightToGP = null;
    public static Path centerToGP = null;
    public static Path leftToGP = null;

    public static Path gpToRight = null;
    public static Path gpToCenter = null;
    public static Path gpToLeft = null;

    public static void calculateRedClose(final FieldConfig field){
        if(bsToRight ==null) {
            WaypointSequence waypointSequence = new WaypointSequence(5);

            // Path from Red Far Stone to Right Column
            waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
            waypointSequence.addWaypoint(
                    new WaypointSequence.Waypoint(
                            field.getFarRedCryptoboxToFarStone() - 21, 23 - field.getFarRedCenterToWall(),
                            Math.toRadians(14)));
            bsToRight = PathGenerator.makePath(waypointSequence, defaultTrajectoryConfig, WHEEL_BASE, "");
        }
    }
}
