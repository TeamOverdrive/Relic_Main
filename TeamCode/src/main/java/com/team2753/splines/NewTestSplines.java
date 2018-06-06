package com.team2753.splines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team254.lib_2014.trajectory.Trajectory;
import com.team254.lib_2014.trajectory.TrajectoryGenerator;
import com.team2753.Constants;
import com.team2753.subsystems.Drive;

import static com.team2753.Constants.defaultTrajectoryConfig;
import static com.team2753.Constants.defaultFollowerConfig;

/**
 * Created by joshua9889 on 5/26/2018.
 */

@Autonomous
@Disabled
@Deprecated
public class NewTestSplines extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("P", Constants.p.getDouble());
        telemetry.addData("D", Constants.d.getDouble());
        telemetry.addData("V", Constants.v.getDouble());
        telemetry.addData("A", Constants.a.getDouble());
        telemetry.update();

//        Path driveOffStone = Line.calculate(defaultTrajectoryConfig,
//                SCurvesStrategy,
//                0.0,
//                0.00001,
//                10,
//                0.00001);
//        Path fullPath = ScalePath.calculate(driveOffStone,
//                1.0/Constants.scale.getDouble());

        Trajectory ref = TrajectoryGenerator.generate(defaultTrajectoryConfig, TrajectoryGenerator.SCurvesStrategy,
                0, 0, 10, 0, 0.000001);

        // Init our drivetrain
        Drive mDrive = new Drive();
        // true = use gyros and reset the encoders
        mDrive.init(this, true);
        mDrive.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Lets go
        telemetry.setAutoClear(false);
        telemetry.addData("Ready to Start","");
        telemetry.update();

        waitForStart();
        telemetry.setAutoClear(true);

        // New Drive Controller
        TrajectoryDriveController controller = new TrajectoryDriveController(mDrive, defaultFollowerConfig);
        controller.loadProfile(ref, ref, 1, 1);
        ElapsedTime dtTimer = new ElapsedTime();

        while(opModeIsActive() && !controller.onTarget()){
            // data
            telemetry.addData("kV", Constants.v.getDouble());
            telemetry.addData("kA", Constants.a.getDouble());
            telemetry.addData("Left Distance", mDrive.getLeftDistanceInches());
            telemetry.addData("Right Distance", mDrive.getRightDistanceInches());
            telemetry.addData("Left Speed", controller.wantedLeftSpeed);
            telemetry.addData("Right Speed", controller.wantedRightSpeed);
            telemetry.addData("Speed output", controller.getGoal());
            telemetry.addData("Gyro", mDrive.getGyroAngleDegrees());
            telemetry.addData("dt", dtTimer.seconds());
            telemetry.addData("Number of Segments", controller.getNumSegments());
            telemetry.addData("Current Pos", controller.getFollowerCurrentSegment());
            telemetry.update();

            // Make sure we only update when we told the controller/trajectory we would
            if (dtTimer.seconds()> defaultTrajectoryConfig.dt) {
                controller.update();
                idle();
                dtTimer.reset();
            }
        }
    }
}
