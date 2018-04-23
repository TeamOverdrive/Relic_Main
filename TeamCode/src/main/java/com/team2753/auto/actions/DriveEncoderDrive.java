package com.team2753.auto.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.team2753.subsystems.Drive;
import com.team2753.subsystems.Robot;

import static com.team2753.subsystems.Drive.COUNTS_PER_INCH;

/**
 * Created by joshua9889 on 3/24/2018.
 */

public class DriveEncoderDrive implements Action {

    private Drive mDrive = Robot.getInstance().getDrive();
    private int leftTarget, rightTarget;
    private double speed, timeoutS;
    private ElapsedTime t = new ElapsedTime();

    public DriveEncoderDrive(double speed, double leftInches, double rightInches, double timeoutS){
        this.speed = Math.abs(speed);
        this.leftTarget = (int) (leftInches * COUNTS_PER_INCH);
        this.rightTarget = (int) (rightInches * COUNTS_PER_INCH);
        this.timeoutS = timeoutS;
    }

    @Override
    public void start() {
        this.leftTarget += mDrive.getLeftCurrentPosition();
        this.rightTarget += mDrive.getRightCurrentPosition();

        mDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mDrive.setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

        mDrive.setLeftRightTarget(leftTarget, rightTarget);

        t.reset();
        mDrive.setLeftRightPowers(speed, speed);
    }

    @Override
    public void update() {
        if ((Math.abs(leftTarget - mDrive.getLeftCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                && (Math.abs(rightTarget - mDrive.getRightCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                && speed > 0.1) {
            mDrive.setLeftRightPowers(speed * 0.7, speed * 0.75);
        } else if ((Math.abs(leftTarget - mDrive.getLeftCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                && (Math.abs(rightTarget - mDrive.getRightCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                && speed > 0.1) {
            mDrive.setLeftRightPowers(speed * 0.3, speed * 0.3);
        }
    }

    @Override
    public boolean isFinished() {
        return t.seconds()<timeoutS &&
                (mDrive.leftIsBusy() || mDrive.rightIsBusy());
    }

    @Override
    public void done() {
        // Stop all motion;
        mDrive.setLeftRightPowers(0,0);

        // Turn off RUN_TO_POSITION
        mDrive.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
