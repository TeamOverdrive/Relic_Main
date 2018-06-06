package com.team2753.splines;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.team254.lib_2014.trajectory.Path;
import com.team2753.subsystems.AutoDrive;
import com.team2753.subsystems.Drive;

/**
 * Created by joshua9889 on 5/31/2018.
 */

public class FollowPath {
    public FollowPath(LinearOpMode opMode, Drive mDrive, Path path, double direction, double angles){
        AutoDrive drive = new AutoDrive(mDrive);
        drive.configureForPathFollowing(path, direction, angles);

        while (opMode.opModeIsActive() && !drive.isDone())
            drive.update();
    }
}
