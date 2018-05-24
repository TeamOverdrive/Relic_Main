package com.team2753.splines;

import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryFollower;
import com.team254.lib_2014.util.ChezyMath;
import com.team2753.subsystems.Drive;

/**
 * Created by joshua9889 on 5/22/2018.
 */

public class TrajectoryDriveController{

    public TrajectoryDriveController(Drive drive, FollowerConfig followerConfig) {
        mDrive = drive;
        this.followerConfig = followerConfig;
        init();
    }

    private Drive mDrive;
    private FollowerConfig followerConfig;

    Trajectory trajectory;
    TrajectoryFollower followerLeft = new TrajectoryFollower("left");
    TrajectoryFollower followerRight = new TrajectoryFollower("right");
    double direction;
    double heading;
    double kTurn = -0.0001;

    public boolean onTarget() {
        return followerLeft.isFinishedTrajectory();
    }

    private void init() {
        followerLeft.configure(followerConfig.get()[0], followerConfig.get()[1],
                followerConfig.get()[2], followerConfig.get()[3],
                followerConfig.get()[4]);
        followerRight.configure(followerConfig.get()[0], followerConfig.get()[1],
                followerConfig.get()[2], followerConfig.get()[3],
                followerConfig.get()[4]);
        kTurn = followerConfig.get()[5];
    }

    public void loadProfile(Trajectory leftProfile, Trajectory rightProfile,
                            double direction, double heading) {
        reset();
        followerLeft.setTrajectory(leftProfile);
        followerRight.setTrajectory(rightProfile);
        this.direction = direction;
        this.heading = heading;
    }

    public void loadProfileNoReset(Trajectory leftProfile, Trajectory rightProfile) {
        followerLeft.setTrajectory(leftProfile);
        followerRight.setTrajectory(rightProfile);
    }

    public void reset() {
        followerLeft.reset();
        followerRight.reset();
        mDrive.zeroSensors();
    }

    public boolean isOnTarget() {
        return followerLeft.isFinishedTrajectory();
    }

    public int getFollowerCurrentSegment() {
        return followerLeft.getCurrentSegment();
    }

    public int getNumSegments() {
        return followerLeft.getNumSegments();
    }

    public void update() {
        if (onTarget()) {
            mDrive.setLeftRightPower(0.0, 0.0);
        } else  {
            double distanceL = direction * mDrive.getLeftDistanceInches();
            double distanceR = direction * mDrive.getRightDistanceInches();

            double speedLeft = direction * followerLeft.calculate(distanceL);
            double speedRight = direction * followerRight.calculate(distanceR);

            double goalHeading = followerLeft.getHeading();
            double observedHeading = mDrive.getGyroAngleRadians();

            double angleDiffRads = ChezyMath.getDifferenceInAngleRadians(observedHeading, goalHeading);
            double angleDiff = Math.toDegrees(angleDiffRads);

            double turn = kTurn * angleDiff;
            mDrive.setLeftRightPower(speedLeft + turn, speedRight - turn);
        }
    }

    public void setTrajectory(Trajectory t) {
        this.trajectory = t;
    }

    public double getGoal() {
        return 0;
    }
}
