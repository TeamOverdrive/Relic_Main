package com.team2753.splines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryFollower;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team2753.Team2753Linear;
import com.team2753.subsystems.AutoDrive;
import com.team2753.subsystems.Drive;

/**
 * Created by joshua9889 on 5/22/2018.
 */
@Autonomous
public class TestingSplinesSingleTrajectory extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Started","");
        telemetry.update();
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 2.0*12; // In/s
        config.max_acc = 0.6*12; // In/s^2
        config.max_jerk = 0.4*12; // In/s^3
        config.dt = 0.01; // seconds

        Trajectory trajectory = TrajectoryGenerator.generate(
                config, TrajectoryGenerator.SCurvesStrategy,
                0, 0,
                10, 0, 0);

        telemetry.addData("Trajectory Generation Finished","");
        telemetry.update();

        TrajectoryFollower follower = new TrajectoryFollower("Follower");
        follower.configure(2.4, 0, 0.1, 0, 0);
        follower.setTrajectory(trajectory);

        Drive mDrive = new Drive();
        mDrive.init(this, true);
        waitForStart();

        AutoDrive drive = new AutoDrive(mDrive, 1);

        ElapsedTime time = new ElapsedTime();
        sleep(10);

        while (!follower.isFinishedTrajectory() && opModeIsActive()){
            if (time.seconds()>config.dt){
                double distance = (mDrive.getRightDistanceInches()+mDrive.getLeftDistanceInches())/2;
                double forwardSpeed = follower.calculate(distance);
                double turnSpeed = -0.001*(mDrive.getGyroAngleDegrees() - follower.getHeading());
                telemetry.addData("Distance", distance);
                telemetry.addData("Turning Error", mDrive.getGyroAngleDegrees() - follower.getHeading());
                telemetry.addData("Forward", forwardSpeed);
                telemetry.addData("Turn", turnSpeed);
                telemetry.update();
                drive.setSpeedTurnPower(forwardSpeed, turnSpeed);
                time.reset();
            }
        }
        mDrive.setLeftRightPower(0,0);
    }
}
