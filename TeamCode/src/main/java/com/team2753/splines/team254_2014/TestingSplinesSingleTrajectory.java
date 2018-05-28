package com.team2753.splines.team254_2014;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryFollower;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team2753.Constants;
import com.team2753.subsystems.Drive;

/**
 * Created by joshua9889 on 5/22/2018.
 */
@Autonomous
@Disabled
@Deprecated
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

        double gearing = Constants.DRIVE_GEAR_REDUCTION;
        double Vmax = 12; // Volts
        double mass = 48.0/9.8; // lbs
        double numberOfMotors = 2;
        double stallTorque = 1.531179; // lb-in

        double velocityMax = (5400*Math.PI*Constants.WHEEL_DIAMETER_INCHES)/(gearing);
        double kv = Vmax/velocityMax;

        double accelerationMax = (2*numberOfMotors*stallTorque*gearing)/(4.0 * mass);
        double ka = Vmax/accelerationMax;

        FollowerConfig followerConfig = new FollowerConfig(0.1, 0, kv, ka, Math.PI/1000);

        TrajectoryFollower follower = new TrajectoryFollower("Follower");

        follower.configure(followerConfig.get()[0], followerConfig.get()[1],
                followerConfig.get()[2], followerConfig.get()[3],
                followerConfig.get()[4]);
        follower.setTrajectory(trajectory);

        Drive mDrive = new Drive();
        mDrive.init(this, true);
        mDrive.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();

        ElapsedTime time = new ElapsedTime();
        sleep(10);

        while (!follower.isFinishedTrajectory() && opModeIsActive()){
            if (time.seconds()>config.dt){
                double distance = (mDrive.getRightDistanceInches()+mDrive.getLeftDistanceInches())/2;
                double forwardSpeed = follower.calculate(distance);
                telemetry.addData("Distance", distance);
                telemetry.addData("Turning Error", mDrive.getGyroAngleDegrees() - follower.getHeading());
                telemetry.addData("Forward", forwardSpeed);
                telemetry.update();
                mDrive.setLeftRightPower(forwardSpeed, forwardSpeed);
                time.reset();
            }
        }
        mDrive.setLeftRightPower(0,0);
    }
}
