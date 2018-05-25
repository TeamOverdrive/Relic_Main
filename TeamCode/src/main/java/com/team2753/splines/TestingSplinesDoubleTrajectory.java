package com.team2753.splines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryFollower;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team2753.Constants;
import com.team2753.libs.PhoneLogger;
import com.team2753.subsystems.Drive;

/**
 * Created by joshua9889 on 5/22/2018.
 *
 * Class used to test Trajectory generation
 */

@Autonomous
public class TestingSplinesDoubleTrajectory extends LinearOpMode{

    // The trajectory that we want to follow
    Trajectory trajectory;

    // Used to follow a trajectory
    TrajectoryFollower followerLeft = new TrajectoryFollower("left");
    TrajectoryFollower followerRight = new TrajectoryFollower("right");

    @Override
    public void runOpMode() throws InterruptedException {
        // Used to log data
        PhoneLogger logger = new PhoneLogger("Spline.txt");

        // Hello driver
        telemetry.addData("Started","");
        telemetry.update();

        // Distance of an arc that we want to travel.
        double distance = 5;

        // Angle that we want to be at, at the end of the circle.
        // Has to be non-zero because we, for now, assume that we are traveling in a arc.
        // Based on unit circle
        double angle = -0.00001;

        // Config for the based on what we want the robot to do, not
        // based on the characteristics of the robot.
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.max_vel = 2.0*12; // In/s
        config.max_acc = 0.6*12; // In/s^2
        config.max_jerk = 0.7*12; // In/s^3
        config.dt = 0.01; // seconds, change of time in each update

        // What kind of trajectory profile do we want?
        TrajectoryGenerator.Strategy strategy = TrajectoryGenerator.SCurvesStrategy;

        // Generate trajectory based to the inputs
        Trajectory reference = TrajectoryGenerator.generate(
                config,
                strategy,
                0.0, // start velocity
                0.0, // start heading
                Math.abs(distance), // goal position
                0.0, // goal velocity
                angle); // goal angle

        // Copy the trajectories to left and right
        // Right now the robot would go straight, if we purely used this traject.
        Trajectory leftProfile = reference;
        Trajectory rightProfile = reference.copy(); // Copy

        // Calculate the radius of the arc
        double radius = Math.abs(Math.abs(distance) / (angle * Math.PI / 180.0));

        double width = Constants.WHEEL_BASE;

        // Find the difference between the left and right motors
        double faster = (radius + (width / 2.0)) / radius;
        double slower = (radius - (width / 2.0)) / radius;

        // Determine which way to curve
        if (angle > 0) {
            leftProfile.scale(faster);
            rightProfile.scale(slower);
        } else {
            leftProfile.scale(slower);
            rightProfile.scale(faster);
        }

        // Used to make sure that all the outputs are in the range of [-1,1]
        // This is not scientific but rather a guess based to testing
        double scaleFactor = 1.0/102.0;
        leftProfile.scale(scaleFactor);
        rightProfile.scale(scaleFactor);

        // We're done making the path
        telemetry.addData("Finished Trajectory Generation","");
        telemetry.update();

        // Init our drivetrain
        Drive mDrive = new Drive();

        // true = use gyros and reset the encoders
        mDrive.init(this, true);

        // Use speed control. One less thing to control
        // TODO: Remove this line and test on the robot; see what happens
        mDrive.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Lets go
        telemetry.addData("Ready to Start","");
        telemetry.update();

        waitForStart();

        // Based on this whitepaper: https://www.chiefdelphi.com/media/papers/3402
        // Used to calculate the V and A terms of a PIDVA controller

        // Used to calculate the real maximum velocity and acceleration
        double gearing = Constants.DRIVE_GEAR_REDUCTION; // 1/3

        // TODO: Test this number on the robot
        // previously mass = 48/9.8, but this is in the wrong units,
        // but have not tested the above with the robot yet
        double mass = 48.0/32.174048694867; // Slug, ft/s^2

        double numberOfMotors = 2; // Per side
        double Vmax = 12; // Volts, max voltage that could be applied to the motors
        double stallTorque = 1.531179; // lb-in

        // Max velocity
        double velocityMax = (5400*Math.PI*Constants.WHEEL_DIAMETER_INCHES)/(gearing);

        // Constant used for PIDVA, V term
        double kv = Vmax/velocityMax;

        // Max acceleration
        double accelerationMax = (2*numberOfMotors*stallTorque*gearing)/(4.0 * mass);

        // Constant used for PIDVA, A term
        double ka = Vmax/accelerationMax;

        // Used to make it easier to save constants
        // P term, I term, D term, V term, A term, P term for gyro compensation
        FollowerConfig followerConfig = new FollowerConfig(0.1, 0, kv, ka, Math.PI/10000);

        // New Drive Controller
        TrajectoryDriveController controller = new TrajectoryDriveController(mDrive, followerConfig);

        // Tell the controller what profiles to follow
        // (the two 1's are in case we want to mirror the path)
        controller.loadProfile(leftProfile, rightProfile, 1, 1);

        // Used to calculate the change in time from the last reading
        ElapsedTime dtTimer = new ElapsedTime();

        // Used to wait a little to see the whole screen
        ElapsedTime starter = new ElapsedTime();

        while(opModeIsActive() && !controller.onTarget()){
            // data
            telemetry.addData("Left Pos", mDrive.getLeftCurrentPosition());
            telemetry.addData("Right Pos", mDrive.getRightCurrentPosition());
            telemetry.addData("Left Speed", controller.wantedLeftSpeed);
            telemetry.addData("Right Speed", controller.wantedRightSpeed);
            telemetry.addData("Speed output", controller.getGoal());
            telemetry.addData("Gyro", mDrive.getGyroAngleDegrees());
            telemetry.addData("dt", dtTimer.seconds());
            telemetry.addData("Number of Segments", controller.getNumSegments());
            telemetry.addData("Current Pos", controller.getFollowerCurrentSegment());
            telemetry.update();

            // Make sure we only update when we told the controller/trajectory we would
            if (dtTimer.seconds()>config.dt && starter.seconds()>1) {
                controller.update();

                // Log data
                logger.write(controller.wantedLeftSpeed);
                idle();
                dtTimer.reset();
            }
        }

        logger.close();

        // Be able to move the robot after we run it
        mDrive.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}