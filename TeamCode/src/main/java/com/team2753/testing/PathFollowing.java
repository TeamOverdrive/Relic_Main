package com.team2753.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.PathGenerator;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team254.lib_2014.trajectory.WaypointSequence;
import com.team2753.auto.AutoModeBase;

/**
 * Created by joshua9889 on 4/23/2018.
 */

@Autonomous
public class PathFollowing extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("Path", true);

        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.dt = .01;
        config.max_acc = 10.0;
        config.max_jerk = 60.0;
        config.max_vel = 15.0;

        final double kWheelbaseWidth = 12.625;
        // Path name must be a valid Java class name.
        final String path_name = "RedCloseCryptobox";

        // Description of this auto mode path.
        // Remember that this is for the GO LEFT CASE!
        WaypointSequence p = new WaypointSequence(10);
        p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
        p.addWaypoint(new WaypointSequence.Waypoint(7.0, 0, 0));

        Path path = PathGenerator.makePath(p, config, kWheelbaseWidth, path_name);

        Robot.getDrive().loadProfile(path.getLeftWheelTrajectory(),
                path.getRightWheelTrajectory(), 0, 0);

        while (opModeIsActive() && !Robot.getDrive().onTarget()){

            Robot.getDrive().update();
        }
        Robot.getDrive().setLeftRightPower(0,0);
    }
}
