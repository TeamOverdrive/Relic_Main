package com.team2753.splines;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team2753.libs.PhoneLogger;
import com.team2753.subsystems.Drive;

/**
 * Created by joshua9889 on 5/27/2018.
 *
 * This OpMode is used to find the max vel and acc of the robot to calculate kV and kA
 */

@TeleOp
@Disabled
public class FindPDVA extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        // Drivetrain
        Drive mDrive = new Drive();
        mDrive.init(this, false);
        mDrive.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // plot
        PhoneLogger logger = new PhoneLogger("MaxVelMaxAccTimeData.csv");
        waitForStart();

        // Max Speed
        mDrive.setLeftRightPower(1, 1);

        ElapsedTime time = new ElapsedTime();
        ElapsedTime dt = new ElapsedTime();
        double lastPosition = 0;
        double lastSpeed = 0;

        // Go for 3 seconds
        while (opModeIsActive() && time.seconds()<3){
            double currentLeftPos = mDrive.getLeftDistanceInches();
            double left_speed = (currentLeftPos-lastPosition)/dt.seconds();
            double accl = (left_speed-lastSpeed)/dt.seconds();

            // Make sure the file is not 60k large with no data
            if(left_speed != 0 && accl != 0)
                logger.write(time.seconds() + "," + left_speed + "," + accl);

            lastPosition = currentLeftPos;
            lastSpeed = left_speed;
            dt.reset();
        }

        // Close the logger
        logger.close();
    }
}
