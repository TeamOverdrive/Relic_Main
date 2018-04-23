package com.team2753.localTestCode.motion;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.TrajectoryGenerator;
import com.team254.lib.trajectory.WaypointSequence;
import com.team254.lib.trajectory.io.TextFileSerializer;

/**
 * Created by joshua9889 on 4/20/2018.
 */

public class TestingMotion {

    public static void main(String[] args){
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.dt = .01;
        config.max_acc = 10.0;
        config.max_jerk = 60.0;
        config.max_vel = 15.0;

        final double kWheelbaseWidth = 16.125/12;
        // Path name must be a valid Java class name.
        final String path_name = "RedCloseCryptobox";

        // Description of this auto mode path.
        // Remember that this is for the GO LEFT CASE!
        WaypointSequence p = new WaypointSequence(10);
        p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        p.addWaypoint(new WaypointSequence.Waypoint(7.0, 0, 0));
        p.addWaypoint(new WaypointSequence.Waypoint(14.0, 1.0, Math.PI / 12.0));
        p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));

        Path path = PathGenerator.makePath(p, config, kWheelbaseWidth, path_name);

        // Outputs to the directory supplied as the first argument.
        TextFileSerializer js = new TextFileSerializer();
        String serialized = js.serialize(path);
        //System.out.print(serialized);

    }
}
