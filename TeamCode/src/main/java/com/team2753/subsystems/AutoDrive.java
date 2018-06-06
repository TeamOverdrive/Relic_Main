package com.team2753.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team2753.Constants;
import com.team2753.splines.TrajectoryDriveController;
import com.team2753.trajectory.FollowerConfig;

import static com.team2753.Constants.defaultTrajectoryConfig;

/**
 * Created by joshua9889 on 5/19/2018.
 */

public class AutoDrive{
    public AutoDrive(Drive drive){
        mDrive = drive;
    }

    public AutoDrive(){}

    private Drive mDrive;

    public void configureForSpeedControl(){
        mDrive.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private FollowerConfig followerConfig;
    private TrajectoryDriveController controller;

    public void configureForPathFollowing(Path pathToFollow, double direction, double angles){
        followerConfig = new FollowerConfig(Constants.p.getDouble(), Constants.d.getDouble(),
                Constants.v.getDouble(), Constants.a.getDouble(), Constants.headingP.getDouble());
        controller = new TrajectoryDriveController(mDrive, followerConfig);
        controller.loadPath(pathToFollow, direction, angles);
    }

    public void loadPathNoReset(Trajectory left, Trajectory right, double direction, double angles){
        followerConfig = new FollowerConfig(Constants.p.getDouble(), Constants.d.getDouble(),
                Constants.v.getDouble(), Constants.a.getDouble(), Constants.headingP.getDouble());
        controller = new TrajectoryDriveController(mDrive, followerConfig);
        controller.loadProfileNoReset(left,right, direction, angles);

    }

    private ElapsedTime dtTimer = new ElapsedTime();
    public void update(){
        if (dtTimer.seconds()> defaultTrajectoryConfig.dt && !controller.isOnTarget()) {
            // Update our controller
            controller.update();
            Thread.yield();
            dtTimer.reset();
        } else if(controller.isOnTarget()){
            mDrive.setLeftRightPower(0,0);

        }
    }

    public boolean isDone(){
        return controller.isOnTarget();
    }

    @Override
    public String toString(){
        return "Number of Segments: " + controller.getNumSegments() + "\n"+
                "Current Segment: " + controller.getFollowerCurrentSegment() + "\n"+
                "dt: " + dtTimer.seconds() + "\n" +
                "Gyro: " + mDrive.getGyroAngleRadians() + "\n";
    }
}
