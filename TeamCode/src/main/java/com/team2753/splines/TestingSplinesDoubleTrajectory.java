package com.team2753.splines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryFollower;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team2753.Constants;
import com.team2753.Team2753Linear;
import com.team2753.subsystems.Drive;

/**
 * Created by joshua9889 on 5/22/2018.
 */

@Autonomous
public class TestingSplinesDoubleTrajectory extends LinearOpMode{
    Trajectory trajectory;
    TrajectoryFollower followerLeft = new TrajectoryFollower("left");
    TrajectoryFollower followerRight = new TrajectoryFollower("right");

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Started","");
        telemetry.update();

        double distance = 20;
        double angle = 0.00001;

        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 2.0*12; // In/s
        config.max_acc = 0.6*12; // In/s^2
        config.max_jerk = 0.4*12; // In/s^3
        config.dt = 0.001; // seconds

        TrajectoryGenerator.Strategy strategy = TrajectoryGenerator.SCurvesStrategy;
        Trajectory reference = TrajectoryGenerator.generate(
                config,
                strategy,
                0.0, // start velocity
                0,
                Math.abs(distance),
                0.0, // goal velocity
                0);

        Trajectory leftProfile = reference;
        Trajectory rightProfile = reference.copy(); // Copy

        double radius = Math.abs(Math.abs(distance) / (angle * Math.PI / 180.0));
        double width = Constants.WHEEL_BASE;
        double faster = (radius + (width / 2.0)) / radius;
        double slower = (radius - (width / 2.0)) / radius;
        System.out.println("faster " + faster);
        if (angle > 0) {
            leftProfile.scale(faster);
            rightProfile.scale(slower);
        } else {
            leftProfile.scale(slower);
            rightProfile.scale(faster);
        }
        telemetry.addData("Finished Trajectory Generation","");
        telemetry.update();

        Drive mDrive = new Drive();
        mDrive.init(this, true);
        mDrive.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Ready to Start","");
        telemetry.update();
        waitForStart();

        double gearing = Constants.DRIVE_GEAR_REDUCTION;
        double Vmax = 12; // Volts
        double mass = 35.0/9.8; // lbs
        double numberOfMotors = 2;
        double stallTorque = 1.531179; // lb-in

        double velocityMax = (5480*Math.PI*Constants.WHEEL_DIAMETER_INCHES)/(gearing);
        double kv = Vmax/velocityMax;

        double accelerationMax = (2*numberOfMotors*stallTorque*gearing)/(4.0 * mass);
        double ka = Vmax/accelerationMax;

        FollowerConfig followerConfig = new FollowerConfig(0, 0, 0, kv, ka, 0);
        TrajectoryDriveController controller = new TrajectoryDriveController(mDrive, followerConfig);

        controller.loadProfile(leftProfile, rightProfile, 1, 1);

        ElapsedTime time = new ElapsedTime();

        while(opModeIsActive() && !controller.onTarget()){
            if (time.seconds()>config.dt) {
                controller.update();
                idle();
                telemetry.addData("dt", time.seconds());
                telemetry.addData("Current Segment", controller.getFollowerCurrentSegment());
                telemetry.addData("Number of Segments", controller.getNumSegments());
                telemetry.update();
                time.reset();
            }
        }
    }
}