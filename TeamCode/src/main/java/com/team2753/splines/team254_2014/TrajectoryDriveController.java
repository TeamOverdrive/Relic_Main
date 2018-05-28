package com.team2753.splines.team254_2014;

import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryFollower;
import com.team254.lib_2014.util.ChezyMath;
import com.team2753.subsystems.Drive;

/**
 * Created by joshua9889 on 5/22/2018.
 *
 * Used to control the Drivetrain when following a Trajectory
 */

public class TrajectoryDriveController{

    public TrajectoryDriveController(Drive drive, FollowerConfig followerConfig) {
        mDrive = drive;
        this.followerConfig = followerConfig;
        init();
    }

    private Drive mDrive;
    private FollowerConfig followerConfig;

    // Used to display on the Driverstation
    public double wantedLeftSpeed = 0;
    public double wantedRightSpeed = 0;

    private Trajectory trajectory;
    private TrajectoryFollower followerLeft = new TrajectoryFollower("left");
    private TrajectoryFollower followerRight = new TrajectoryFollower("right");
    private double direction, heading, kTurn;

    public boolean onTarget() {
        return followerLeft.isFinishedTrajectory();
    }

    private void init() {
        followerLeft.configure(followerConfig.get()[0], 0, followerConfig.get()[1],
                followerConfig.get()[2], followerConfig.get()[3]);

        followerRight.configure(followerConfig.get()[0], 0, followerConfig.get()[1],
                followerConfig.get()[2], followerConfig.get()[3]);

        kTurn = followerConfig.get()[4];
    }

    public void loadPath(Path path, double direction, double heading){
        loadProfile(path.getLeftWheelTrajectory(), path.getRightWheelTrajectory(),
                direction, heading);
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
            double distanceL = direction * mDrive.getLeftDistanceInches(); // inches
            double distanceR = direction * mDrive.getRightDistanceInches();

            // Calculate the PDVA here based on the current position
            double speedLeft = direction * followerLeft.calculate(distanceL);
            double speedRight = direction * followerRight.calculate(distanceR);
            wantedLeftSpeed = speedLeft;
            wantedRightSpeed = speedRight;

            // Gyro correction
            double goalHeading = followerLeft.getHeading(); // Radians
            double observedHeading = mDrive.getGyroAngleRadians(); // Radians

            double angleDiffRads = ChezyMath.getDifferenceInAngleRadians(observedHeading, goalHeading);
            double angleDiff = Math.toDegrees(angleDiffRads);

            // Calculate the Proportional value
            double turn = kTurn * angleDiffRads;

            // Steer like a car, but with two pedals
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
