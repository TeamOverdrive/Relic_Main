package com.team2753.auto.SPLINE;

import com.team254.lib_2014.trajectory.WaypointSequence;
import com.team2753.Team2753Linear;

/**
 * Created by joshua9889 on 5/29/2018.
 */

public class BLUE_CLOSE extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        WaypointSequence blueClose = new WaypointSequence(5);
        blueClose.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        blueClose.addWaypoint(new WaypointSequence.Waypoint(10, 0, 0));
        blueClose.addWaypoint(new WaypointSequence.Waypoint(36, 3, Math.PI));
    }
}
