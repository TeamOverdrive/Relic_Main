package com.team2753.testing.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team2753.splines.FollowerDrive;
import com.team2753.subsystems.Drive;
import com.team2753.subsystems.FollowerWheel;

/**
 * Created by joshua9889 on 6/1/2018.
 */

@TeleOp
@Disabled
public class TestFollowerWheel extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Drive mDrive = new Drive();
        mDrive.init(this, true);
        mDrive.zeroSensors();

        FollowerWheel mFollow = new FollowerWheel();
        mFollow.init(this, false);

        FollowerDrive followerDrive = new FollowerDrive(mDrive, mFollow);

        telemetry.addData("Hello","");
        telemetry.update();
        waitForStart();

        ElapsedTime t = new ElapsedTime();
        mDrive.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        while (opModeIsActive()) {
            followerDrive.update();
            telemetry.addData("X", followerDrive.getX());
            telemetry.addData("Y", followerDrive.getY());
            telemetry.addData("Radius", followerDrive.radius());
            telemetry.addData("Angle", followerDrive.angle());
            telemetry.addData("Real Angle", mDrive.getGyroAngleRadians());
            telemetry.update();
        }
        mDrive.setLeftRightPower(0,0);

        telemetry.addData("X", followerDrive.getX());
        telemetry.addData("Y", followerDrive.getY());
        telemetry.update();
        while (opModeIsActive())
            Thread.yield();

    }
}
