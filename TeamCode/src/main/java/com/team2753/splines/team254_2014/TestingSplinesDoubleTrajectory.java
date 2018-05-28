package com.team2753.splines.team254_2014;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team254.lib_2014.trajectory.Path;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team2753.Constants;
import com.team2753.libs.PhoneLogger;
import com.team2753.subsystems.Drive;

import static com.team2753.Constants.config;

/**
 * Created by joshua9889 on 5/22/2018.
 *
 * Class used to test Trajectory generation and it works!
 */

@Autonomous
public class TestingSplinesDoubleTrajectory extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {

        // Used to log the speed and position to be able to plot the data in Excel
        PhoneLogger logger = new PhoneLogger("SpeedPosTimeData.csv");

        // Generate a simple path for testing
        Path path = Line.calculate(config, TrajectoryGenerator.SCurvesStrategy,
                0, 0, 15, 0);

        // Init our drivetrain
        Drive mDrive = new Drive();
        mDrive.init(this, true);

        // Use speed control to make the output of the motor linear
        mDrive.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

        /*
        * Used to make it easier to save constants
        * P term, D term, V term, A term, P term for gyro compensation
        * We are not using I because the feed-forward + feed-back control
        * will be enough to control the system
        */
        FollowerConfig followerConfig = new FollowerConfig(Constants.p.getDouble(), Constants.d.getDouble(),
                Constants.v.getDouble(), Constants.a.getDouble(), Constants.headingP.getDouble());

        // New Drive Controller
        TrajectoryDriveController controller = new TrajectoryDriveController(mDrive, followerConfig);

        // Tell the controller what profiles to follow
        // (the two 1's are in case we want to mirror the path)
        controller.loadPath(path, 1, 1);

        // Lets go
        telemetry.setAutoClear(false);
        telemetry.addData("Ready to Start","");
        telemetry.update();

        waitForStart();
        telemetry.setAutoClear(true);
        telemetry.clearAll();

        // Used to calculate the change in time from the last reading
        ElapsedTime dtTimer = new ElapsedTime();
        ElapsedTime timeToComplete = new ElapsedTime();
        double totalTime = 0;
        boolean first = true;

        // Used to calculate speed
        double lastPosition = 0;

        while(opModeIsActive()){
            // data
            telemetry.addData("Time To Complete", totalTime);
            telemetry.addData("Left Distance", mDrive.getLeftDistanceInches());
            telemetry.addData("Right Distance", mDrive.getRightDistanceInches());
            telemetry.addData("Left Speed", controller.wantedLeftSpeed);
            telemetry.addData("Right Speed", controller.wantedRightSpeed);
            telemetry.addData("Speed output", controller.getGoal());
            telemetry.addData("Gyro", mDrive.getGyroAngleRadians());
            telemetry.addData("dt", dtTimer.seconds());
            telemetry.addData("Number of Segments", controller.getNumSegments());
            telemetry.addData("Current Pos", controller.getFollowerCurrentSegment());
            telemetry.update();

            // Make sure we only update when we told the controller/trajectory we would
            if (dtTimer.seconds()>config.dt && !controller.isOnTarget()) {
                // Update our controller
                controller.update();

                // Used to log data so we can plot it
                double currentTime = timeToComplete.milliseconds();
                double currentLeftPos = mDrive.getLeftDistanceInches();
                double left_speed = (currentLeftPos-lastPosition)/dtTimer.milliseconds();
                logger.write(String.valueOf(currentTime) + "," +
                        String.valueOf(path.getLeftWheelTrajectory().getSegment(controller.getFollowerCurrentSegment()).pos)
                + "," + String.valueOf(currentLeftPos));

                idle();
                dtTimer.reset();
            } else if(controller.isOnTarget()){
                mDrive.setLeftRightPower(0,0);
                if(first) {
                    // Track how long it took to complete the path
                    totalTime = timeToComplete.seconds();
                    first = false;
                }
            }
        }

        // Close the logger
        logger.close();

        // Be able to move the robot after we run it
        mDrive.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}